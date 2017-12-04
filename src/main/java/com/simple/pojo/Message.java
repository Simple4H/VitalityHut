package com.simple.pojo;

import java.util.Date;

public class Message {
    private Integer id;

    private String title;

    private String messges;

    private String anthor;

    private Date createTime;

    private Date updateTime;

    public Message(Integer id, String title, String messges, String anthor, Date createTime, Date updateTime) {
        this.id = id;
        this.title = title;
        this.messges = messges;
        this.anthor = anthor;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Message() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getMessges() {
        return messges;
    }

    public void setMessges(String messges) {
        this.messges = messges == null ? null : messges.trim();
    }

    public String getAnthor() {
        return anthor;
    }

    public void setAnthor(String anthor) {
        this.anthor = anthor == null ? null : anthor.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}