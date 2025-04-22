package com.smbc.schoollibrary.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.smbc.schoollibrary.constants.StatusBookEnum;
import com.smbc.schoollibrary.constants.ApiConstant.ResponseMessage;
import com.smbc.schoollibrary.dto.BookDto;
import com.smbc.schoollibrary.dto.ListBookPojo;
import com.smbc.schoollibrary.dto.SMBCResponseDto;
import com.smbc.schoollibrary.models.BookCatalog;
import com.smbc.schoollibrary.repository.BookCatalogRepository;
import com.smbc.schoollibrary.utils.ResponseUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookCatalogService {

    private final BookCatalogRepository bookCatalogRepository;

    public SMBCResponseDto<Object> getAllBookCatalog(StatusBookEnum availability) {
        try {
            List<ListBookPojo> bookCatalog = bookCatalogRepository
                    .getAllWithAvailability(Objects.isNull(availability) ? null : availability.getStatus());
            return ResponseUtil.success(bookCatalog, ResponseMessage.SUCCESS_RETRIEVE_DATA);

        } catch (Exception e) {
            log.error("Error retrieve data", e);
            return ResponseUtil.error(null, ResponseMessage.ERROR_RETRIEVE_DATA);
        }

    }

    public SMBCResponseDto<Object> getBookCatalogById(Long id) {
        try {
            Optional<BookCatalog> bookCatalog = bookCatalogRepository.findByIdAndIsDeletedFalse(id);

            if (!bookCatalog.isPresent()) {
                return ResponseUtil.error(null, ResponseMessage.ERROR_DATA_DOESNT_EXIST);
            }

            return ResponseUtil.success(bookCatalog, ResponseMessage.SUCCESS_FIND_DATA);
        } catch (Exception e) {
            log.error("Error find data", e);
            return ResponseUtil.error(null, ResponseMessage.ERROR_RETRIEVE_DATA);
        }
    }

    public SMBCResponseDto<Object> addNewBookCatalog(BookDto request) {
        try {
            Optional<BookCatalog> bookExist = bookCatalogRepository.findByBookCode(request.getBookCode());

            if (bookExist.isPresent()) {
                return ResponseUtil.error(null, ResponseMessage.ERROR_DATA_ALREADY_EXIST);
            }

            BookCatalog newBook = new BookCatalog();
            newBook.setBookCode(request.getBookCode());
            newBook.setTitle(request.getTitle());
            newBook.setAuthor(request.getAuthor());
            newBook.setPublisher(request.getPublisher());
            newBook.setGenre(request.getGenre());
            newBook.setPublishDate(request.getPublishDate());
            newBook.setIsDeleted(false);

            bookCatalogRepository.save(newBook);

            return ResponseUtil.success(newBook, ResponseMessage.SUCCESS_CREATE_DATA);
        } catch (Exception e) {
            log.error("Error create data", e);
            return ResponseUtil.error(null, ResponseMessage.ERROR_CREATE_DATA);
        }
    }

    public SMBCResponseDto<Object> updateBookCatalog(Long id, BookDto request) {
        try {
            Optional<BookCatalog> bookExist = bookCatalogRepository.findByIdAndIsDeletedFalse(id);

            if (!bookExist.isPresent()) {
                return ResponseUtil.error(null, ResponseMessage.ERROR_DATA_DOESNT_EXIST);
            }

            BookCatalog updateBook = bookExist.get();
            updateBook.setBookCode(request.getBookCode());
            updateBook.setTitle(request.getTitle());
            updateBook.setAuthor(request.getAuthor());
            updateBook.setPublisher(request.getPublisher());
            updateBook.setGenre(request.getGenre());
            updateBook.setPublishDate(request.getPublishDate());

            bookCatalogRepository.save(updateBook);

            return ResponseUtil.success(updateBook, ResponseMessage.SUCCESS_UPDATE_DATA);
        } catch (Exception e) {
            log.error("Error update data", e);
            return ResponseUtil.error(null, ResponseMessage.ERROR_UPDATE_DATA);
        }
    }

    public SMBCResponseDto<Object> deleteBookCatalog(Long id) {
        try {
            Optional<BookCatalog> bookExist = bookCatalogRepository.findByIdAndIsDeletedFalse(id);

            if (!bookExist.isPresent()) {
                return ResponseUtil.error(null, ResponseMessage.ERROR_DATA_DOESNT_EXIST);
            }

            bookExist.get().setIsDeleted(true);

            bookCatalogRepository.save(bookExist.get());

            return ResponseUtil.success(null, ResponseMessage.SUCCESS_REMOVE_DATA);
        } catch (Exception e) {
            log.error("Error remove data", e);
            return ResponseUtil.error(null, ResponseMessage.ERROR_REMOVE_DATA);
        }
    }
}
