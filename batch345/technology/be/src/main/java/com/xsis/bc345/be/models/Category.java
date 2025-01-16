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
@Table(name = "tbl_m_categories")
public class Category {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="category_name", length = 100,nullable = false)
    private String categoryName;

    @Column(name="description", nullable = true)
    private String description;

    @Column(name="is_deleted")
    private boolean deleted;

    @Column(name="create_by", nullable = false)
    private Integer createBy;

    @Column(name="create_date")
    @CreationTimestamp
    private LocalDateTime createDate;

    @Column(name="update_by", nullable = true)
    private Integer updateBy;

    @Column(name="update_date",nullable = true)
    private LocalDateTime updateDate;

    public String getCategoryName() {
        return categoryName;
    }
    public Integer getCreateBy() {
        return createBy;
    }
    public LocalDateTime getCreateDate() {
        return createDate;
    }
    public boolean isDeleted() {
        return deleted;
    }
    public String getDescription() {
        return description;
    }
    public Integer getId() {
        return id;
    }
    public Integer getUpdateBy() {
        return updateBy;
    }
    public LocalDateTime getUpdateDate() {
        return updateDate;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
    public void setDescription(String description) {
        this.description = description;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }
    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
