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
@Table(name="tbl_t_order_header")
public class OrderHeader {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private Integer id;

  @Column(name="trx_code", length = 100, nullable = false, unique = true)
  private String trxCode;

  @Column(name="customer_id", nullable = false)
  private Integer customerId;

  @Column(name="amount")
  private Double amount;

  @Column(name="total_qty", nullable = false)
  private Integer totalQty;

  @Column(name="is_checkout")
  private boolean checkout;

  @Column(name="is_deleted")
  private boolean deleted;

  @Column(name="create_by", nullable = false)
  private Integer createBy;

  @Column(name="create_date")
  @CreationTimestamp
  private LocalDateTime createDate;
  
  @Column(name="update_by")
  private Integer updateBy;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTrxCode() {
    return trxCode;
  }

  public void setTrxCode(String trxCode) {
    this.trxCode = trxCode;
  }

  public Integer getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Integer customerId) {
    this.customerId = customerId;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Integer getTotalQty() {
    return totalQty;
  }

  public void setTotalQty(Integer totalQty) {
    this.totalQty = totalQty;
  }

  public boolean isCheckout() {
    return checkout;
  }

  public void setCheckout(boolean checkout) {
    this.checkout = checkout;
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

  @Column(name="update_date")
  private LocalDateTime updateDate;
}
