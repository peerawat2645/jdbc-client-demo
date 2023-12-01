package com.demo.jdbcclientdemo.dao;

import com.demo.jdbcclientdemo.domain.Book;

import java.math.BigInteger;
import java.util.List;

public interface BookDao {
    public List<Book> findAll() throws Exception;
    public List<Book> find(Book findObject) throws Exception;
    public Book findById(BigInteger id) throws Exception;
    public Book insert(Book insertObject) throws Exception;
    public Book update(Book updateObject) throws Exception;
    public void delete(BigInteger id) throws Exception;

}
