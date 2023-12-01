package com.demo.jdbcclientdemo.exceptions;

public class ServiceException extends Exception {

    public ServiceException() {

    }

    public ServiceException(String messageCode) {
        super(messageCode);
    }

    public ServiceException(String messageCode, Throwable cause) {
        super(messageCode, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String messageCode, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(messageCode, cause, enableSuppression, writableStackTrace);
    }

}
