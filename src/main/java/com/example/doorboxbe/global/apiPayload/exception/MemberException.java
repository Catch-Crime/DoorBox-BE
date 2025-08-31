package com.example.doorboxbe.global.apiPayload.exception;

import com.example.doorboxbe.global.apiPayload.code.BaseErrorCode;

public class MemberException extends GeneralException {

    public MemberException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }

}

