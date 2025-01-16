package com.xsis.bc345.be.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_t_order_detail")
public class OrderDetail {
	@Id
	// @SequenceGenerator(name = "tbl_t_order_detail_id_seq", sequenceName = "tbl_t_order_detail_id_seq", allocationSize = 1)
	// @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "tbl_t_order_detail_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "order_header_id", nullable = false)
	private long orderHeaderId;
	
	@Column(name = "product_id", nullable = false)
	private long productId;

	@Column(name = "qty")
	private int qty;

	@Column(name = "price")
	private double price;

	@Column(name = "is_deleted")
	private boolean deleted;

	@Column(name = "create_by")
	private int createBy;

	@Column(name = "create_date")
	@CreationTimestamp
	private LocalDateTime createDate;

	@Column(name = "update_by")
	private int updateBy;

	@Column(name = "update_date")
	private LocalDateTime updateDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="order_header_id", updatable = false, insertable = false, referencedColumnName = "id")
    private OrderHeader orderHeader;


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderHeaderId() {
        return this.orderHeaderId;
    }

    public void setOrderHeaderId(long orderHeaderId) {
        this.orderHeaderId = orderHeaderId;
    }

    public long getProductId() {
        return this.productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQty() {
        return this.qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public OrderHeader getOrderHeader() {
        return this.orderHeader;
    }

    public void setOrderHeader(OrderHeader orderHeader) {
        this.orderHeader = orderHeader;
    }
}
