package com.demo.jdbcclientdemo.controller;

import com.demo.jdbcclientdemo.constant.CommonConstant;
import com.demo.jdbcclientdemo.constant.ServiceNameConstant;
import com.demo.jdbcclientdemo.model.rest.response.GetBookResponse;
import com.demo.jdbcclientdemo.service.BookService;
import com.demo.jdbcclientdemo.service.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(ServiceNameConstant.CONTROLLER_PATH_API)
public class BookController extends BookControllerValidator{

    @Autowired
    private LoggerService loggerService;

    @Autowired
    private BookService bookService;

    @PostMapping(ServiceNameConstant.SERVICE_GET_BOOK_All)
    public Object getAll() throws Exception {
        Date startDate = new Date();
        GetBookResponse response;

        loggerService.accessLogger(startDate, new Date(), CommonConstant.SID,
                ServiceNameConstant.SERVICE_GET_BOOK_All + CommonConstant.BEGIN,
                null, null);

        response = bookService.getAll();
        loggerService.accessLogger(startDate, new Date(), ServiceNameConstant.SERVICE_GET_BOOK_All + CommonConstant.END,
               CommonConstant.SID, null, response);
        return response;
    }
}
