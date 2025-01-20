package com.xsis.frontend.model;

import java.sql.Timestamp;

public class ProductView {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int variantId;
    private String image;
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getVariantId() {
        return this.variantId;
    }

    public void setVariantId(int variantId) {
        this.variantId = variantId;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isDeleted() {
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
