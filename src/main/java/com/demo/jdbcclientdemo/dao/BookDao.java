package com.demo.jdbcclientdemo.dao;

import com.demo.jdbcclientdemo.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigInteger;
import java.util.List;

public interface BookDao {
    public List<Book> findAll() throws Exception;

    public Page<Book> findAll(Pageable pageable) throws Exception;

    public Page<Book> findAllByName(String name, Pageable pageable) throws Exception;

    public List<Book> find(Book findObject) throws Exception;

    public Book findById(BigInteger id) throws Exception;

    public BigInteger insert(Book insertObject) throws Exception;

    public void update(Book updateObject) throws Exception;

    public void deleteById(BigInteger id) throws Exception;

}
