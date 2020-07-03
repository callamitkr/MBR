package com.capgemini.mbr.exception;

public class FoundException extends Exception {
    private static final long serialVersionUID = 1L;
    private String messageKey;
    public FoundException(String messageKey,String message){
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
