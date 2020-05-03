package com.management.property.pojo;

import java.io.Serializable;
import java.util.Date;

public class RepairAnnal implements Serializable {
    private Integer id;

    private Integer userId;

    private String requestType;

    private Date requestDate;

    private String userName;

    private String repairStatus;

    private String requestLocation;

    private String requestPhone;

    private String repairName;

    private String repairPhone;

    private Date repairDate;

    private Integer repairId;

    public Integer getRepairId() {
        return repairId;
    }

    public void setRepairId(Integer repairId) {
        this.repairId = repairId;
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

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRepairStatus() {
        return repairStatus;
    }

    public void setRepairStatus(String repairStatus) {
        this.repairStatus = repairStatus;
    }

    public String getRequestLocation() {
        return requestLocation;
    }

    public void setRequestLocation(String requestLocation) {
        this.requestLocation = requestLocation;
    }

    public String getRequestPhone() {
        return requestPhone;
    }

    public void setRequestPhone(String requestPhone) {
        this.requestPhone = requestPhone;
    }

    public String getRepairName() {
        return repairName;
    }

    public void setRepairName(String repairName) {
        this.repairName = repairName;
    }

    public String getRepairPhone() {
        return repairPhone;
    }

    public void setRepairPhone(String repairPhone) {
        this.repairPhone = repairPhone;
    }

    public Date getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(Date repairDate) {
        this.repairDate = repairDate;
    }
}