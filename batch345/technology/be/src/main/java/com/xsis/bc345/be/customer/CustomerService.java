package com.xsis.bc345.be.customer;

import com.xsis.bc345.be.util.exception.PasswordMismatchException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
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

        return data.get();
    }

    public CustomerModel login(CustomerModel customer) {
        var data = customerRepository.findByEmailEqualsIgnoreCaseAndDeleted(customer.getEmail(), false);

        if (data.isEmpty())
            throw new EntityNotFoundException("Customer with email %s doesn't exists".formatted(customer.getEmail()));

        String loginPassword = digestString(customer.getPassword());

        if (!loginPassword.equals(data.get().getPassword()))
            throw new PasswordMismatchException("Wrong password");

        return data.get();
    }

    public CustomerModel createCustomer(CustomerModel customerModel) {
        customerModel.setPassword(digestString(customerModel.getPassword()));

        return customerRepository.save(customerModel);
    }

    public CustomerModel updateCustomer(CustomerModel customerModel) {
        var data = customerRepository.findByIdAndDeleted(customerModel.getId(), false);

        if (data.isEmpty())
            throw new EntityNotFoundException("Customer with id %s doesn't exists".formatted(customerModel.getId()));

        var existingData = data.get();

        if (customerModel.getPassword() != null)
            customerModel.setPassword(digestString(customerModel.getPassword()));

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

    private static String digestString(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder(2 * hash.length);

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);

                if (hex.length() == 1) hexString.append('0');

                hexString.append(hex);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
