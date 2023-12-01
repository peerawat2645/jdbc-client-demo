/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.jdbcclientdemo.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.demo.jdbcclientdemo.constant.CommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author Anuwat_K
 */
@Component
public class LoggerService {

    private static final String START_SQUARE_BRACKETS = "[";
    private static final String END_SQUARE_BRACKETS = "]";
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");

    private final Logger accessLogger = LoggerFactory.getLogger(CommonConstant.ACCESS_LOG);
    private final Logger externalLogger = LoggerFactory.getLogger(CommonConstant.EXTERNAL_LOG);
    private final Logger systemLogger = LoggerFactory.getLogger(CommonConstant.SYSTEM_LOG);
    private final Logger errorLogger = LoggerFactory.getLogger(CommonConstant.ERROR_LOG);

    public void accessLogger(Date startDate, Date endDate, String refID, String result, Object input, Object output) {
        StringBuilder sb = new StringBuilder();

        // ReferenceID
        sb.append("RefID=");
        sb.append(START_SQUARE_BRACKETS);
        sb.append(refID == null ? "" : refID);
        sb.append(", ");

        //Time
        sb.append(dateFormat.format(startDate));
        sb.append(", ");
        sb.append(dateFormat.format(endDate));
        sb.append(", ");
        sb.append((endDate.getTime() - startDate.getTime()));
        sb.append(END_SQUARE_BRACKETS);
        sb.append(" ");

        //Result
        sb.append("Result=");
        sb.append(START_SQUARE_BRACKETS);
        sb.append(result == null ? "" : result);
        sb.append(END_SQUARE_BRACKETS);
        sb.append(" ");

        //Resquest
        sb.append("Request=");
        sb.append(START_SQUARE_BRACKETS);
        sb.append(input == null ? "" : input.toString());
        sb.append(END_SQUARE_BRACKETS);
        sb.append(" ");

        // Response
        sb.append("Response=");
        sb.append(START_SQUARE_BRACKETS);
        sb.append(output == null ? "" : output.toString());
        sb.append(END_SQUARE_BRACKETS);

        String log = sb.toString();

        accessLogger.trace(log);
    }

    public void accessExternalLogger(Date startDate, Date endDate, String refID, String result, Object input, Object output) {
        StringBuilder sb = new StringBuilder();

        // ReferenceID
        sb.append("RefID=");
        sb.append(START_SQUARE_BRACKETS);
        sb.append(refID == null ? "" : refID);
        sb.append(", ");

        //Time
        sb.append(dateFormat.format(startDate));
        sb.append(", ");
        sb.append(dateFormat.format(endDate));
        sb.append(", ");
        sb.append((endDate.getTime() - startDate.getTime()));
        sb.append(END_SQUARE_BRACKETS);
        sb.append(" ");

        //Result
        sb.append("Result=");
        sb.append(START_SQUARE_BRACKETS);
        sb.append(result == null ? "" : result);
        sb.append(END_SQUARE_BRACKETS);
        sb.append(" ");

        //Resquest
        sb.append("Request=");
        sb.append(START_SQUARE_BRACKETS);
        sb.append(input == null ? "" : input.toString());
        sb.append(END_SQUARE_BRACKETS);
        sb.append(" ");

        // Response
        sb.append("Response=");
        sb.append(START_SQUARE_BRACKETS);
        sb.append(output == null ? "" : output.toString());
        sb.append(END_SQUARE_BRACKETS);

        String log = sb.toString();

        externalLogger.trace(log);
    }

    public void systemLogger(String refID, String message, Object result) {
        StringBuilder sb = new StringBuilder();

        // ReferenceID
        sb.append("RefID=[");
        sb.append(refID == null ? "" : refID);
        sb.append("]");
        sb.append(" ");

        //Result
        sb.append("Message=[");
        sb.append(message == null ? "" : message);
        sb.append("]");
        sb.append(" ");

        //Object
        sb.append("Result=[");
        sb.append(result == null ? "" : result.toString());
        sb.append("]");
        sb.append(" ");

        String log = sb.toString();

        systemLogger.trace(log);
    }

    public void printStackTraceToErrorLog(String refID, String exceptionClassName, Throwable e) {
        StringBuilder str = new StringBuilder();

        str.append("[RefID: ").append(refID).append("] ");
        str.append("[Type: ").append(exceptionClassName).append("] ");
        str.append("[MessageCode: ").append(e.getMessage()).append("] ");

        StackTraceElement elements[] = e.getStackTrace();
        if (elements != null && elements.length > 0) {
            str.append(e.toString());
            for (int i = 0, n = elements.length; i < n; i++) {
                str.append(" at ");
                str.append(elements[i].getClassName()).append(" (").append(elements[i].getMethodName())
                        .append(":").append(elements[i].getLineNumber()).append(")\n");
            }

        } else {
            str.append(e.toString());
        }

        if (e.getCause() != null) {
            StackTraceElement elements2[] = e.getCause().getStackTrace();
            if (elements2 != null && elements2.length != 0) {
                str.append(" Caused by : ");
                str.append(e.getCause().toString());
                str.append(" at ");
                for (StackTraceElement elements21 : elements2) {
                    str
                            .append(" at ")
                            .append(elements21.getClassName())
                            .append("(").append(elements21.getMethodName())
                            .append(":").append(elements21.getLineNumber()).append(")\n");
                }

            } else {
                str.append(e.getCause().toString());
            }
        }
        String log = str.toString();
        errorLogger.error(log);
    }

}
