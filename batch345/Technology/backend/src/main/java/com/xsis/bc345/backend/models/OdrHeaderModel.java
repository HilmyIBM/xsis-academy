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
@Table(name = "tbl_t_order_header")
public class OdrHeaderModel {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "trx_code",nullable = false)
    private String trxCode;

    @Column(name = "customer_id",nullable = false)
    private int customerId;

    @Column(nullable = true)
    private double amount;

    @Column(name = "total_qty",nullable = true)
    private int totalQty;

    @Column(name = "is_checkout",nullable = false)
    private boolean isCheckout;

    @Column(name = "is_deleted",nullable = true)
    private boolean isDeleted;

    @Column(name = "create_by",nullable = false)
    private int createBy;

    @Column(name = "create_date",nullable = true)
    @CreationTimestamp
    private LocalDateTime createDate;

    @Column(name = "update_by",nullable = true)
    private int updateBy;

    @Column(name = "update_date",nullable = true)
    private LocalDateTime updateDate;   

    /**
     * @return Integer return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return String return the trxCode
     */
    public String getTrxCode() {
        return trxCode;
    }

    /**
     * @param trxCode the trxCode to set
     */
    public void setTrxCode(String trxCode) {
        this.trxCode = trxCode;
    }

    /**
     * @return int return the customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return double return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return int return the totalQty
     */
    public int getTotalQty() {
        return totalQty;
    }

    /**
     * @param totalQty the totalQty to set
     */
    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

    /**
     * @return boolean return the isCheckout
     */
    public boolean isIsCheckout() {
        return isCheckout;
    }

    /**
     * @param isCheckout the isCheckout to set
     */
    public void setIsCheckout(boolean isCheckout) {
        this.isCheckout = isCheckout;
    }

    /**
     * @return boolean return the isDeleted
     */
    public boolean isIsDeleted() {
        return isDeleted;
    }

    /**
     * @param isDeleted the isDeleted to set
     */
    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * @return int return the createBy
     */
    public int getCreateBy() {
        return createBy;
    }

    /**
     * @param createBy the createBy to set
     */
    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    /**
     * @return LocalDateTime return the createDate
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * @return int return the updateBy
     */
    public int getUpdateBy() {
        return updateBy;
    }

    /**
     * @param updateBy the updateBy to set
     */
    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * @return LocalDateTime return the updateDate
     */
    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate the updateDate to set
     */
    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

}
