package com.xsis.bc345.be.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomer() {
        var data = userService.getAllCustomer();

        if (data.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable long id) {
        return new ResponseEntity<>(userService.getCustomer(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveCustomer(@RequestBody UserModel userModel) {
        return new ResponseEntity<>(userService.createCustomer(userModel), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateCustomer(@RequestBody UserModel userModel) {
        return new ResponseEntity<>(userService.updateCustomer(userModel), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCustomer(@RequestBody UserModel userModel) {
        return new ResponseEntity<>(userService.deleteCustomer(userModel), HttpStatus.OK);
    }
}
