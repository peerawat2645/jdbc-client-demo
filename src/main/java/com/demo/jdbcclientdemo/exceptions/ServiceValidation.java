package com.demo.jdbcclientdemo.exceptions;

public class ServiceValidation extends Exception {

    public ServiceValidation() {

    }

    public ServiceValidation(String messageCode) {
        super(messageCode);
    }

    public ServiceValidation(String messageCode, Throwable cause) {
        super(messageCode, cause);
    }

    public ServiceValidation(Throwable cause) {
        super(cause);
    }

    public ServiceValidation(String messageCode, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(messageCode, cause, enableSuppression, writableStackTrace);
    }

}
