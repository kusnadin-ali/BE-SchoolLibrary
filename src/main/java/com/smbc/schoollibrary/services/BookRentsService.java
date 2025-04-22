package com.smbc.schoollibrary.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.smbc.schoollibrary.constants.StatusEnum;
import com.smbc.schoollibrary.constants.ApiConstant.ResponseMessage;
import com.smbc.schoollibrary.dto.BookRentDto;
import com.smbc.schoollibrary.dto.SMBCResponseDto;
import com.smbc.schoollibrary.models.BookCatalog;
import com.smbc.schoollibrary.models.BookRents;
import com.smbc.schoollibrary.models.Members;
import com.smbc.schoollibrary.repository.BookCatalogRepository;
import com.smbc.schoollibrary.repository.BookRentsRepository;
import com.smbc.schoollibrary.repository.MembersRepository;
import com.smbc.schoollibrary.utils.ResponseUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookRentsService {

    private final BookRentsRepository bookRentsRepository;
    private final MembersRepository membersRepository;
    private final BookCatalogRepository bookCatalogRepository;

    public SMBCResponseDto<Object> getAllBookRents() {
        try {
            List<BookRents> bookRents = bookRentsRepository.findAll();

            return ResponseUtil.success(bookRents, ResponseMessage.SUCCESS_RETRIEVE_DATA);
        } catch (Exception e) {
            log.error("Error retrieve data", e);
            return ResponseUtil.error(null, ResponseMessage.ERROR_RETRIEVE_DATA);
        }
    }

    public SMBCResponseDto<Object> createNewBookRent(BookRentDto request) {
        try {
            Optional<Members> memberExist = membersRepository.findByStudentNumber(request.getStudentNumber());
            Optional<BookCatalog> bookExist = bookCatalogRepository.findByBookCode(request.getBookCode());

            if (!(memberExist.isPresent() && bookExist.isPresent())) {
                return ResponseUtil.error(null, ResponseMessage.ERROR_DATA_DOESNT_EXIST);
            }

            Optional<BookRents> bookRentExist = bookRentsRepository.findByStudentNumberAndBookCodeAndStatus(
                    request.getStudentNumber(), request.getBookCode(), StatusEnum.RENTED
            );

            if (bookRentExist.isPresent()) {
                return ResponseUtil.error(null, ResponseMessage.ERROR_DATA_ALREADY_RENTED);
            }

            LocalDate dateNow = LocalDate.now();

            BookRents newBookRent = new BookRents();
            newBookRent.setStudentNumber(request.getStudentNumber());
            newBookRent.setBookCode(request.getBookCode());
            newBookRent.setDueDate(LocalDate.now().plusDays(7));
            newBookRent.setRentDate(dateNow);
            newBookRent.setStatus(StatusEnum.RENTED);

            bookRentsRepository.save(newBookRent);

            return ResponseUtil.success(newBookRent, ResponseMessage.SUCCESS_CREATE_DATA);
        } catch (Exception e) {
            log.error("Error create data", e);
            return ResponseUtil.error(null, ResponseMessage.ERROR_CREATE_DATA);
        }
    }

    public SMBCResponseDto<Object> returnBook(BookRentDto request) {
        try {
            Optional<BookRents> bookRentExist = bookRentsRepository.findByStudentNumberAndBookCodeAndStatus(
                    request.getStudentNumber(), request.getBookCode(), StatusEnum.RENTED);

            if (!bookRentExist.isPresent()) {
                return ResponseUtil.error(null, ResponseMessage.ERROR_DATA_DOESNT_EXIST);
            }

            BookRents bookRent = bookRentExist.get();

            bookRent.setStatus(StatusEnum.RETURNED);
            bookRent.setReturnDate(LocalDate.now());

            bookRentsRepository.save(bookRent);

            return ResponseUtil.success(bookRent, ResponseMessage.SUCCESS_UPDATE_DATA);
        } catch (Exception e) {
            log.error("Error update data", e);
            return ResponseUtil.error(null, ResponseMessage.ERROR_UPDATE_DATA);
        }
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void checkOverdueBookRents() {
        try {
            List<BookRents> bookRents = bookRentsRepository.findByStatus(StatusEnum.RENTED);

            for (BookRents bookRent : bookRents) {
                if (Objects.equals(bookRent.getDueDate(), LocalDate.now())) {
                    bookRent.setStatus(StatusEnum.OVERDUE);
                    bookRentsRepository.save(bookRent);
                }
            }
        } catch (Exception e) {
            log.error("Error update data", e);
        }
    }

}
