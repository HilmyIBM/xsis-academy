package com.xa.spring272.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_details")
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="header_id", insertable = false, updatable=false)
	public OrderHeader orderheader;

	@Column(name="header_id")
	private Long HeaderId;
	
	@ManyToOne
	@JoinColumn(name="product_id", insertable = false, updatable=false)
	public Product product;
	
	@Column(name="product_id")
	private Long ProductId;
	
	@Column(name="quantity")
	private int Quantity;
	
	@Column(name="price")
	private Double Price;
	
	@Column(name="active")
	private boolean Active;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHeaderId() {
		return HeaderId;
	}

	public void setHeaderId(Long headerId) {
		HeaderId = headerId;
	}

	public Long getProductId() {
		return ProductId;
	}

	public void setProductId(Long productId) {
		ProductId = productId;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public Double getPrice() {
		return Price;
	}

	public void setPrice(Double price) {
		Price = price;
	}

	public boolean isActive() {
		return Active;
	}

	public void setActive(boolean active) {
		Active = active;
	}

	public OrderHeader getOrderheader() {
		return orderheader;
	}

	public void setOrderheader(OrderHeader orderheader) {
		this.orderheader = orderheader;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
