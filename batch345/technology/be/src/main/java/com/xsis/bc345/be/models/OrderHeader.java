package com.xsis.bc345.be.models;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_t_order_header")
public class OrderHeader {
    @Id
    // @SequenceGenerator(name = "tbl_t_order_header_id_seq", sequenceName = "tbl_t_order_header_id_seq", allocationSize = 1)
	// @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "tbl_t_order_header_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "trx_code", nullable = false, unique = true)
	private String trxCode;
	
	@Column(name = "customer_id", nullable = false)
	private long customerId;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "total_qty")
	private int totalQty;
	
	@Column(name = "is_checkout")
	private boolean checkedOut;
	
	@Column(name = "is_deleted")
	private boolean deleted;

	@Column(name = "create_by", nullable = false)
	private int createBy;

	@Column(name = "create_date")
	@CreationTimestamp
	private LocalDateTime createDate;

	@Column(name = "update_by")
	private int updateBy;

	@Column(name = "update_date")
	private LocalDateTime updateDate;

    @OneToMany(mappedBy = "orderHeader", fetch = FetchType.EAGER)
    private List<OrderDetail> orderDetails;

}
