package com.management.property.pojo;

import java.io.Serializable;
import java.util.Date;

public class Unpaid implements Serializable {
    private Integer unpaidId;

    private Integer userId;

    private String userName;

    private String region;

    private String paywhether;

    private Float paymoeny;

    private Date maturitydate;

    private static final long serialVersionUID = 1L;

    public Integer getUnpaidId() {
        return unpaidId;
    }

    public void setUnpaidId(Integer unpaidId) {
        this.unpaidId = unpaidId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPaywhether() {
        return paywhether;
    }

    public void setPaywhether(String paywhether) {
        this.paywhether = paywhether;
    }

    public Float getPaymoeny() {
        return paymoeny;
    }

    public void setPaymoeny(Float paymoeny) {
        this.paymoeny = paymoeny;
    }

    public Date getMaturitydate() {
        return maturitydate;
    }

    public void setMaturitydate(Date maturitydate) {
        this.maturitydate = maturitydate;
    }
}