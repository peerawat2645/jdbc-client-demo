package com.demo.jdbcclientdemo.util;

import com.demo.jdbcclientdemo.exceptions.ServiceException;
import com.demo.jdbcclientdemo.exceptions.ServiceValidation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    private DateUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static final Locale DEFAULT_LOCALE = new Locale("en", "EN");
    public static final Locale THAI_LOCALE = new Locale("th", "TH");
    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    public static Date parseStringtoDate(String dateString, String pattern, String language) throws ServiceException, ParseException {
        SimpleDateFormat format;

        if (StringUtils.isNotEmptyOrNull(dateString) && StringUtils.isNotEmptyOrNull(pattern)) {
            format = new SimpleDateFormat(pattern, DEFAULT_LOCALE);

            return format.parse(dateString);

        } else {
            return null;
        }
    }

    public static String formatDatetoString(Date date, String pattern, String language) {
        SimpleDateFormat format;

        if (date != null && StringUtils.isNotEmptyOrNull(pattern)) {
            format = new SimpleDateFormat(pattern, DEFAULT_LOCALE);

            return format.format(date);

        } else {
            return null;
        }

    }

    public static boolean validateDate(String dateString, String pattern, String language) throws ServiceValidation {
        SimpleDateFormat format;
        boolean isDateValid = false;

        try {
            if (StringUtils.isNotEmptyOrNull(dateString) && StringUtils.isNotEmptyOrNull(pattern)) {
                format = new SimpleDateFormat(pattern, DEFAULT_LOCALE);

                format.parse(dateString);
                isDateValid = true;
            }

        } catch (ParseException e) {
            isDateValid = false;
        }

        return isDateValid;
    }

    public static String getDateStringDiff(String dateStr1, String dateStr2, String pattern)
            throws ServiceException, ParseException {

        Date date1 = parseStringtoDate(dateStr1, pattern, null);
        Date date2 = parseStringtoDate(dateStr2, pattern, null);

        long diffInMillies = 0;
        if (date1 != null && date2 != null) {
            diffInMillies = date2.getTime() - date1.getTime();
        }
        return String.valueOf(diffInMillies);
    }
}
