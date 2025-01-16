package com.xsis.bc345.be.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.bc345.be.models.Variant;
import com.xsis.bc345.be.services.VariantService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@CrossOrigin("*")
@RequestMapping("api/variant")
public class VariantController {

    @Autowired
    private VariantService variantSvc;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            List<Variant> data = variantSvc.getAll();
            if (data.isEmpty()) {
                return new ResponseEntity<List<Variant>>(new ArrayList<Variant>(), HttpStatus.OK);
            }
            return new ResponseEntity<List<Variant>>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
