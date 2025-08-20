package com.example.doorboxbe.global.apiPayload.exception;

import com.example.doorboxbe.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {

    private final BaseErrorCode code;

    public GeneralException(BaseErrorCode errorCode) {
        this.code = errorCode;
    }
}
