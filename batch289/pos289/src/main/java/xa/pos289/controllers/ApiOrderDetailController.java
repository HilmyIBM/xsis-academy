package xa.pos289.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xa.pos289.models.Category;
import xa.pos289.models.OrderDetail;
import xa.pos289.repositories.OrderDetailRepo;

@RestController
@CrossOrigin("*")
@RequestMapping(value="/api/")
public class ApiOrderDetailController {
	@Autowired OrderDetailRepo odrepo;

	@GetMapping("/orderdetail")
	public ResponseEntity<List<OrderDetail>> getAllOrderDetail(){
		try {
			List<OrderDetail> orderdetail = this.odrepo.findAll();
			return new ResponseEntity<List<OrderDetail>>(orderdetail, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<OrderDetail>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/orderdetail/get/{hid}")
	public ResponseEntity<List<OrderDetail>> getDetailByHid(@PathVariable("hid") Long hid){
		try {
			List<OrderDetail> orderdetail = this.odrepo.getDetailByHeaderId(hid);
			return new ResponseEntity<List<OrderDetail>>(orderdetail, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<OrderDetail>>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/orderdetail/{id}")
	public ResponseEntity<?> getOrderDetailById(@PathVariable Long id){
		try {
			OrderDetail orderdetail = this.odrepo.findById(id).orElse(null);
			
			if(orderdetail != null) {
				return new ResponseEntity<OrderDetail>(orderdetail,HttpStatus.OK);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Order detail dengan id " + id + " tidak ditemukan");
			}		
		} catch(Exception e) {
			return new ResponseEntity<OrderDetail>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/orderdetail")
	public ResponseEntity<OrderDetail> insertOrderDetail(@RequestBody OrderDetail orderdetail) {
		try {
			this.odrepo.save(orderdetail);
			return new ResponseEntity<OrderDetail>(orderdetail,HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<OrderDetail>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping("/orderdetail/{id}")
	public ResponseEntity<OrderDetail> editOrderDetail(@RequestBody OrderDetail orderdetail,
			@PathVariable Long id){
		try {
			orderdetail.setId(id);
			this.odrepo.save(orderdetail);
			return new ResponseEntity<OrderDetail>(orderdetail,HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<OrderDetail>(HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping("/orderdetail/{id}")
	public ResponseEntity<?> deleteOrderDetail(@PathVariable Long id){
		try {
			OrderDetail orderdetail = this.odrepo.findById(id).orElse(null);
			if(orderdetail != null) {
				this.odrepo.deleteById(id);
				return new ResponseEntity<Category>(HttpStatus.OK);
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Gagal menghapus id " + id + " Karena tidak ditemukan.");
			}
		}catch(Exception e) {
			return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
		}
	}
	
}
