package com.demo.jdbcclientdemo.service.impl;

import com.demo.jdbcclientdemo.constant.CommonConstant;
import com.demo.jdbcclientdemo.constant.DateTimeConstant;
import com.demo.jdbcclientdemo.constant.ExceptionConstant;
import com.demo.jdbcclientdemo.dao.BookDao;
import com.demo.jdbcclientdemo.domain.Book;
import com.demo.jdbcclientdemo.exceptions.ServiceException;
import com.demo.jdbcclientdemo.model.rest.response.GetBookResponse;
import com.demo.jdbcclientdemo.service.BookService;
import com.demo.jdbcclientdemo.service.LoggerService;
import com.demo.jdbcclientdemo.util.DateUtil;
import net.sf.jsqlparser.util.validation.metadata.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private LoggerService loggerService;
    @Autowired
    private BookDao bookDao;
    @Override
    public GetBookResponse getAll() throws Exception {
        GetBookResponse response = new GetBookResponse();
        Date currentDate = new Date();
        try {
            List<Book> bookListDB = bookDao.findAll();
            if (bookListDB != null && !bookListDB.isEmpty()) {
                response.setBooks(bookListDB);
            }
            System.out.println(response);

            response.setStatusCode(CommonConstant.RESPONSE_CODE_SUCCESS);
            response.setStatus(CommonConstant.SUCCESS_CODE);
            response.setTimestamp(DateUtil.formatDatetoString(currentDate, DateTimeConstant.DATE_TIME_FORMAT_SLASH, null));

        } catch (DataAccessException e) {
            loggerService.printStackTraceToErrorLog(CommonConstant.SID, e.getClass().getName(), e);
            throw new DatabaseException(ExceptionConstant.DATABASE_EXCEPTION);

        } catch (Exception e) {
            loggerService.printStackTraceToErrorLog(CommonConstant.SID, e.getClass().getSimpleName(), e);
            throw new ServiceException(ExceptionConstant.SERVICE_EXCEPTION);
        }
        return response;
    }

}
