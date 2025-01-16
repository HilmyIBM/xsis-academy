package com.xsis.bc345.be.customer;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerModel> getAllCustomer() {
        try {
            var data = customerRepository.findAllByDeleted(false);

            return data.orElse(List.of());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    public CustomerModel createCustomer(CustomerModel customerModel) {
        try {
            return customerRepository.save(customerModel);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public CustomerModel updateCustomer(CustomerModel customerModel) {
        var data = customerRepository.findByIdAndDeleted(customerModel.getId(), false);

        if (data.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with id %s doesn't exists".formatted(customerModel.getId()));

        var existingData = data.get();

        customerModel.setCreateBy(existingData.getCreateBy());
        customerModel.setCreateDate(existingData.getCreateDate());
        customerModel.setUpdateDate(LocalDateTime.now());

        return customerRepository.save(customerModel);
    }

    public void deleteCustomer(CustomerModel model) {
        var data = customerRepository.findByIdAndDeleted(model.getId(), false);

        if (data.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with id %s doesn't exists".formatted(model.getId()));

        var existingData = data.get();

        existingData.setUpdateBy(model.getUpdateBy());
        existingData.setUpdateDate(LocalDateTime.now());
        existingData.setDeleted(true);

        customerRepository.save(existingData);
    }
}
