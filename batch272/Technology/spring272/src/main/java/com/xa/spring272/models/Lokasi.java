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
@Table(name="m_location")
public class Lokasi {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long Id;
	
	@Column(name="name")
	private String Name;
	
//	@ManyToOne
//	@JoinColumn(name="location_id", insertable = false, updatable=false)
//	public Lokasi lokasi;
	
	@Column(name="location_id")
	private Long LocationId;
	
	@ManyToOne
	@JoinColumn(name="loklev", insertable=false, updatable=false)
	public LokasiLevel loklev;
	
	@Column(name="loklev")
	private Long LokLev;

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

	public Long getLocationId() {
		return LocationId;
	}

	public void setLocationId(Long locationId) {
		LocationId = locationId;
	}

	public LokasiLevel getLoklev() {
		return loklev;
	}

	public void setLoklev(LokasiLevel loklev) {
		this.loklev = loklev;
	}

	public Long getLokLev() {
		return LokLev;
	}

	public void setLokLev(Long lokLev) {
		LokLev = lokLev;
	}
	
	
}
