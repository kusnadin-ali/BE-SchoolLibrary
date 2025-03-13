package com.smbc.schoollibrary.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smbc.schoollibrary.constants.ApiConstant.ResponseMessage;
import com.smbc.schoollibrary.dto.MemberDto;
import com.smbc.schoollibrary.models.Members;
import com.smbc.schoollibrary.repository.MembersRepository;
import com.smbc.schoollibrary.utils.ResponseUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MembersService {
    private final MembersRepository membersRepository;

    public ResponseEntity<?> getAllMember() {
        try {

            List<Members> members = membersRepository.findAll();
            return ResponseUtil.success(members, ResponseMessage.SUCCESS_RETRIEVE_DATA);
        } catch (Exception e) {
            log.error("Error retrieve data", e);
            return ResponseUtil.error(null, ResponseMessage.ERROR_RETRIEVE_DATA);
        }
    }

    public ResponseEntity<?> getMemberById(Long id) {
        try {
            Optional<Members> members = membersRepository.findByIdAndIsDeletedFalse(id);
    
            if (!members.isPresent()) {
                return ResponseUtil.error(null, ResponseMessage.ERROR_DATA_DOESNT_EXIST);
            }
    
            return ResponseUtil.success(members, ResponseMessage.SUCCESS_FIND_DATA);
        } catch (Exception e) {
            log.error("Error find data", e);
            return ResponseUtil.error(null, ResponseMessage.ERROR_RETRIEVE_DATA);
        }
    }

    public ResponseEntity<?> createMember(MemberDto request) {
        try {
            Optional<Members> memberExist = membersRepository.findByStudentNumber(request.getStudentNumber());
    
            if (memberExist.isPresent()) {
                return ResponseUtil.error(null, ResponseMessage.ERROR_DATA_ALREADY_EXIST);
            }
            
            Members newMember = new Members();
            newMember.setFullname(request.getFullname());
            newMember.setStudentNumber(request.getStudentNumber());
            newMember.setGender(request.getGender());
    
            membersRepository.save(newMember);
    
            return ResponseUtil.success(newMember, ResponseMessage.SUCCESS_CREATE_DATA);
        } catch (Exception e) {
            log.error("Error create data", e);
            return ResponseUtil.error(null, ResponseMessage.ERROR_CREATE_DATA);
        }
    }

    public ResponseEntity<?> updateMember(Long id, MemberDto request) {
        try {
            Optional<Members> memberExist = membersRepository.findByIdAndIsDeletedFalse(id);
    
            if (!memberExist.isPresent()) {
                return ResponseUtil.error(null, ResponseMessage.ERROR_DATA_DOESNT_EXIST);
            }

            Members updateMember = memberExist.get();
            updateMember.setFullname(request.getFullname());
            updateMember.setStudentNumber(request.getStudentNumber());
            updateMember.setGender(request.getGender());

            membersRepository.save(updateMember);

            return ResponseUtil.success(updateMember, ResponseMessage.SUCCESS_UPDATE_DATA);
        } catch (Exception e) {
            log.error("Error update data", e);
            return ResponseUtil.error(null, ResponseMessage.ERROR_UPDATE_DATA);
        }
    }

    public ResponseEntity<?> deleteMember(Long id) {
        try {
            Optional<Members> memberExist = membersRepository.findByIdAndIsDeletedFalse(id);
    
            if (!memberExist.isPresent()) {
                return ResponseUtil.error(null, ResponseMessage.ERROR_DATA_DOESNT_EXIST);
            }
    
            memberExist.get().setIsDeleted(true);
    
            membersRepository.save(memberExist.get());
    
            return ResponseUtil.success(null, ResponseMessage.SUCCESS_REMOVE_DATA);
        } catch (Exception e) {
            log.error("Error remove data", e);
            return ResponseUtil.error(null, ResponseMessage.ERROR_REMOVE_DATA);
        }
    }
}
