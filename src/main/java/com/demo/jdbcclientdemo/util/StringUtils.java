package com.demo.jdbcclientdemo.util;

public class StringUtils {

    private StringUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isNotEmptyOrNull(String str) {
        return !(null == str || str.trim().isEmpty() || "null".equalsIgnoreCase(str));
    }
}

