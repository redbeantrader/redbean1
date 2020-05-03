package com.management.property.pojo;

import java.io.Serializable;

public class Costs implements Serializable {
    private Integer costsId;

    private String costsType;

    private static final long serialVersionUID = 1L;

    public Integer getCostsId() {
        return costsId;
    }

    public void setCostsId(Integer costsId) {
        this.costsId = costsId;
    }

    public String getCostsType() {
        return costsType;
    }

    public void setCostsType(String costsType) {
        this.costsType = costsType;
    }
}