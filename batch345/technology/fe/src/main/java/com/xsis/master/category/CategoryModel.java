package com.xsis.master.category;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.Objects;

public class CategoryModel {
    private long id;
    private String name;
    private String description;
    private boolean isDeleted;

    private long createBy;
    private LocalDateTime createDate;
    private long updateBy;
    private LocalDateTime updateDate;

    public CategoryModel() {}

    public CategoryModel(long id, String name, String description, boolean isDeleted, long createBy, LocalDateTime createDate, long updateBy, LocalDateTime updateDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isDeleted = isDeleted;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
    }

    public CategoryModel(long id, String name, String description, boolean isDeleted, long createBy, LocalDateTime createDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isDeleted = isDeleted;
        this.createBy = createBy;
        this.createDate = createDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(long createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(long updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public CategoryModel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CategoryModel that)) return false;
        return isDeleted == that.isDeleted && createBy == that.createBy && updateBy == that.updateBy && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(createDate, that.createDate) && Objects.equals(updateDate, that.updateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, isDeleted, createBy, createDate, updateBy, updateDate);
    }

    @Override
    public String toString() {
        return "CategoryModel{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isDeleted=" + isDeleted +
                ", createBy=" + createBy +
                ", createDate=" + createDate +
                ", updateBy=" + updateBy +
                ", updateDate=" + updateDate +
                '}';
    }
}
