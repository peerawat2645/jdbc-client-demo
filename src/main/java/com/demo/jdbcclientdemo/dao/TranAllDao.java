package com.demo.jdbcclientdemo.dao;

import com.demo.jdbcclientdemo.domain.TranAll;

import java.math.BigInteger;
import java.util.List;

public interface TranAllDao {

    public List<TranAll> find(TranAll findObject) throws Exception;

    public BigInteger insert(List<TranAll> insertObjectList) throws Exception;

    public TranAll findById(BigInteger id) throws Exception;

}
