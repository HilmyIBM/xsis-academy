package com.xsis.bc345.frontend.models;

import java.time.LocalDateTime;

public class CustomerView {
  private int id;
  private String name;
  private String email;
  private String password;
  private String address;
  private String phone;
  private int roleId;
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
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public String getPhone() {
    return phone;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }
  public int getRoleId() {
    return roleId;
  }
  public void setRoleId(int roleId) {
    this.roleId = roleId;
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
