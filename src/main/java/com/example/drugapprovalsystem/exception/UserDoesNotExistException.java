package com.example.drugapprovalsystem.exception;

import com.example.drugapprovalsystem.model.Message;

public class UserDoesNotExistException extends Exception{
    @Override
    public String getMessage(){
        return Message.msgUserDoesNotExist;
    }
}
