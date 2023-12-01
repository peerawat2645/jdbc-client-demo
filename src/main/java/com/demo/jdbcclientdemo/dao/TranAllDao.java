package com.demo.jdbcclientdemo.dao;

import com.demo.jdbcclientdemo.domain.TranAll;

import java.util.List;

public interface TranAllDao {

    public List<TranAll> find(TranAll findObject) throws Exception;

    public void insert(List<TranAll> insertObjectList) throws Exception;

}
