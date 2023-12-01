package com.demo.jdbcclientdemo.model.rest.response;

import com.demo.jdbcclientdemo.domain.Book;
import org.springframework.data.domain.Page;

public class GetBookPageableResponse {

    private Page<Book> books;

    public Page<Book> getBooks() {
        return books;
    }

    public void setBooks(Page<Book> books) {
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
