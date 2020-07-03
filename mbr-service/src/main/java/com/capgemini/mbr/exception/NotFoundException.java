package com.capgemini.mbr.exception;

public class NotFoundException extends Exception {
    private static final long serialVersionUID = 1L;
    private String messageKey;
    public NotFoundException(String messageKey,String message){
        super(message);
        this.messageKey = messageKey;

    }
    public String getMessageKey() {
        return messageKey;
    }

   /* public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }*/
}
