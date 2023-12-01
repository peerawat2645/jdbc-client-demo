package com.demo.jdbcclientdemo.dao;

import com.demo.jdbcclientdemo.domain.TranBook;

import java.math.BigInteger;
import java.util.List;

public interface TranBookDao {
    public List<TranBook> find(TranBook findObject) throws Exception;

    public void insert(List<TranBook> insertObjectList) throws Exception;

    public TranBook findById(BigInteger id) throws Exception;

}
