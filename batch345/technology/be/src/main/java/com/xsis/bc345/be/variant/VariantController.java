package com.xsis.bc345.be.variant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/variant")
public class VariantController {

    private final VariantService variantService;

    @Autowired
    public VariantController(VariantService variantService) {
        this.variantService = variantService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllVariant() {
        var data = variantService.getAllVariant();

        if (data.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> saveVariant(@RequestBody VariantModel variantModel) {
        return new ResponseEntity<>(variantService.createVariant(variantModel), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<?> updateVariant(@RequestBody VariantModel variantModel) {
        return new ResponseEntity<>(variantService.updateVariant(variantModel), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteVariant(@RequestBody VariantModel variantModel) {
        variantService.deleteVariant(variantModel);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
