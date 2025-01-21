package com.xsis.bc345.be.product;

import com.xsis.bc345.be.variant.VariantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final VariantRepository variantRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, VariantRepository variantRepository) {
        this.productRepository = productRepository;
        this.variantRepository = variantRepository;
    }

    public List<ProductModel> getAllProduct() {
        try {
            var data = productRepository.findAllByDeletedAndVariant_Deleted(false, false);

            return data.orElseGet(List::of);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    public ProductModel getById(int id, boolean deleted) {
        try {
            var data = productRepository.findByIdAndDeleted(id, deleted);

            if (data.isEmpty())
                throw new EntityNotFoundException("Product with id %s doesn't exists or already deleted".formatted(id));

            return data.get();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    public ProductModel createProduct(ProductModel productModel) {
        try {
            return productRepository.save(productModel);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    public ProductModel updateProduct(ProductModel productModel) {
        try {
            var product = productRepository.findByIdAndDeleted(productModel.getId(), false);

            if (product.isEmpty())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id %s doesn't exists or already deleted".formatted(productModel.getId()));

            var variant = variantRepository.findByIdAndDeleted(productModel.getVariant().getId(), false);

            if (variant.isEmpty())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Variant with id %s doesn't exists or already deleted".formatted(productModel.getId()));

            ProductModel existingProduct = product.get();

            productModel.setCreateBy(existingProduct.getCreateBy());
            productModel.setCreateDate(existingProduct.getCreateDate());
            productModel.setUpdateDate(LocalDateTime.now());

            return productRepository.save(productModel);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    public ProductModel deleteProduct(ProductModel productModel) {
        try {
            var data = productRepository.findByIdAndDeleted(productModel.getId(), false);

            if (data.isEmpty())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id %s doesn't exists or already deleted".formatted(productModel.getId()));

            ProductModel currProd = data.get();

            currProd.setDeleted(true);
            currProd.setUpdateBy(productModel.getUpdateBy());
            currProd.setUpdateDate(LocalDateTime.now());

            return productRepository.save(currProd);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }
}
