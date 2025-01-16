package com.xsis.bc345.be.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tbl_t_oder_header")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name="id")
    private int id;

    @Column(name="trx_code", length = 100, nullable = false, unique = true)
    private String trxCode;

    @Column(name = "customer_id", nullable = false)
    private int cutomerId;

    @Column(name = "amount", nullable = false)
    private Double amount;
    
    @Column(name = "total_qty", nullable = false)
    private int totalQty;
    
    @Column(name = "checkout", nullable = false)
    private boolean checkout;

    @Column(name="is_deleted")
    private boolean deleted;

    @Column(name="create_by", nullable = false)
    private int createBy;
    
    @Column(name="create_date")
    @CreationTimestamp
    private LocalDateTime createDate;

    @Column(name="update_by", nullable = true)
    private Integer updateBy;
    
    @Column(name="update_date", nullable = true)
    private LocalDateTime updateDate;


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrxCode() {
        return this.trxCode;
    }

    public void setTrxCode(String trxCode) {
        this.trxCode = trxCode;
    }

    public int getCutomerId() {
        return this.cutomerId;
    }

    public void setCutomerId(int cutomerId) {
        this.cutomerId = cutomerId;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public int getTotalQty() {
        return this.totalQty;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

    public boolean isCheckout() {
        return this.checkout;
    }

    public boolean getCheckout() {
        return this.checkout;
    }

    public void setCheckout(boolean checkout) {
        this.checkout = checkout;
    }

    public boolean isDeleted() {
        return this.deleted;
    }

    public boolean getDeleted() {
        return this.deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Integer getUpdateBy() {
        return this.updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
    
}
