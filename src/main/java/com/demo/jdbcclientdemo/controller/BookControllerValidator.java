package com.demo.jdbcclientdemo.controller;

import com.demo.jdbcclientdemo.constant.ExceptionConstant;
import com.demo.jdbcclientdemo.exceptions.ServiceValidation;
import com.demo.jdbcclientdemo.model.rest.request.InsertBookRequest;
import com.demo.jdbcclientdemo.model.rest.request.UpdateBookRequest;
import com.demo.jdbcclientdemo.util.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class BookControllerValidator {

    @Autowired
    private ValidatorUtils validator;

    protected void insertBookValidation(InsertBookRequest request) throws ServiceValidation {

        if (null != request.getName() && !validator.validateLength(request.getName(), 200)) {
            throw new ServiceValidation(ExceptionConstant.ERROR_CODE_BOOK_NAME_IS_INVALID);
        }
        if (null != request.getTitle() && !validator.validateLength(request.getTitle(), 50)) {
            throw new ServiceValidation(ExceptionConstant.ERROR_CODE_BOOK_TITLE_IS_INVALID);
        }
    }

    protected void updateBookValidation(UpdateBookRequest request) throws ServiceValidation {

        if (null != request.getBookID() || !validator.validateLength(request.getBookID().toString(), 20)) {
            throw new ServiceValidation(ExceptionConstant.ERROR_CODE_BOOK_ID_IS_INVALID);
        }
        if (null != request.getName() && !validator.validateLength(request.getName(), 200)) {
            throw new ServiceValidation(ExceptionConstant.ERROR_CODE_BOOK_NAME_IS_INVALID);
        }
        if (null != request.getTitle() && !validator.validateLength(request.getTitle(), 50)) {
            throw new ServiceValidation(ExceptionConstant.ERROR_CODE_BOOK_TITLE_IS_INVALID);
        }
    }
}
