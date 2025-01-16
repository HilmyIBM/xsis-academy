package com.xa.spring272.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xa.spring272.models.Lokasi;
import com.xa.spring272.models.LokasiLevel;
import com.xa.spring272.repositories.LokLevRepo;
import com.xa.spring272.repositories.LokRepo;

@RestController
@CrossOrigin("*")
@RequestMapping(value="/api/location")
public class LocationRestController {
	
	@Autowired
	private LokRepo lokrepo;
	
	@Autowired
	private LokLevRepo loklevrepo;

	@GetMapping(value="/")
	public ResponseEntity<List<Map<String, Object>>> getLokasi() {
		try {
			List<Map<String, Object>> lokasi = this.lokrepo.AllLocation();
			return new ResponseEntity<>(lokasi, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}	

	@GetMapping(value="/locationlevel")
	public ResponseEntity<List<LokasiLevel>> locationlevel() {
		try {
			List<LokasiLevel> lokasi = this.loklevrepo.findAll();
			return new ResponseEntity<>(lokasi, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}	

	@GetMapping(value="/getname/{lid}")
	public ResponseEntity<List<Lokasi>> getname(@PathVariable("lid") Long lid) {
		try {
			List<Lokasi> lokasi = this.lokrepo.getLokasi(lid);
			return new ResponseEntity<>(lokasi, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}	}
