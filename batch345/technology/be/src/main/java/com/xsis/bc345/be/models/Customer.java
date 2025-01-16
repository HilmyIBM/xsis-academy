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
@Table(name="tbl_m_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name="id")
    private int id;

    @Column(name="name", length = 50, nullable = false)
    private String name;

    @Column(name="email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name="password", length = 50, nullable = false)
    private String password;

    @Column(name="address", nullable = false)
    private String address;

    @Column(name="phone", length = 15, nullable = false, unique = true)
    private String phone;

    @Column(name="role_id", nullable = true)
    private Integer roleID;

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
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
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

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    

}
