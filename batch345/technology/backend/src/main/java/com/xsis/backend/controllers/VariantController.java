package com.xsis.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.backend.models.Variant;
import com.xsis.backend.services.VariantService;

@RestController
@CrossOrigin("*")
@RequestMapping("api/variants")
public class VariantController {
    @Autowired
    private VariantService variantService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            List<Variant> data = variantService.getAll();
            return new ResponseEntity<List<Variant>>(data, (data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
