package com.xsis.backend.models;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "trx_code", length = 100, nullable = false, unique = true)
    private String trxCode;

    @Column(name = "customer_id", nullable = false)
    private int customerId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "total_qty", nullable = false)
    private int totalQty;

    @Column(name = "is_checkedout")
    private boolean checkedout;

    @Column(name = "is_deleted")
    private boolean deleted;

    @Column(name = "create_by", nullable = false)
    private int createBy;

    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDateTime createDate;

    @Column(name = "update_by", nullable = true)
    private Integer updateBy;

    @Column(name = "update_date", nullable = true)
    private LocalDateTime updateDate;
}
