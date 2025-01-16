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
@Table(name="tbl_t_order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name="id")
    private int id;

    @Column(name = "order_header_id", nullable = false)
    private int orderHeaderId;
    
    @Column(name = "product_id", nullable = false)
    private int productId;
    
    @Column(name = "qty", nullable = false)
    private int qty;
    
    @Column(name = "price", nullable = false)
    private Double price;

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

    public int getOrderHeaderId() {
        return this.orderHeaderId;
    }

    public void setOrderHeaderId(int orderHeaderId) {
        this.orderHeaderId = orderHeaderId;
    }

    public int getProductId() {
        return this.productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQty() {
        return this.qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
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
