package com.quantcast.interview.exceptions;

public class ExceptionMessage {

    private String message;
    private int code;

    public ExceptionMessage(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }
    public int getCode() {
        return code;
    }
}
