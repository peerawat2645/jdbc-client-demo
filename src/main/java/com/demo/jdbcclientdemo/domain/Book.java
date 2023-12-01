package com.demo.jdbcclientdemo.domain;

import com.demo.jdbcclientdemo.domain.BaseDomain;

import java.math.BigInteger;

public class Book extends BaseDomain {

    private BigInteger bookID;
    private String name;
    private String title;
    private BigInteger tran_id_generate;
    private BigInteger tran_id_verify;
    private String tran_status_group;
    private String tran_status_code;

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

    public BigInteger getTran_id_generate() {
        return tran_id_generate;
    }

    public void setTran_id_generate(BigInteger tran_id_generate) {
        this.tran_id_generate = tran_id_generate;
    }

    public BigInteger getTran_id_verify() {
        return tran_id_verify;
    }

    public void setTran_id_verify(BigInteger tran_id_verify) {
        this.tran_id_verify = tran_id_verify;
    }

    public String getTran_status_group() {
        return tran_status_group;
    }

    public void setTran_status_group(String tran_status_group) {
        this.tran_status_group = tran_status_group;
    }

    public String getTran_status_code() {
        return tran_status_code;
    }

    public void setTran_status_code(String tran_status_code) {
        this.tran_status_code = tran_status_code;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Book{");
        sb.append("bookID=").append(bookID);
        sb.append(", name=").append(name);
        sb.append(", title=").append(title);
        sb.append(", tranIDGenerate=").append(tran_id_generate);
        sb.append(", tranIDVerify=").append(tran_id_verify);
        sb.append(", tranStatusGroup=").append(tran_status_group);
        sb.append(", tranStatusCode=").append(tran_status_code);
        sb.append('}');
        return sb.toString();
    }
}
