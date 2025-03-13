package com.smbc.schoollibrary.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.smbc.schoollibrary.constants.ApiConstant.ResponseCode;
import com.smbc.schoollibrary.constants.ApiConstant.ResponseMessage;

public class ResponseUtil {
    
    public static <T> ResponseEntity<Map<String, Object>> success() {
        return success(null);
    }

    public static <T> ResponseEntity<Map<String, Object>> success(T result) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", ResponseCode.SUCCESS_CODE);
        response.put("message", ResponseMessage.SUCCESS_MESSAGE);
        if (result != null)
            response.put("result", result);

        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<Map<String, Object>> success(T result, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", ResponseCode.SUCCESS_CODE);
        response.put("message", message);
        if (result != null)
            response.put("result", result);

        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<Map<String, Object>> error(T result) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", ResponseCode.ERROR_CODE);
        response.put("message", ResponseMessage.ERROR_MESSAGE);
        if (result != null)
            response.put("result", result);

        return ResponseEntity.status(200).body(response);
    }

    public static <T> ResponseEntity<Map<String, Object>> error(T result, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", ResponseCode.ERROR_CODE);
        response.put("message", message);
        if (result != null)
            response.put("result", result);

        return ResponseEntity.status(200).body(response);
    }
}
