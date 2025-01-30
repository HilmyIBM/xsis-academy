package com.xsis.bc345.be.user;

import com.xsis.bc345.be.util.encryption.Encrypt;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final Encrypt encrypt;

    @Autowired
    public UserService(UserRepository userRepository, Encrypt encrypt) {
        this.userRepository = userRepository;
        this.encrypt = encrypt;
    }

    public List<UserModel> getAllCustomer() {
        var data = userRepository.findAllByDeleted(false);

        if (data.isEmpty())
            return List.of();

        return data.get()
                .stream()
                .peek(c -> c.setPassword(null))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public UserModel getCustomer(Long id) {
        var data = userRepository.findByIdAndDeleted(id, false);

        if (data.isEmpty())
            throw new EntityNotFoundException("Customer with id %s doesn't exists".formatted(id));

        UserModel userData = data.get();
        userData.setPassword(null);

        return userData;
    }

    public UserModel createCustomer(UserModel userModel) {
        userModel.setPassword(encrypt.sha256Algorithm(userModel.getPassword()));

        return userRepository.save(userModel);
    }

    public UserModel updateCustomer(UserModel userModel) {
        var data = userRepository.findByIdAndDeleted(userModel.getId(), false);

        if (data.isEmpty())
            throw new EntityNotFoundException("Customer with id %s doesn't exists".formatted(userModel.getId()));

        var existingData = data.get();

        if (userModel.getPassword() != null)
            userModel.setPassword(encrypt.sha256Algorithm(userModel.getPassword()));

        userModel.setCreateBy(existingData.getCreateBy());
        userModel.setCreateDate(existingData.getCreateDate());
        userModel.setUpdateDate(LocalDateTime.now());

        var updatedUser = userRepository.save(userModel);

        updatedUser.setPassword(null);

        return updatedUser;
    }

    public UserModel deleteCustomer(UserModel model) {
        var data = userRepository.findByIdAndDeleted(model.getId(), false);

        if (data.isEmpty())
            throw new EntityNotFoundException("Customer with id %s doesn't exists".formatted(model.getId()));

        var existingData = data.get();

        existingData.setUpdateBy(model.getUpdateBy());
        existingData.setUpdateDate(LocalDateTime.now());
        existingData.setDeleted(true);

        var deletedUser = userRepository.save(existingData);
        deletedUser.setPassword(null);

        return deletedUser;
    }
}
