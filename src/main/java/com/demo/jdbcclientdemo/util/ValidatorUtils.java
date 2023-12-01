package com.demo.jdbcclientdemo.util;

import org.springframework.stereotype.Component;

import static com.demo.jdbcclientdemo.util.StringUtils.isNotEmptyOrNull;
import java.math.BigInteger;
import java.util.regex.Pattern;

@Component
public class ValidatorUtils {
    public static final String APPLICATION_VALUE_TYPE_INTEGER = "Integer";
    public static final String APPLICATION_VALUE_TYPE_FLOAT = "Float";
    public static final String APPLICATION_VALUE_TYPE_DOUBLE = "Double";
    public static final String APPLICATION_VALUE_TYPE_LONG = "Long";
    public static final String APPLICATION_VALUE_TYPE_BIGINTEGER = "BigInteger";
    public static final String APPLICATION_VALUE_TYPE_BOOLEAN = "Boolean";
    public static final String APPLICATION_VALUE_MOBILE_PATTERN = "^0([0-9])\\d{8}$";
    public static final String APPLICATION_VALUE_RGB_PATTERN = "#[0-9a-f]{6}";
    public static final String APPLICATION_VALUE_MOBILE_MASK_PATTERN = "^([x]{3}[- .]?){2}\\d{4}$";

    public static boolean validateMandatory(String str) {
        return !(null == str || "".equals(str.trim()) || str.trim().length() == 0 || "null".equalsIgnoreCase(str));
    }

    public static boolean validatePattern(String input, String regex) {
        if (isNotEmptyOrNull(regex)) {
            Pattern pattern = Pattern.compile(regex);
            if (!pattern.matcher(input).matches()) {
                return false;
            }
        }
        return true;
    }

    public static boolean validateLength(String input, int maxLength) {
        return input != null && (input.length() <= maxLength);
    }

    public static boolean validateLengthEqual(String input, int maxLength) {
        return input != null && (input.length() == maxLength);
    }

    public static boolean validateType(String input, String validateType) {
        try {
            if (isNotEmptyOrNull(input)) {
                if (APPLICATION_VALUE_TYPE_INTEGER.equalsIgnoreCase(validateType)) {
                    Integer.parseInt(input);

                } else if (APPLICATION_VALUE_TYPE_FLOAT.equalsIgnoreCase(validateType)) {
                    Float.parseFloat(input);

                } else if (APPLICATION_VALUE_TYPE_DOUBLE.equalsIgnoreCase(validateType)) {
                    Double.parseDouble(input);

                } else if (APPLICATION_VALUE_TYPE_LONG.equalsIgnoreCase(validateType)) {
                    Long.parseLong(input);

                } else if (APPLICATION_VALUE_TYPE_BIGINTEGER.equalsIgnoreCase(validateType)) {
                    BigInteger.valueOf(Long.parseLong(input));

                } else if (APPLICATION_VALUE_TYPE_BOOLEAN.equalsIgnoreCase(validateType)) {
                    return "true".equalsIgnoreCase(input) || "false".equalsIgnoreCase(input);
                }
            }

            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    public static boolean validatePosition(String input, String pattern, int length) {
        return (input.indexOf(pattern, length) == length);
    }
}
