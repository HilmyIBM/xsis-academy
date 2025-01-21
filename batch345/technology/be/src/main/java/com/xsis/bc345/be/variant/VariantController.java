package com.xsis.bc345.be.variant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/variant")
public class VariantController {

    private final VariantService variantService;

    @Autowired
    public VariantController(VariantService variantService) {
        this.variantService = variantService;
    }

    @GetMapping
    public ResponseEntity<List<VariantModel>> getAllVariant() {
        var data = variantService.getAllVariant();

        if (data.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VariantModel> getVariantById(@PathVariable int id) {
         return new ResponseEntity<>(variantService.getById(id, false), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VariantModel> saveVariant(@RequestBody VariantModel variantModel) {
        return new ResponseEntity<>(variantService.createVariant(variantModel), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<VariantModel> updateVariant(@RequestBody VariantModel variantModel) {
        return new ResponseEntity<>(variantService.updateVariant(variantModel), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<VariantModel> deleteVariant(@RequestBody VariantModel variantModel) {
        return new ResponseEntity<>(variantService.deleteVariant(variantModel), HttpStatus.OK);
    }
}
