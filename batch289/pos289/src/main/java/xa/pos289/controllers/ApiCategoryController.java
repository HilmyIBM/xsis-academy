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
import xa.pos289.repositories.CategoryRepo;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api")
public class ApiCategoryController {
	
	@Autowired CategoryRepo catRepo;
	
	@GetMapping("/category")
	public ResponseEntity<List<Category>> getAllCat(){
		try {
			List<Category> category = this.catRepo.findAll();
			return new ResponseEntity<List<Category>>(category,HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/category/{id}")
	public ResponseEntity<?> getCatById(@PathVariable Long id){
		try {
			Category category = this.catRepo.findById(id).orElse(null);
			
			if(category != null) {
				return new ResponseEntity<Category>(category,HttpStatus.OK);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Kategori dengan id " + id + " tidak ditemukan");
			}		
		} catch(Exception e) {
			return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/category")
	public ResponseEntity<Category> insertCategory(@RequestBody Category category) {
		try {
			this.catRepo.save(category);
			return new ResponseEntity<Category>(category,HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping("/category/{id}")
	public ResponseEntity<Category> editCategory(@RequestBody Category category,
			@PathVariable Long id){
		try {
			category.setId(id);
			this.catRepo.save(category);
			return new ResponseEntity<Category>(category,HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping("/category/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long id){
		try {
			Category category = this.catRepo.findById(id).orElse(null);
			if(category != null) {
				this.catRepo.deleteById(id);
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
