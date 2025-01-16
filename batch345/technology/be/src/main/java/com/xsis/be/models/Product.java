package com.xsis.be.models;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Tbl_M_Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name="stock")
    private Integer stock;

    @Column(name="variant_id", nullable = false)
    private Integer variantId;

    @Column(name = "image", nullable = true)
    private String image;

    @Column(name = "is_deleted", nullable = false)
    private boolean deleted;

    @Column(name = "create_by", nullable = false)
    private Integer createBy;

    @Column(name = "create_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime createDate;

    @Column(name = "update_by", nullable = true)
    private Integer updateBy;

    @Column(name = "update_date", nullable = true)
    private LocalDateTime updateDate;



    public Integer getCreateBy() {
        return createBy;
    }
    public LocalDateTime getCreateDate() {
        return createDate;
    }
    public Integer getId() {
        return id;
    }
    public String getImage() {
        return image;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public Integer getStock() {
        return stock;
    }
    public Integer getUpdateBy() {
        return updateBy;
    }
    public LocalDateTime getUpdateDate() {
        return updateDate;
    }
    public Integer getVariantId() {
        return variantId;
    }
    public boolean isDeleted() {
        return deleted;
    }


    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }
    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
    public void setVariantId(Integer variantId) {
        this.variantId = variantId;
    }
	// is_deleted BOOLEAN DEFAULT FALSE,
	// create_by int NOT NULL,
	// create_date TIMESTAMP DEFAULT NOW(),
	// update_by int NULL,
	// update_date TIMESTAMP NULL,
}
