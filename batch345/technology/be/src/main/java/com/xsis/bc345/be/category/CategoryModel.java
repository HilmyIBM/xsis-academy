package com.xsis.bc345.be.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xsis.bc345.be.variant.VariantModel;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tbl_m_categories")
public class CategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "category_name", length = 100, nullable = false)
    private String categoryName;

    @Column(name = "description")
    private String description;

    @Column(name = "is_deleted")
    private boolean deleted;

    @Column(name = "create_by", nullable = false)
    private int createBy;

    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDateTime createDate;

    @Column(name = "update_by")
    private Integer updateBy;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "category", cascade = CascadeType.MERGE, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private List<VariantModel> variants;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
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

    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public List<VariantModel> getVariants() {
        return variants;
    }

    public void setVariants(List<VariantModel> variants) {
        this.variants = variants;
    }

    @Override
    public String toString() {
        return "CategoryModel{" +
                "updateDate=" + updateDate +
                ", updateBy=" + updateBy +
                ", createDate=" + createDate +
                ", createBy=" + createBy +
                ", deleted=" + deleted +
                ", description='" + description + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", id=" + id +
                '}';
    }
}
