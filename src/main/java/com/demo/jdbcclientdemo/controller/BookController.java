package com.demo.jdbcclientdemo.controller;

import com.demo.jdbcclientdemo.constant.CommonConstant;
import com.demo.jdbcclientdemo.constant.ServiceNameConstant;
import com.demo.jdbcclientdemo.model.rest.BaseResponse;
import com.demo.jdbcclientdemo.model.rest.request.InsertBookRequest;
import com.demo.jdbcclientdemo.model.rest.request.UpdateBookRequest;
import com.demo.jdbcclientdemo.model.rest.response.GetBookPageableResponse;
import com.demo.jdbcclientdemo.model.rest.response.GetBookResponse;
import com.demo.jdbcclientdemo.service.BookService;
import com.demo.jdbcclientdemo.service.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Date;

@RestController
@CrossOrigin("*")
@RequestMapping(ServiceNameConstant.CONTROLLER_PATH_API)
public class BookController extends BookControllerValidator {

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

    @PostMapping(ServiceNameConstant.SERVICE_GET_BOOK_All_PAGE)
    public Object getAllByPageable(@PathVariable("page") int page) throws Exception {
        Date startDate = new Date();
        GetBookPageableResponse response;

        loggerService.accessLogger(startDate, new Date(), CommonConstant.SID,
                ServiceNameConstant.SERVICE_GET_BOOK_All_PAGE + CommonConstant.BEGIN,
                null, null);

        response = bookService.getAll(page);
        loggerService.accessLogger(startDate, new Date(), ServiceNameConstant.SERVICE_GET_BOOK_All_PAGE + CommonConstant.END,
                CommonConstant.SID, null, response);
        return response;
    }

    @PostMapping(ServiceNameConstant.SERVICE_SEARCH_BOOK)
    public Object getAllByPageable(@PathVariable(value = "name", required = false) String name,
                                   @PathVariable("page") int page) throws Exception {
        Date startDate = new Date();
        GetBookPageableResponse response;

        loggerService.accessLogger(startDate, new Date(), CommonConstant.SID,
                ServiceNameConstant.SERVICE_SEARCH_BOOK + CommonConstant.BEGIN,
                null, null);

        String url;
        if (name != null && !name.isEmpty()) {
            response = bookService.getAllByName(name, page);
        } else {
            response = bookService.getAll(page);
        }

        loggerService.accessLogger(startDate, new Date(), ServiceNameConstant.SERVICE_SEARCH_BOOK + CommonConstant.END,
                CommonConstant.SID, null, response);
        return response;
    }


    @PostMapping(ServiceNameConstant.SERVICE_INSERT_BOOK)
    public Object insert(@RequestBody InsertBookRequest book) throws Exception {
        Date startDate = new Date();
        GetBookResponse response;

        loggerService.accessLogger(startDate, new Date(), CommonConstant.SID,
                ServiceNameConstant.SERVICE_INSERT_BOOK + CommonConstant.BEGIN,
                book, null);

        insertBookValidation(book);

        response = bookService.insert(book);
        loggerService.accessLogger(startDate, new Date(), ServiceNameConstant.SERVICE_INSERT_BOOK + CommonConstant.END,
                CommonConstant.SID, book, response);
        return response;
    }

    @PostMapping(ServiceNameConstant.SERVICE_UPDATE_BOOK)
    public Object update(@RequestBody UpdateBookRequest book) throws Exception {
        Date startDate = new Date();
        GetBookResponse response;

        loggerService.accessLogger(startDate, new Date(), CommonConstant.SID,
                ServiceNameConstant.SERVICE_UPDATE_BOOK + CommonConstant.BEGIN,
                book, null);

        updateBookValidation(book);

        response = bookService.update(book);
        loggerService.accessLogger(startDate, new Date(), ServiceNameConstant.SERVICE_UPDATE_BOOK + CommonConstant.END,
                CommonConstant.SID, book, response);
        return response;
    }

    @PostMapping(ServiceNameConstant.SERVICE_FIND_BOOK)
    public Object find(@PathVariable("id") BigInteger id) throws Exception {
        Date startDate = new Date();
        GetBookResponse response;

        loggerService.accessLogger(startDate, new Date(), CommonConstant.SID,
                ServiceNameConstant.SERVICE_FIND_BOOK + CommonConstant.BEGIN,
                id, null);

        response = bookService.findById(id);
        loggerService.accessLogger(startDate, new Date(), ServiceNameConstant.SERVICE_FIND_BOOK + CommonConstant.END,
                CommonConstant.SID, id, response);
        return response;
    }

    @PostMapping(ServiceNameConstant.SERVICE_DELETE_BOOK)
    public Object delete(@PathVariable("id") BigInteger id) throws Exception {
        Date startDate = new Date();
        BaseResponse response;

        loggerService.accessLogger(startDate, new Date(), CommonConstant.SID,
                ServiceNameConstant.SERVICE_DELETE_BOOK + CommonConstant.BEGIN,
                id, null);

        response = bookService.deleteById(id);
        loggerService.accessLogger(startDate, new Date(), ServiceNameConstant.SERVICE_DELETE_BOOK + CommonConstant.END,
                CommonConstant.SID, id, response);
        return response;
    }
}
