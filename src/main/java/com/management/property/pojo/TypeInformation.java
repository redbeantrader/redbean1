package com.management.property.pojo;

import java.io.Serializable;

public class TypeInformation implements Serializable {
    private Integer id;

    private String type;

    private Float area;

    private String region;

    private String structure;

    private String sell;

    private String number;

    private Integer rent;

    private Float water;

    private Float electricity;

    private Float gas;

    private Integer internet;

    private Integer wcut;

    private Integer ecut;

    private Integer gcut;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getRent() {
        return rent;
    }

    public void setRent(Integer rent) {
        this.rent = rent;
    }

    public Float getWater() {
        return water;
    }

    public void setWater(Float water) {
        this.water = water;
    }

    public Float getElectricity() {
        return electricity;
    }

    public void setElectricity(Float electricity) {
        this.electricity = electricity;
    }

    public Float getGas() {
        return gas;
    }

    public void setGas(Float gas) {
        this.gas = gas;
    }

    public Integer getInternet() {
        return internet;
    }

    public void setInternet(Integer internet) {
        this.internet = internet;
    }

    public Integer getWcut() {
        return wcut;
    }

    public void setWcut(Integer wcut) {
        this.wcut = wcut;
    }

    public Integer getEcut() {
        return ecut;
    }

    public void setEcut(Integer ecut) {
        this.ecut = ecut;
    }

    public Integer getGcut() {
        return gcut;
    }

    public void setGcut(Integer gcut) {
        this.gcut = gcut;
    }
}