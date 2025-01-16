package com.xa.spring272.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;

@Entity
@Table(name="m_location_level")
public class LokasiLevel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long Id;
	
	@Column(name="name")
	private String Name;
	
	@Column(name="abbreviation")
	private String Abbreviation;
	

	@Column(name="create_by")
	private int CreateBy;
	
	@NotNull
	@CreationTimestamp
	@Column(name="create_on")
	private LocalDateTime CreateOn;
	
	@Column(name="modified_by")
	private Long ModifiedBy;
	
	@UpdateTimestamp
	@Column(name="modified_on")
	private LocalDateTime ModifiedOn;
	
	@Column(name="delete_by")
	private Long DeleteBy;
	
	@Column(name="delete_on")
	private LocalDateTime DeleteOn;
	
	@NotNull
	@Column(name="is_delete")
	private boolean IsDelete = false;

	public int getCreateBy() {
		return CreateBy;
	}

	public void setCreateBy(int createBy) {
		CreateBy = createBy;
	}

	public LocalDateTime getCreateOn() {
		return CreateOn;
	}

	public void setCreateOn(LocalDateTime createOn) {
		CreateOn = createOn;
	}

	public Long getModifiedBy() {
		return ModifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		ModifiedBy = modifiedBy;
	}

	public LocalDateTime getModifiedOn() {
		return ModifiedOn;
	}

	public void setModifiedOn(LocalDateTime modifiedOn) {
		ModifiedOn = modifiedOn;
	}

	public Long getDeleteBy() {
		return DeleteBy;
	}

	public void setDeleteBy(Long deleteBy) {
		DeleteBy = deleteBy;
	}

	public LocalDateTime getDeleteOn() {
		return DeleteOn;
	}

	public void setDeleteOn(LocalDateTime deleteOn) {
		DeleteOn = deleteOn;
	}

	public boolean isIsDelete() {
		return IsDelete;
	}

	public void setIsDelete(boolean isDelete) {
		IsDelete = isDelete;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAbbreviation() {
		return Abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		Abbreviation = abbreviation;
	}
	
	

}
