package com.demo.jdbcclientdemo.constant;

public class ServiceNameConstant {
    private ServiceNameConstant() {
        throw new IllegalStateException();
    }

    public static final String CONTROLLER_PATH_API = "/api";


    public static final String SERVICE_GET_BOOK_All = "/getBookAll";
    public static final String SERVICE_GET_BOOK_All_PAGE = "/getBookAll/{page}";
    public static final String SERVICE_INSERT_BOOK = "/insertBook";
    public static final String SERVICE_UPDATE_BOOK = "/updateBook";
    public static final String SERVICE_FIND_BOOK = "/findBook/{id}";
    public static final String SERVICE_SEARCH_BOOK = "/searchBook/{name}/page/{page}";
    public static final String SERVICE_DELETE_BOOK = "/deleteBook/{id}";
}
