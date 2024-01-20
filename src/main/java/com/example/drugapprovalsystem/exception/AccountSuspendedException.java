package com.example.drugapprovalsystem.exception;

import com.example.drugapprovalsystem.model.Message;

public class AccountSuspendedException extends Exception {
    @Override
    public String getMessage(){
        return Message.msgAccountSuspended;
    }
}
