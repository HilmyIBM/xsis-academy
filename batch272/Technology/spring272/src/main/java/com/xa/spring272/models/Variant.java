package com.xa.spring272.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="variants")
public class Variant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="category_id", insertable = false, updatable=false)
	public Category category;
	
	@Column(name="category_id")
	private Long CategoryId;

	@Column(unique=true, name="initial", length=200)
	private String Initial;
	
	@Column(name="name", length=200)
	private String Name;
	
	@Column(name="active")
	private boolean Active;
	
	@Column(name="createby", length=200)
	private String Createby;
	
	@Column(name="createdate")
	private LocalDateTime Createdate;
	
	@Column(name="modifyby", length=200)
	private String Modifyby;
	
	@Column(name="modifydate")
	private LocalDateTime Modifydate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCategoryId() {
		return CategoryId;
	}

	public void setCategoryId(Long categoryId) {
		CategoryId = categoryId;
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

	public boolean isActive() {
		return Active;
	}

	public void setActive(boolean active) {
		Active = active;
	}

	public String getCreateby() {
		return Createby;
	}

	public void setCreateby(String createby) {
		Createby = createby;
	}

	public LocalDateTime getCreatedate() {
		return Createdate;
	}

	public void setCreatedate(LocalDateTime createdate) {
		Createdate = createdate;
	}

	public String getModifyby() {
		return Modifyby;
	}

	public void setModifyby(String modifyby) {
		Modifyby = modifyby;
	}

	public LocalDateTime getModifydate() {
		return Modifydate;
	}

	public void setModifydate(LocalDateTime modifydate) {
		Modifydate = modifydate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	
}
