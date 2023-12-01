package com.demo.jdbcclientdemo.dao;

import com.demo.jdbcclientdemo.domain.TranAll;
import com.demo.jdbcclientdemo.domain.TranBook;

import java.math.BigInteger;
import java.util.List;

public interface TranAllDao {

    public List<TranAll> find(TranAll findObject) throws Exception;

    public void insert(List<TranAll> insertObjectList) throws Exception;

    public TranAll findById(BigInteger id) throws Exception;

}
