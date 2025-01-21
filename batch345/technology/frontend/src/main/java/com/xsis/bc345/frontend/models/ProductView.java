package com.xsis.bc345.frontend.models;

import java.sql.Timestamp;

public class ProductView {
  private int id;
  private String name;
  private int price;
  private int stock;
  private int variantId;
  private String variantName;
  private String categoryName;
  private String image;
  private boolean is_deleted;
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
  public String getVariantName() {
    return variantName;
  }
  public void setVariantName(String variantName) {
    this.variantName = variantName;
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
  public String getCategoryName() {
    return categoryName;
  }
  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }
}
