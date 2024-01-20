package com.example.drugapprovalsystem.exception;

import com.example.drugapprovalsystem.model.Message;

public class InvalidateException extends Exception {
    @Override
    public String getMessage(){
        return Message.msgInvalidCredential;
    }
}
