package com.quantcast.interview.exceptions;

public class CookieException extends Exception {
    private String message;
    private int code;

    public CookieException() {
    }

    public CookieException(ExceptionMessage em) {
        this.message = em.getMessage();
        this.code = em.getCode();
    }

    public String getMessage(){
        return message;
    }

    public int getCode(){
        return code;
    }
}
