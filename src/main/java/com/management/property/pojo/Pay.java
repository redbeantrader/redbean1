package com.management.property.pojo;

import java.io.Serializable;
import java.util.Date;

public class Pay implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer costsId;

    private String payWhether;

    private Integer payMonth;

    private Float payMoeny;

    private Date payDate;

    private Date maturityDate;

    private String userName;

    private String costsType;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCostsType() {
        return costsType;
    }

    public void setCostsType(String costsType) {
        this.costsType = costsType;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCostsId() {
        return costsId;
    }

    public void setCostsId(Integer costsId) {
        this.costsId = costsId;
    }

    public String getPayWhether() {
        return payWhether;
    }

    public void setPayWhether(String payWhether) {
        this.payWhether = payWhether;
    }

    public Integer getPayMonth() {
        return payMonth;
    }

    public void setPayMonth(Integer payMonth) {
        this.payMonth = payMonth;
    }

    public Float getPayMoeny() {
        return payMoeny;
    }

    public void setPayMoeny(Float payMoeny) {
        this.payMoeny = payMoeny;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Date getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(Date maturityDate) {
        this.maturityDate = maturityDate;
    }
}