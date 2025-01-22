package com.xsis.bc345.be.product;

import com.xsis.bc345.be.variant.VariantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        return productRepository
                .findAllByDeletedAndVariant_Deleted(false, false)
                .orElse(List.of());
    }

    public List<ProductModel> getAllProduct(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductModel> pageProduct = productRepository.findAllByDeleted(false, pageable);

        return pageProduct.getContent();
    }

    public ProductModel getById(int id, boolean deleted) {
        var data = productRepository.findByIdAndDeleted(id, deleted);

        if (data.isEmpty())
            throw new EntityNotFoundException("Product with id %s doesn't exists or already deleted".formatted(id));

        return data.get();
    }

    public ProductModel createProduct(ProductModel productModel) {
        return productRepository.save(productModel);
    }

    public ProductModel updateProduct(ProductModel productModel) {
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
    }

    public ProductModel deleteProduct(ProductModel productModel) {
        var data = productRepository.findByIdAndDeleted(productModel.getId(), false);

        if (data.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id %s doesn't exists or already deleted".formatted(productModel.getId()));

        ProductModel currProd = data.get();

        currProd.setDeleted(true);
        currProd.setUpdateBy(productModel.getUpdateBy());
        currProd.setUpdateDate(LocalDateTime.now());

        return productRepository.save(currProd);
    }
}
