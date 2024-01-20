package com.example.drugapprovalsystem.exception;

import com.example.drugapprovalsystem.model.Message;

public class InvalidCredentialsException extends Exception{
    @Override
    public String getMessage() {
        return Message.msgTokenExpired;
    }
}
