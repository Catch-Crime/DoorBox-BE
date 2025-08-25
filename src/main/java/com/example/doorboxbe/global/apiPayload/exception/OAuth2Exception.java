package com.example.doorboxbe.global.apiPayload.exception;

import com.example.doorboxbe.global.apiPayload.code.BaseErrorCode;

public class OAuth2Exception extends GeneralException {

    public OAuth2Exception(BaseErrorCode baseErrorCode) { super(baseErrorCode);}

}