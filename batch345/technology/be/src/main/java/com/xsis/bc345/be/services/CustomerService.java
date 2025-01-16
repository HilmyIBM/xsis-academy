package com.xsis.bc345.be.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.xsis.bc345.be.models.Customer;
import com.xsis.bc345.be.repositories.CustomerRepositori;
import com.xsis.bc345.be.repositories.CustomerRepositori;

@Service
public class CustomerService {
    private CustomerRepositori CustomerRepo;

    public CustomerService(CustomerRepositori CustomerRepo){
        this.CustomerRepo = CustomerRepo;

    }
    public List<Customer> getAll() throws Exception{
        return CustomerRepo.findByDeleted(false).get();
    }

    public Optional<Customer> getById(Integer id) throws Exception{
        return CustomerRepo.findByIdAndDeleted(id, false);
    }

    public Optional<List<Customer>> getByCustomerName(String categoryName) throws Exception{
        return CustomerRepo.findByCustomerNameIgnoreCaseAndDeleted(categoryName, false);
    }
    public Optional<List<Customer>> getByNameOrDescription(String filter) throws Exception{
        return CustomerRepo.findByCustomerNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseAndDeleted(filter, filter, false);
    }

    public Customer create(Customer data) throws Exception{
        return CustomerRepo.save(data);
    }
    public Customer update(Customer data) throws Exception{
        Optional<Customer> catergoryExsist = CustomerRepo.findById(data.getId());
        if(catergoryExsist.isPresent()){
            // update field
            data.setCreateBy(catergoryExsist.get().getCreateBy());
            data.setCreateDate(catergoryExsist.get().getCreateDate());
            data.setUpdateDate(LocalDateTime.now());

            // update table
            return CustomerRepo.save(data);
        }
        throw new Exception("Customer doesn't exsist");
    }
    public Customer delete(Customer data) throws Exception{
        Optional<Customer> catergoryExsist = CustomerRepo.findById(data.getId());
        if(catergoryExsist.isPresent()){
            CustomerRepo.deleteById(data.getId());
        }
        throw new Exception("Customer doesn't exsist");
    }
}   
