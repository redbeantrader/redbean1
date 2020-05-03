package com.management.property.pojo;

import java.io.Serializable;
import java.util.Date;

public class MessageCenter implements Serializable {
    private Integer messageId;

    private String messageType;

    private String messageStatus;

    private String messageContent;

    private Date messageTime;

    private Integer promulgatorId;

    private Integer userId;

    private String title;

    private static final long serialVersionUID = 1L;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Date getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Date messageTime) {
        this.messageTime = messageTime;
    }

    public Integer getPromulgatorId() {
        return promulgatorId;
    }

    public void setPromulgatorId(Integer promulgatorId) {
        this.promulgatorId = promulgatorId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}