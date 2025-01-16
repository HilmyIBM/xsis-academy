package com.xa.spring272.restcontroller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xa.spring272.models.OrderHeader;
import com.xa.spring272.models.Product;
import com.xa.spring272.repositories.OrderHeaderRepo;

@RestController
@CrossOrigin("*")
@RequestMapping(value="/api/orderheader/")
public class OrderHeaderRestController {
	
	@Autowired
	private OrderHeaderRepo headerrepo;

	@PostMapping(value="/")
	public ResponseEntity<Object> saveHeader(@RequestBody OrderHeader header) {
		OrderHeader headerdata = this.headerrepo.save(header);
		if(headerdata.equals(header)) {
			return new ResponseEntity<>(headerdata, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Simpan orderheader gagal!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Object> updateProduct(@RequestBody OrderHeader orderheader,
			@PathVariable("id") Long id) {
		Optional<OrderHeader> headerdata = this.headerrepo.findById(id);
		if(headerdata.isPresent()) {
			orderheader.setId(id);
			this.headerrepo.save(orderheader);
			return new ResponseEntity<>(headerdata, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Edit product gagal!", HttpStatus.BAD_REQUEST);
		}
	}	
	
}
