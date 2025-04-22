package com.smbc.schoollibrary.utils;

import com.smbc.schoollibrary.constants.ApiConstant.ResponseCode;
import com.smbc.schoollibrary.constants.ApiConstant.ResponseMessage;
import com.smbc.schoollibrary.dto.SMBCResponseDto;

public class ResponseUtil {

    public static <T> SMBCResponseDto<T> success() {
        return success(null);
    }

    public static <T> SMBCResponseDto<T> success(T result) {
        SMBCResponseDto<T> response = new SMBCResponseDto<>();
        response.setCode(ResponseCode.SUCCESS_CODE);
        response.setMessage(ResponseMessage.SUCCESS_MESSAGE);
        response.setResult(result);

        return response;
    }

    public static <T> SMBCResponseDto<T> success(T result, String message) {

        SMBCResponseDto<T> response = new SMBCResponseDto<>();
        response.setCode(ResponseCode.SUCCESS_CODE);
        response.setMessage(message);
        response.setResult(result);

        return response;
    }

    public static <T> SMBCResponseDto<T> error(T result) {

        SMBCResponseDto<T> response = new SMBCResponseDto<>();
        response.setCode(ResponseCode.ERROR_CODE);
        response.setMessage(ResponseMessage.ERROR_MESSAGE);
        response.setResult(result);

        return response;
    }

    public static <T> SMBCResponseDto<T> error(T result, String message) {

        SMBCResponseDto<T> response = new SMBCResponseDto<>();
        response.setCode(ResponseCode.ERROR_CODE);
        response.setMessage(message);
        response.setResult(result);

        return response;
    }
}
