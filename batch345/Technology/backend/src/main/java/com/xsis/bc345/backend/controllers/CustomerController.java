package com.xsis.bc345.backend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.bc345.backend.models.CategoryModel;
import com.xsis.bc345.backend.models.CustomerModel;
import com.xsis.bc345.backend.services.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@CrossOrigin("*")
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerSVC;

    @GetMapping("")
    public ResponseEntity<?>getAll(){
         try {
            List<CustomerModel> data= customerSVC.getAll();
            if (data.size()>0) {
                return new ResponseEntity<List<CustomerModel>>(data,HttpStatus.OK);   
            }else{
                return new ResponseEntity<List<CustomerModel>>(data,HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> addCustomer(@RequestBody final CustomerModel data) {
        try {
            return new ResponseEntity<CustomerModel>(customerSVC.addCustomer(data),HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseEntity<?> edit(@RequestBody final CustomerModel data){
        try {
            return new ResponseEntity<CustomerModel>(customerSVC.update(data),HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getbyId(@PathVariable int id) {
        try {
            Optional<CustomerModel> data=customerSVC.getbyId(id);

            if (data.isPresent()) {
                return new ResponseEntity<CustomerModel>(data.get(),HttpStatus.OK);   
            }else{
                return new ResponseEntity<CustomerModel>(data.get(),HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            
        }
    }

    @DeleteMapping("/delete/{id}/{userId}")
    public ResponseEntity<?> delete(@PathVariable int id,@PathVariable int userId){ 
        try {
            CustomerModel data=customerSVC.delete(id,userId);
            return new ResponseEntity<CustomerModel>(data,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable String email) {
        try {
            Optional<CustomerModel> data=customerSVC.getByEmail(email);

            if (data.isPresent()) {
                return new ResponseEntity<CustomerModel>(data.get(),HttpStatus.OK);   
            }else{
                return new ResponseEntity<CustomerModel>(data.get(),HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            
        }
    }

    @GetMapping("/paginated/{page}/{size}")
    public ResponseEntity<?> getPaginated(@PathVariable int page,@PathVariable int size) {
        try {
                Page<CustomerModel> data=customerSVC.getAll(PageRequest.of(page, size,Sort.by("id").ascending()));
                return new ResponseEntity<Page<CustomerModel>>(data,HttpStatus.OK);   
            }
            catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/filter/{filter}/{page}/{size}")
    public ResponseEntity<?> getbyFilter(@PathVariable String filter,@PathVariable int page,@PathVariable int size) {
        try {
            Page<CustomerModel> data=customerSVC.getbyfilter(filter,PageRequest.of(page, size,Sort.by("id").ascending()));
            return new ResponseEntity<Page<CustomerModel>>(data,HttpStatus.OK);   
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
