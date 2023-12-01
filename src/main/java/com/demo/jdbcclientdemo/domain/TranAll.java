package com.demo.jdbcclientdemo.domain;

import com.demo.jdbcclientdemo.domain.BaseDomain;

import java.math.BigInteger;

public class TranAll extends BaseDomain {

    private BigInteger tranID;
    private String tranGroup;
    private String tranCode;
    private BigInteger tranRefID;
    private String sid;

    public BigInteger getTranID() {
        return tranID;
    }

    public void setTranID(BigInteger tranID) {
        this.tranID = tranID;
    }

    public String getTranGroup() {
        return tranGroup;
    }

    public void setTranGroup(String tranGroup) {
        this.tranGroup = tranGroup;
    }

    public String getTranCode() {
        return tranCode;
    }

    public void setTranCode(String tranCode) {
        this.tranCode = tranCode;
    }

    public BigInteger getTranRefID() {
        return tranRefID;
    }

    public void setTranRefID(BigInteger tranRefID) {
        this.tranRefID = tranRefID;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("tranAll{");
        sb.append("tranID=").append(tranID);
        sb.append(", tranGroup=").append(tranGroup);
        sb.append(", tranCode=").append(tranCode);
        sb.append(", tranRefID=").append(tranRefID);
        sb.append(", sid=").append(sid);

        sb.append('}');
        return sb.toString();
    }
}
