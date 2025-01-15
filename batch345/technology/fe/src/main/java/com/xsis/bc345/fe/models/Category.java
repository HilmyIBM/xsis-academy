package com.xsis.bc345.fe.models;

import java.time.LocalDateTime;

public class Category {
    
    private String name; 
    private String descriptions;
    private boolean deleted;
    private int id;
    
    private int createBy;
    private LocalDateTime createDate;
    private int updateBy;
    private LocalDateTime updateDate;

    public String getDescriptions() {
        return descriptions;
    }
    public boolean isDeleted() {
        return deleted;
    }
    public int getCreateBy() {
        return createBy;
    }
    public LocalDateTime getCreateDate() {
        return createDate;
    }
    public int getUpdateBy() {
        return updateBy;
    }
    public LocalDateTime getUpdateDate() {
        return updateDate;
    }
    
    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }
    public void setDeleted(boolean Deleted) {
        this.deleted = Deleted;
    }
    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }
    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
