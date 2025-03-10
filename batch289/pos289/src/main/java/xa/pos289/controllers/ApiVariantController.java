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

import xa.pos289.models.Variant;
import xa.pos289.repositories.VariantRepo;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api")
public class ApiVariantController {

	@Autowired
	VariantRepo varRepo;

	@GetMapping("/variant")
	public ResponseEntity<List<Variant>> getAllVariant() {
		try {
			List<Variant> variant = this.varRepo.findAll();
			return new ResponseEntity<List<Variant>>(variant, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Variant>>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/variant/{id}")
	public ResponseEntity<?> getVariantById(@PathVariable Long id){
		try {
			Variant variant = this.varRepo.findById(id).orElse(null);
			if(variant != null) {
				return new ResponseEntity<Variant>(variant,HttpStatus.OK);
			} else {
				return ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.body("Variant dengan Id " + id +" tidak ditemukan");
			}
			
		} catch(Exception e) {
			return new ResponseEntity<Variant>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/insertVariant")
	public ResponseEntity<Variant> insertVariant(@RequestBody Variant variant) {
		try {
			this.varRepo.save(variant);
			return new ResponseEntity<Variant>(variant, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Variant>(HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping("/editVariant/{id}")
	public ResponseEntity<Variant> editVariant(@RequestBody Variant variant, @PathVariable Long id) {
		try {
			variant.setId(id);
			this.varRepo.save(variant);
			return new ResponseEntity<Variant>(variant, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Variant>(HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("/deleteVariant/{id}")
	public ResponseEntity<Variant> deleteVariant(@PathVariable Long id) {
		try {
			this.varRepo.deleteById(id);
			return new ResponseEntity<Variant>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Variant>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/getvarbycatid/{id}")
	public ResponseEntity<?> getvarbycatid(@PathVariable("id") Long id){
		try {
			List<Variant> variant = this.varRepo.findByCategory_id(id);
			if(variant != null) {
				return new ResponseEntity<>(variant,HttpStatus.OK);
			} else {
				return ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.body("CategoryID dengan Id " + id +" tidak ditemukan");
			}
			
		} catch(Exception e) {
			return new ResponseEntity<Variant>(HttpStatus.NO_CONTENT);
		}
	}

}
