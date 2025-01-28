package com.xsis.bc345.be.customer;

import com.xsis.bc345.be.util.encryption.Encrypt;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final Encrypt encrypt;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, Encrypt encrypt) {
        this.customerRepository = customerRepository;
        this.encrypt = encrypt;
    }

    public List<CustomerModel> getAllCustomer() {
        var data = customerRepository.findAllByDeleted(false);

        if (data.isEmpty())
            return List.of();

        return data.get()
                .stream()
                .peek(c -> c.setPassword(null))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public CustomerModel getCustomer(Long id) {
        var data = customerRepository.findByIdAndDeleted(id, false);

        if (data.isEmpty())
            throw new EntityNotFoundException("Customer with id %s doesn't exists".formatted(id));

        CustomerModel userData = data.get();
        userData.setPassword(null);

        return userData;
    }

    public CustomerModel createCustomer(CustomerModel customerModel) {
        customerModel.setPassword(encrypt.sha256Algorithm(customerModel.getPassword()));

        return customerRepository.save(customerModel);
    }

    public CustomerModel updateCustomer(CustomerModel customerModel) {
        var data = customerRepository.findByIdAndDeleted(customerModel.getId(), false);

        if (data.isEmpty())
            throw new EntityNotFoundException("Customer with id %s doesn't exists".formatted(customerModel.getId()));

        var existingData = data.get();

        if (customerModel.getPassword() != null)
            customerModel.setPassword(encrypt.sha256Algorithm(customerModel.getPassword()));

        customerModel.setCreateBy(existingData.getCreateBy());
        customerModel.setCreateDate(existingData.getCreateDate());
        customerModel.setUpdateDate(LocalDateTime.now());

        return customerRepository.save(customerModel);
    }

    public CustomerModel deleteCustomer(CustomerModel model) {
        var data = customerRepository.findByIdAndDeleted(model.getId(), false);

        if (data.isEmpty())
            throw new EntityNotFoundException("Customer with id %s doesn't exists".formatted(model.getId()));

        var existingData = data.get();

        existingData.setUpdateBy(model.getUpdateBy());
        existingData.setUpdateDate(LocalDateTime.now());
        existingData.setDeleted(true);

        return customerRepository.save(existingData);
    }
}
