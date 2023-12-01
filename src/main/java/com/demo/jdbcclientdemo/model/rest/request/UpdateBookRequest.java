package com.demo.jdbcclientdemo.model.rest.request;

import java.math.BigInteger;

public class UpdateBookRequest {

    private BigInteger bookID;
    private String name;
    private String title;

    public BigInteger getBookID() {
        return bookID;
    }

    public void setBookID(BigInteger bookID) {
        this.bookID = bookID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Book{");
        sb.append("bookID=").append(bookID);
        sb.append(", name=").append(name);
        sb.append(", title=").append(title);
        sb.append('}');
        return sb.toString();
    }
}
