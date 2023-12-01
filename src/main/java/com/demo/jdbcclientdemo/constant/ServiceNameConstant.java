package com.demo.jdbcclientdemo.constant;

public class ServiceNameConstant {
    private ServiceNameConstant() {
        throw new IllegalStateException();
    }

    public static final String CONTROLLER_PATH_API = "/api";
    public static final String SERVICE_VERSION = "/version";
    public static final String SERVICE_RELOAD = "/reload";


    public static final String SERVICE_GET_BOOK_All = "/getBookAll";
}
