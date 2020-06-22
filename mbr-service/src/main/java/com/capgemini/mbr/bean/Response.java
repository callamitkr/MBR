package com.capgemini.mbr.bean;

import org.springframework.http.HttpStatus;

public class Response {
    private String message;
    private HttpStatus status;

    public Response(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatusCode(HttpStatus status) {
        this.status = status;
    }
}
