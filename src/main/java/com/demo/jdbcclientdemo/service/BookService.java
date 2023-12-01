package com.demo.jdbcclientdemo.service;

import com.demo.jdbcclientdemo.model.rest.BaseResponse;
import com.demo.jdbcclientdemo.model.rest.request.InsertBookRequest;
import com.demo.jdbcclientdemo.model.rest.request.UpdateBookRequest;
import com.demo.jdbcclientdemo.model.rest.response.GetBookPageableResponse;
import com.demo.jdbcclientdemo.model.rest.response.GetBookResponse;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

public interface BookService {

    @Transactional(rollbackFor = Exception.class)
    public GetBookResponse getAll() throws Exception;

    @Transactional(rollbackFor = Exception.class)
    public GetBookPageableResponse getAll(int page) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    public GetBookPageableResponse getAllByName(String name, int page) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    public GetBookResponse insert(InsertBookRequest book) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    public GetBookResponse update(UpdateBookRequest book) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    public GetBookResponse findById(BigInteger bookID) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    public BaseResponse deleteById(BigInteger bookID) throws Exception;
}
