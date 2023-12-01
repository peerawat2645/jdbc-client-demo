package com.demo.jdbcclientdemo.constant;

public class ExceptionConstant {
    private ExceptionConstant() {
        throw new IllegalStateException();
    }

    public static final String SERVICE_EXCEPTION = "000500";
    public static final String DATABASE_EXCEPTION = "990000";

    public static final String ERROR_CODE_BOOK_ID_IS_INVALID = "100464";
    public static final String ERROR_CODE_BOOK_NAME_IS_INVALID = "100465";
    public static final String ERROR_CODE_BOOK_TITLE_IS_INVALID = "100466";


}
