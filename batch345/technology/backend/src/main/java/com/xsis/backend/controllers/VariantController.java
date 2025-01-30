package com.xsis.backend.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
            final List<Map<String, Object>> data = variantService.getAllNative();
            return new ResponseEntity<List<Map<String, Object>>>(data,
                    (data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/paginated/{page}/{size}")
    public ResponseEntity<?> getAll(@PathVariable int page, @PathVariable int size,
            @RequestParam(defaultValue = "id") String sort, @RequestParam(defaultValue = "ASC") String sd) {
        try {
            final Page<Map<String, Object>> data = variantService
                    .getAllNative(PageRequest.of(page, size, Sort.by(Direction.fromString(sd), sort)));
            return new ResponseEntity<Page<Map<String, Object>>>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/filter/{filter}")
    public ResponseEntity<?> getByFilter(@PathVariable String filter) {
        try {
            List<Map<String, Object>> data = variantService.getByFilter(filter);
            return new ResponseEntity<List<Map<String, Object>>>(data,
                    (data.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/paginated/filter/{filter}/{page}/{size}")
    public ResponseEntity<?> getByFilter(@PathVariable String filter, @PathVariable int page, @PathVariable int size,
            @RequestParam(defaultValue = "id") String sort, @RequestParam(defaultValue = "ASC") String sd) {
        try {
            Page<Map<String, Object>> data = variantService.getByFilter(filter, PageRequest.of(page, size,
                    Sort.by(Direction.fromString(sd), sort)));
            return new ResponseEntity<Page<Map<String, Object>>>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody final Variant data) {
        try {
            return new ResponseEntity<Variant>(variantService.create(data), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        try {
            Optional<Variant> data = variantService.getById(id);

            return new ResponseEntity<Variant>(data.get(), (data.isPresent() ? HttpStatus.OK : HttpStatus.NO_CONTENT));

        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody final Variant data) {
        try {
            return new ResponseEntity<Variant>(variantService.update(data), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}/{userId}")
    public ResponseEntity<?> delete(@PathVariable int id, @PathVariable int userId) {
        try {
            Variant data = variantService.delete(id, userId);
            if (data.isDeleted()) {
                return new ResponseEntity<Variant>(data, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Failed to delete variant!", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
