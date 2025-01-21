package com.xsis.bc345.fe.models;

import java.time.LocalDateTime;

public class CategoryView {
    
    private int id;
    private String categoryName; 
    private String description;
    private boolean deleted;
    private int createBy;
    private LocalDateTime createDate;
    private int updateBy;
    private LocalDateTime updateDate;

    public String getDescription() {
        return description;
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
    
    public void setDescription(String description) {
        this.description = description;
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
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", categoryName='" + getCategoryName() + "'" +
            ", description='" + getDescription() + "'" +
            ", deleted='" + isDeleted() + "'" +
            ", createBy='" + getCreateBy() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", updateBy='" + getUpdateBy() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            "}";
    }


}
