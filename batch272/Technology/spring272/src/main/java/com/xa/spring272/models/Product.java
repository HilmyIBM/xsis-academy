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
@Table(name="products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@ManyToOne
	@JoinColumn(name="variant_id", insertable = false, updatable=false)
	public Variant variant;

	@Column(name="variant_id")
	private Long VariantId;

	@Column(unique=true, name="initial", length=20)
	private String Initial;
	
	@Column(unique=true, name="name", length=200)
	private String Name;
	
	@Column(name="description", length=300)
	private String Description;

	@Column(name="price")
	private Double Price;

	@Column(name="stock")
	private Integer Stock;

	@Column(name="active")
	private boolean Active;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Variant getVariant() {
		return variant;
	}

	public void setVariant(Variant variant) {
		this.variant = variant;
	}

	public Long getVariantId() {
		return VariantId;
	}

	public void setVariantId(Long variantId) {
		VariantId = variantId;
	}

	public String getInitial() {
		return Initial;
	}

	public void setInitial(String initial) {
		Initial = initial;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Double getPrice() {
		return Price;
	}

	public void setPrice(Double price) {
		Price = price;
	}

	public Integer getStock() {
		return Stock;
	}

	public void setStock(Integer stock) {
		Stock = stock;
	}

	public boolean isActive() {
		return Active;
	}

	public void setActive(boolean active) {
		Active = active;
	}
	
	
}
