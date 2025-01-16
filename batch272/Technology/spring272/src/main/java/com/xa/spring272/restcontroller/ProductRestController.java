package com.xa.spring272.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xa.spring272.models.Category;
import com.xa.spring272.models.Product;
import com.xa.spring272.models.Variant;
import com.xa.spring272.repositories.CategoryRepo;
import com.xa.spring272.repositories.ProductRepo;
import com.xa.spring272.repositories.ProductRepoPaging;
import com.xa.spring272.repositories.VariantRepo;
import com.xa.spring272.services.ProductService;

@RestController
@CrossOrigin("*")
@RequestMapping(value="/api/product/")
public class ProductRestController {
	@Autowired
	private ProductRepo productrepo;
	
	@Autowired
	private CategoryRepo categoryrepo;
	
	@Autowired
	private VariantRepo variantrepo;
	

	@GetMapping(value="/")
	public ResponseEntity<List<Product>> getAllProduct() {
		try {
			List<Product> product = this.productrepo.findAll();
			return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value="/search/{key}")
	public ResponseEntity<List<Product>> searchProduct(
			@PathVariable("key") String key) {
		try {
			List<Product> product = this.productrepo.searchProduct(key);
			return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(value="/{id}")
	public ResponseEntity<Object> getProductbyid(@PathVariable("id") Long id) {
		Optional<Product> productdata = this.productrepo.findById(id);
		if(productdata.isPresent()) {
			return new ResponseEntity<>(productdata, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("data tidak ditemukan!", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value="/getcategory")
	public ResponseEntity<List<Category>> getcategory() {
		try {
			List<Category> category = this.categoryrepo.findAll();
			return new ResponseEntity<>(category, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(value="/getvariant")
	public ResponseEntity<List<Variant>> getvariant() {
		try {
			List<Variant> variant = this.variantrepo.findAll();
			return new ResponseEntity<>(variant, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(value="/getvariantbycategory/{catid}")
	public ResponseEntity<List<Variant>> getvariantbycategory(
			@PathVariable("catid") Long catid) {
		try {
			List<Variant> variant = this.variantrepo.getVariantByCategory(catid);
			return new ResponseEntity<>(variant, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(value="/getvariantbyid/{id}")
	public ResponseEntity<Object> getvariantbyid(
			@PathVariable("id") Long id) {
		try {
			Optional<Variant> variant = this.variantrepo.findById(id);
			return new ResponseEntity<>(variant, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping(value="/")
	public ResponseEntity<Object> saveProduct(@RequestBody Product product) {
		Product productdata = this.productrepo.save(product);
		if(productdata.equals(product)) {
			return new ResponseEntity<>(productdata, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Simpan product gagal!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PatchMapping(value="/{id}")
	public ResponseEntity<Object> updateProduct(@RequestBody Product product, @PathVariable("id") Long id) {
		Optional<Product> productdata = this.productrepo.findById(id);
		if(productdata.isPresent()) {
			product.setId(id);
			this.productrepo.save(product);
			return new ResponseEntity<>(productdata, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Edit product gagal!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable("id") Long id) {
		this.productrepo.deleteById(id);
		return new ResponseEntity<>("Delete product berhasil", HttpStatus.OK);
	}
	
	@Autowired
	private ProductService productservice;
	
	@GetMapping("/pageable/{pageNo}/{pageSize}/{sortBy}")
	public ResponseEntity<List<Product>> getProductPageable(
			@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
		try {
			List<Product> productdata = this.productservice.getAllProduct(pageNo, pageSize, sortBy);
			return new ResponseEntity<>(productdata, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value="/paged") 
	public Page<Product> productPaged(Pageable pagable) {
		return this.productrepo.findAll(pagable);
	}
}	
