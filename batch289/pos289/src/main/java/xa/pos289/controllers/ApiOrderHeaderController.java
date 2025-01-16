package xa.pos289.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xa.pos289.models.OrderDetail;
import xa.pos289.models.OrderHeader;
import xa.pos289.models.Product;
import xa.pos289.repositories.OrderHeaderRepo;

@RestController
@CrossOrigin("*")
@RequestMapping(value="/api/")
public class ApiOrderHeaderController {
	
	@Autowired OrderHeaderRepo orderheaderrepo;
	
	@GetMapping("/orderheader")
	public ResponseEntity<List<OrderHeader>> getAllOrderHeader(){
		try {
			List<OrderHeader> orderheader = this.orderheaderrepo.findAll();
			return new ResponseEntity<List<OrderHeader>>(orderheader, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<OrderHeader>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/orderheader/{id}")
	public ResponseEntity<?> getOrderheaderById(@PathVariable Long id){
		try {
			OrderHeader orderheader = this.orderheaderrepo.findById(id).orElse(null);
			
			if(orderheader != null) {
				return new ResponseEntity<OrderHeader>(orderheader, HttpStatus.OK);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Order header dengan id " + id + " tidak ditemukan");
			}		
		} catch(Exception e) {
			return new ResponseEntity<OrderDetail>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/orderheader")
	public ResponseEntity<OrderHeader> insertorderheader(@RequestBody OrderHeader orderheader) {
		OrderHeader orderheaderData = this.orderheaderrepo.save(orderheader);
		if(orderheaderData.equals(orderheader)) {
			return new ResponseEntity<OrderHeader>(orderheader, HttpStatus.OK);
		} else {
			return new ResponseEntity<OrderHeader>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/orderheader/{id}")
	public ResponseEntity<OrderHeader> editOrderHeader(@RequestBody OrderHeader orderheader,
			@PathVariable Long id){
		try {
			orderheader.setId(id);
			this.orderheaderrepo.save(orderheader);
			return new ResponseEntity<OrderHeader>(orderheader,HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<OrderHeader>(HttpStatus.NO_CONTENT);
		}
	}

}
