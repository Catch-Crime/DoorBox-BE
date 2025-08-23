package com.example.doorboxbe.global.apiPayload.exception;

import com.example.doorboxbe.global.apiPayload.code.BaseErrorCode;

public class TokenException extends GeneralException {

    public TokenException(BaseErrorCode baseErrorCode) { super(baseErrorCode);}
}
