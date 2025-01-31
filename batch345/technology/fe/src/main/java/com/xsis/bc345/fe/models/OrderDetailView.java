package com.xsis.bc345.fe.models;

import java.sql.Timestamp;

public class OrderDetailView {    
    private int id;
    private int orderHeaderId;
    private long productId;
    private String productName;
    private int qty;
    private Double price;
    private boolean deleted;
    private int createBy;
    private Timestamp createDate;
    private Integer updateBy;
    private Timestamp updateDate;

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

    public long getProductId() {
        return this.productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public Timestamp getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Integer getUpdateBy() {
        return this.updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Timestamp getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

}
