package com.xsis.bc345.frontend.controller;

import java.time.LocalDateTime;

public class Category {
  private long id;
  private String name;
  private String description;

  private boolean deleted;

  private int createBy;
  private LocalDateTime createDate;
  private int updateBy;
  private LocalDateTime updateDate;

  

  public Category(long id, String name, String description, boolean deleted, int createBy, LocalDateTime createDate,
      int updateBy, LocalDateTime updateDate) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.deleted = deleted;
    this.createBy = createBy;
    this.createDate = createDate;
    this.updateBy = updateBy;
    this.updateDate = updateDate;
  }
  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
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
