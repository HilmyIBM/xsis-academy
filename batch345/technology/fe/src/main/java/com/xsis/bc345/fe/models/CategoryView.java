package com.xsis.bc345.fe.models;

import java.time.LocalDateTime;

public class CategoryView {
    private long id;
    private String categoryName;
    private String description;
    private boolean deleted;
    private int createBy;
    private LocalDateTime createDate;
    private Integer updateBy;
    private LocalDateTime updateDate;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String name) {
        this.categoryName = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isDeleted() {
        return deleted;
    }
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    public int getCreateBy() {
        return createBy;
    }
    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }
    public LocalDateTime getCreateDate() {
        return createDate;
    }
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
    public Integer getUpdateBy() {
        return updateBy;
    }
    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }
    public LocalDateTime getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    
}
