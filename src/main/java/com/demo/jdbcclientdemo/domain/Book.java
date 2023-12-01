package com.demo.jdbcclientdemo.domain;

import java.math.BigInteger;

public class Book extends BaseDomain {

    private BigInteger bookID;
    private String name;
    private String title;
    private BigInteger tranIDGenerate;
    private BigInteger tranIDVerify;
    private String tranStatusGroup;
    private String tranStatusCode;

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

    public BigInteger getTranIDGenerate() {
        return tranIDGenerate;
    }

    public void setTranIDGenerate(BigInteger tranIDGenerate) {
        this.tranIDGenerate = tranIDGenerate;
    }

    public BigInteger getTranIDVerify() {
        return tranIDVerify;
    }

    public void setTranIDVerify(BigInteger tranIDVerify) {
        this.tranIDVerify = tranIDVerify;
    }

    public String getTranStatusGroup() {
        return tranStatusGroup;
    }

    public void setTranStatusGroup(String tranStatusGroup) {
        this.tranStatusGroup = tranStatusGroup;
    }

    public String getTranStatusCode() {
        return tranStatusCode;
    }

    public void setTranStatusCode(String tranStatusCode) {
        this.tranStatusCode = tranStatusCode;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Book{");
        sb.append("bookID=").append(bookID);
        sb.append(", name=").append(name);
        sb.append(", title=").append(title);
        sb.append(", tranIDGenerate=").append(tranIDGenerate);
        sb.append(", tranIDVerify=").append(tranIDVerify);
        sb.append(", tranStatusGroup=").append(tranStatusGroup);
        sb.append(", tranStatusCode=").append(tranStatusCode);
        sb.append('}');
        return sb.toString();
    }
}
