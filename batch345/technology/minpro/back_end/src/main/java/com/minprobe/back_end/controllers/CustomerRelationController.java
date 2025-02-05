package com.minprobe.back_end.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

import com.minprobe.back_end.models.BloodGroup;
import com.minprobe.back_end.models.CustomerRelation;
import com.minprobe.back_end.services.BloodGroupService;
import com.minprobe.back_end.services.CustomerRelationService;

@RestController
@CrossOrigin("*")
@RequestMapping("api/customer-relation")
public class CustomerRelationController {
    
    @Autowired
    private CustomerRelationService customerRelationSvc;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            List<CustomerRelation> data = customerRelationSvc.getAll();
            return new ResponseEntity<List<CustomerRelation>>(data, data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/filter/{filter}")
    public ResponseEntity<?> getByFilter(@PathVariable String filter) {
        try {
            List<CustomerRelation> data = customerRelationSvc.getByCode(filter).get();
            return new ResponseEntity<List<CustomerRelation>>(data, data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // CREATE NEW DATA
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody final CustomerRelation data) {
        try {
            data.setCreatedOn(LocalDateTime.now());
            return new ResponseEntity<CustomerRelation>(customerRelationSvc.create(data), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        try {
            Optional<CustomerRelation> data = customerRelationSvc.getById(id);
            return new ResponseEntity<CustomerRelation>(data.get(),data.isPresent() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // UPDATE DATA
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody final CustomerRelation data) {
        try {
            return new ResponseEntity<CustomerRelation>(customerRelationSvc.update(data), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE DATA
    @DeleteMapping("delete/{id}/{userId}")
    public ResponseEntity<?> delete(@PathVariable int id, @PathVariable int userId) {
        try {
            CustomerRelation data = customerRelationSvc.delete(id, userId);

            if (data.isDelete()) {
                return new ResponseEntity<CustomerRelation>(data, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Failed to delete!", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
