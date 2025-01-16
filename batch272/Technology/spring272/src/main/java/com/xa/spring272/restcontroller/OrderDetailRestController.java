package com.xa.spring272.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xa.spring272.models.OrderDetail;
import com.xa.spring272.models.Product;
import com.xa.spring272.repositories.OrderDetailRepo;

@RestController
@CrossOrigin("*")
@RequestMapping(value="/api/orderdetail/")
public class OrderDetailRestController {
	
	@Autowired
	private OrderDetailRepo orderdetailrepo;
	
	@PostMapping(value="/")
	public ResponseEntity<Object> saveDetail(@RequestBody OrderDetail detail) {
		OrderDetail detaildata = this.orderdetailrepo.save(detail);
		if(detaildata.equals(detail)) {
			return new ResponseEntity<>(detaildata, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Simpan orderdetail gagal!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value="/{hid}")
	public ResponseEntity<List<OrderDetail>> getdetail(@PathVariable("hid") Long hid) {
		try {
			List<OrderDetail> detaildata = this.orderdetailrepo.getDetailByHid(hid);
			return new ResponseEntity<>(detaildata, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
