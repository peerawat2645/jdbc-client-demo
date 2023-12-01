package com.demo.jdbcclientdemo.service;

import com.demo.jdbcclientdemo.model.rest.response.GetBookResponse;
import org.springframework.transaction.annotation.Transactional;

public interface BookService {

    @Transactional(rollbackFor = Exception.class)
    public GetBookResponse getAll()throws Exception;
}
