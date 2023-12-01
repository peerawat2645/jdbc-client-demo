package com.demo.jdbcclientdemo.model.rest.response;

import com.demo.jdbcclientdemo.domain.Book;
import com.demo.jdbcclientdemo.model.rest.BaseResponse;

import java.util.List;

public class GetBookResponse extends BaseResponse {
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GetBookResponse{books=").append(books);
        sb.append('}');
        return sb.toString();
    }
}
