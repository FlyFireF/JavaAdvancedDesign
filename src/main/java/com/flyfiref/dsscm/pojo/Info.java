package com.flyfiref.dsscm.pojo;

import java.util.Date;

public class Info {

    private Integer id;
    private String infoCode;
    private Date creationDate;
    private String content;
    private String providerName;

    public Info() {
        super();
    }

    public Info(int id, String infoCode, Date creationDate, String content, String providerName) {
        this.id = id;
        this.infoCode = infoCode;
        this.creationDate = creationDate;
        this.content = content;
        this.providerName = providerName;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfoCode() {
        return infoCode;
    }

    public void setInfoCode(String infoCode) {
        this.infoCode = infoCode;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
}
