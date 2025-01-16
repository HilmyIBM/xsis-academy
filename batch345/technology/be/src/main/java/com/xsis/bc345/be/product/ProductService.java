package com.xsis.bc345.be.product;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductModel> getAllProduct() {
        try {
            var data = productRepository.findAllByDeleted(false);

            return data.orElseGet(List::of);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ProductModel createProduct(ProductModel productModel) {
        try {
            return productRepository.save(productModel);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ProductModel updateProduct(ProductModel productModel) {

//        updateModel(productModel.getId(),
//                productModel,
//                update -> {
//                    update.setDeleted(productModel.isDeleted());
//                },
//                save -> productRepository.save(productModel));

        var data = productRepository.findByIdAndDeleted(productModel.getId(), false);

        if (data.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id %s doesn't exists".formatted(productModel.getId()));

        ProductModel currProduct = data.get();

        productModel.setVariantId(currProduct.getVariantId());
        productModel.setCreateBy(currProduct.getCreateBy());
        productModel.setCreateDate(currProduct.getCreateDate());
        productModel.setUpdateDate(LocalDateTime.now());

        return productRepository.save(productModel);
    }

    public void deleteProduct(ProductModel productModel) {
        var data = productRepository.findByIdAndDeleted(productModel.getId(), false);

        if (data.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id %s doesn't exists".formatted(productModel.getId()));

        ProductModel currProd = data.get();

        currProd.setDeleted(true);
        currProd.setUpdateBy(productModel.getUpdateBy());
        currProd.setUpdateDate(LocalDateTime.now());

        productRepository.save(currProd);
    }


    public <T, ID> T updateModel(
            ID id,
            T model,
            Consumer<T> test,
            Consumer<T> updater,
            Function<T, T> saver) {

//        var data = finder.get();
//
//        if (data.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource with id %s doesn't exist".formatted(id));
//        }
//
//        T existingModel = data.get();

        updater.andThen(test).accept(model);

        // Apply custom updates using the provided Consumer
        updater.accept(model);

        // Save and return the updated model
        return saver.apply(model);
    }
}
