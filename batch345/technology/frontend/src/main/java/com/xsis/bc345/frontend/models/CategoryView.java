package com.xsis.bc345.frontend.models;

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
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getCategoryName() {
    return categoryName;
  }
  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
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
  public int getUpdateBy() {
    return updateBy;
  }
  public void setUpdateBy(int updateBy) {
    this.updateBy = updateBy;
  }
  public LocalDateTime getUpdateDate() {
    return updateDate;
  }
  public void setUpdateDate(LocalDateTime updateDate) {
    this.updateDate = updateDate;
  }

  
}
