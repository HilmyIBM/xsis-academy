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
@Table(name = "tbl_t_order_header")
public class OrderHeader {
    @Id
    // @SequenceGenerator(name = "tbl_t_order_header_id_seq", sequenceName = "tbl_t_order_header_id_seq", allocationSize = 1)
	// @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "tbl_t_order_header_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "trx_code", unique = true)
	private String trxCode;
	
	@Column(name = "customer_id", nullable = false)
	private long customerId;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "total_qty")
	private int totalQty;
	
	@Column(name = "is_checkout")
	private boolean checkedOut;
	
	@Column(name = "is_deleted")
	private boolean deleted;

	@Column(name = "create_by", nullable = false)
	private int createBy;

	@Column(name = "create_date")
	@CreationTimestamp
	private LocalDateTime createDate;

	@Column(name = "update_by")
	private int updateBy;

	@Column(name = "update_date")
	private LocalDateTime updateDate;


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTrxCode() {
        return this.trxCode;
    }

    public void setTrxCode(String trxCode) {
        this.trxCode = trxCode;
    }

    public long getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getTotalQty() {
        return this.totalQty;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

    public boolean isCheckedOut() {
        return this.checkedOut;
    }

    public boolean getCheckedOut() {
        return this.checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
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

    public int getUpdateBy() {
        return this.updateBy;
    }

    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

}
