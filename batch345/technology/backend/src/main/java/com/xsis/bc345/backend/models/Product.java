package com.xsis.bc345.backend.models;

import java.time.LocalDateTime;

import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name="tbl_m_product")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private Integer id;

  @Column(name="name", nullable = false, length = 100)
  private String name;

  @Column(name="price", nullable = false)
  private Integer price;

  @Column(name="stock")
  private Integer stock;

  @Column(name="variant_id")
  private Integer variantId;

  @Column(name="image")
  private String image;
  
  @Column(name="is_deleted")
  private boolean deleted;

  @Column(name="create_by", nullable = false)
  private Integer createBy;

  @Column(name="create_date")
  @CreationTimestamp
  private LocalDateTime createDate;

  @Column(name="update_by")
  private Integer updateBy;

  @Column(name="update_date")
  private LocalDateTime updateDate;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }

  public Integer getVariantId() {
    return variantId;
  }

  public void setVariantId(Integer variantId) {
    this.variantId = variantId;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  public Integer getCreateBy() {
    return createBy;
  }

  public void setCreateBy(Integer createBy) {
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
