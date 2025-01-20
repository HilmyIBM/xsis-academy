package com.xsis.bc345.frontend.models;

import java.time.LocalDateTime;

public class ProductView {
  private int id;
  private String name;
  private int price;
  private int stock;
  private int variantId;
  private String image;
  private boolean is_deleted;
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
  public int getPrice() {
    return price;
  }
  public void setPrice(int price) {
    this.price = price;
  }
  public int getStock() {
    return stock;
  }
  public void setStock(int stock) {
    this.stock = stock;
  }
  public int getVariantId() {
    return variantId;
  }
  public void setVariantId(int variantId) {
    this.variantId = variantId;
  }
  public String getImage() {
    return image;
  }
  public void setImage(String image) {
    this.image = image;
  }
  public boolean isIs_deleted() {
    return is_deleted;
  }
  public void setIs_deleted(boolean is_deleted) {
    this.is_deleted = is_deleted;
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
