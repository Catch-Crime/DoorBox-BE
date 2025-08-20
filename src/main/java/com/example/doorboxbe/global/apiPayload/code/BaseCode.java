package com.example.doorboxbe.global.apiPayload.code;

import org.springframework.http.HttpStatus;

public interface BaseCode {

    HttpStatus getHttpStatus();
    String getCode();
    String getMessage();
}
