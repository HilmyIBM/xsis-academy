package com.xsis.bc345.be.auth;

import com.xsis.bc345.be.customer.CustomerModel;
import com.xsis.bc345.be.customer.CustomerRepository;
import com.xsis.bc345.be.util.encryption.Encrypt;
import com.xsis.bc345.be.util.exception.PasswordMismatchException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final CustomerRepository customerRepository;
    private final Encrypt encrypt;

    public AuthService(CustomerRepository customerRepository, Encrypt encrypt) {
        this.customerRepository = customerRepository;
        this.encrypt = encrypt;
    }

    public CustomerModel login(AuthDTO authDTO) {
        var data = customerRepository.findByEmailEqualsIgnoreCaseAndDeleted(authDTO.getEmail(), false);

        if (data.isEmpty())
            throw new EntityNotFoundException("Customer with email %s doesn't exists".formatted(authDTO.getEmail()));

        String loginPassword = encrypt.sha256Algorithm(authDTO.getPassword());

        if (!loginPassword.equals(data.get().getPassword()))
            throw new PasswordMismatchException("Wrong password");

        CustomerModel userData = data.get();

        userData.setPassword(null);
        userData.setAddress(null);
        userData.setPhone(null);

        return userData;
    }

}
