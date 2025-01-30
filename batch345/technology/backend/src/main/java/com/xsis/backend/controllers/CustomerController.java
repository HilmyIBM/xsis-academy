package com.xsis.backend.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

import com.xsis.backend.models.Customer;
import com.xsis.backend.services.CustomerService;

@RestController
@CrossOrigin("*")
@RequestMapping("api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            List<Customer> data = customerService.getAll();
            return new ResponseEntity<List<Customer>>(data,
                    (data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/paginated/{page}/{size}")
    public ResponseEntity<?> getAll(@PathVariable int page, @PathVariable int size) {
        try {
            final Page<Map<String, Object>> data = customerService.getAll(PageRequest.of(page, size));
            return new ResponseEntity<Page<Map<String, Object>>>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        try {
            Optional<Customer> data = customerService.getById(id);
            return new ResponseEntity<Customer>(data.get(), (data.isPresent() ? HttpStatus.OK : HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/filter/{filter}")
    public ResponseEntity<?> getByFilter(@PathVariable String filter) {
        try {
            List<Map<String, Object>> data = customerService.getListByFilter(filter);
            return new ResponseEntity<List<Map<String, Object>>>(data,
                    (data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/paginated/filter/{filter}/{page}/{size}")
    public ResponseEntity<?> getByFilter(@PathVariable String filter, @PathVariable int page, @PathVariable int size) {
        try {
            Page<Map<String, Object>> data = customerService.getListByFilter(filter, PageRequest.of(page, size));
            return new ResponseEntity<Page<Map<String, Object>>>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable String email) {
        try {
            Optional<Customer> data = customerService.getByEmail(email);

            return new ResponseEntity<Customer>(
                    data.get(),
                    (data.isPresent() ? HttpStatus.OK : HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody final Customer data) {
        try {
            return new ResponseEntity<Customer>(customerService.create(data), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody final Customer data) {
        try {
            return new ResponseEntity<Customer>(customerService.update(data), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}/{userId}")
    public ResponseEntity<?> delete(@PathVariable int id, @PathVariable int userId) {
        try {
            Customer data = customerService.delete(id, userId);
            if (data.isDeleted()) {
                return new ResponseEntity<Customer>(data, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Failed to delete customer!", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
