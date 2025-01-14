package com.xsis.bc345.fe.models;

import java.time.LocalDateTime;

public class Category {
    private long id;
    private String name;
    private String desc;

    private boolean deleted;

    private int createBy;
    private LocalDateTime createdDate;
    private int updateBy;
    private LocalDateTime updateDate;

    public boolean isDeleted() {
        return deleted;
    }
    public String getName() {
        return name;
    }
    public String getDesc() {
        return desc;
    }
    public int getCreateBy() {
        return createBy;
    }
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }public long getId() {
        return id;
    }public int getUpdateBy() {
        return updateBy;
    }
    public LocalDateTime getUpdateDate() {
        return updateDate;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }
    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
    
}
