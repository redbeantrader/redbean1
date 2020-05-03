package com.management.property.pojo;

import java.io.Serializable;
import java.util.Date;

public class Text implements Serializable {
    private Integer id;

    private String type;

    private Date date;

    private String status;

    private String proposePlace;

    private String textContent;

    private String title;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProposePlace() {
        return proposePlace;
    }

    public void setProposePlace(String proposePlace) {
        this.proposePlace = proposePlace;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}