package com.xsis.bc345.backend.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tbl_t_order_detail")
public class OrderDetail {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private Integer id;

  @Column(name="order_header_id", nullable = false)
  private Integer orderHeaderId;

  @Column(name="product_id", nullable = false)
  private Integer productId;

  @Column(name="qty")
  private Integer qty;

  @Column(name="price")
  private Double price;

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

  public Integer getOrderHeaderId() {
    return orderHeaderId;
  }

  public void setOrderHeaderId(Integer orderHeaderId) {
    this.orderHeaderId = orderHeaderId;
  }

  public Integer getProductId() {
    return productId;
  }

  public void setProductId(Integer productId) {
    this.productId = productId;
  }

  public Integer getQty() {
    return qty;
  }

  public void setQty(Integer qty) {
    this.qty = qty;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
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
