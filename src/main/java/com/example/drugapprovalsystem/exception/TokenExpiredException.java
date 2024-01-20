package com.example.drugapprovalsystem.exception;

import com.example.drugapprovalsystem.model.Message;

public class TokenExpiredException extends Exception {
    @Override
    public String getMessage() {
        return Message.msgTokenExpired;
    }
}
