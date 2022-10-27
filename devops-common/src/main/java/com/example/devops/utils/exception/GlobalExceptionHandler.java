package com.example.devops.utils.exception;

import com.example.devops.utils.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ApplicationException.class})  // Có thể bắt nhiều loại exception
    public ResponseEntity<?> handleExceptionA(ApplicationException e) {
        return BaseResponse.error(e);
    }
}
