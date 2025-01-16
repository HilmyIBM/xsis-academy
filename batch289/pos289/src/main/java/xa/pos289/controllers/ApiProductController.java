package xa.pos289.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.http.HttpHeaders;
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

import xa.pos289.models.Product;
import xa.pos289.models.Variant;
import xa.pos289.repositories.ProductRepo;
import xa.pos289.repositories.VariantRepo;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api")
public class ApiProductController {

	@Autowired
	ProductRepo proRepo;

	@GetMapping("/product")
	public ResponseEntity<List<Product>> getAllProduct() {
		try {
			List<Product> product = this.proRepo.findAll();
			return new ResponseEntity<List<Product>>(product, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<?> getProductById(@PathVariable Long id){
		try {
			Product product = this.proRepo.findById(id).orElse(null);
			if(product != null) {
				return new ResponseEntity<Product>(product, HttpStatus.OK);
			} else {
				return ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.body("Product dengan Id " + id +" tidak ditemukan");
			}
			
		} catch(Exception e) {
			return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/insertProduct")
	public ResponseEntity<Object> insertProduct(@RequestBody Product product) {
		Product productData = this.proRepo.save(product);
		if(productData.equals(product)) {
			return new ResponseEntity<>("Data product berhasih disimpan", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Failed!", HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/editProduct/{id}")
	public ResponseEntity<Product> editProduct(@RequestBody Product product, @PathVariable("id") Long id) {
		try {
			product.setId(id);
			this.proRepo.save(product);
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
		try {
			this.proRepo.deleteById(id);
			return new ResponseEntity<Product>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/product/search/{textsearch}")
	public ResponseEntity<List<Product>> getAllProduct(
			@PathVariable("textsearch") String textsearch) {
		try {
			List<Product> product = this.proRepo.SearchProduct(textsearch);
			return new ResponseEntity<List<Product>>(product, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
		}
	}
}
