package com.demo.jdbcclientdemo.constant;

public class DatabaseConstant {

    private DatabaseConstant() {
        throw new IllegalStateException();
    }

    public static final String AND = " and ";
    public static final String IS_NOT_NULL = " is not null ";
    public static final String IS_NULL = " is null ";
    public static final String OR = " or ";
    public static final String LIKE_QUESTION_MARK = " like ? ";
    public static final String EQUAL_QUESTION_MARK = " = ? ";
    public static final  String EQUAL_STR_DATE_QUESTION_MARK = " = STR_TO_DATE(?, '%a %b %d %H:%i:%s ICT %Y') ";
    public static final String SIGN_QUESTION_MARK = " ? ";
    public static final String SIGN_COMMA = ",";
    public static final String OPEN_PARENTHESIS = " ( ";
    public static final String CLOSE_PARENTHESIS = " ) ";
    public static final String WHERE_0_EQUAL_0 = " where 0 = 0 ";
    public static final String FROM = " from ";
}
