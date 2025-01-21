package com.xsis.bc345.frontend.models;

import java.sql.Timestamp;

public class VariantView {
  
  private int id;
  private int categoryId;
  private String categoryName;
  private String name;
  private String description;
  private boolean deleted;
  private int createBy;
  private Timestamp createDate;
  private int updateBy;
  private Timestamp updateDate;

  
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public int getCategoryId() {
    return categoryId;
  }
  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }
  public String getCategoryName() {
    return categoryName;
  }
  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
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
  public Timestamp getCreateDate() {
    return createDate;
  }
  public void setCreateDate(Timestamp createDate) {
    this.createDate = createDate;
  }
  public int getUpdateBy() {
    return updateBy;
  }
  public void setUpdateBy(int updateBy) {
    this.updateBy = updateBy;
  }
  public Timestamp getUpdateDate() {
    return updateDate;
  }
  public void setUpdateDate(Timestamp updateDate) {
    this.updateDate = updateDate;
  }

  

}
