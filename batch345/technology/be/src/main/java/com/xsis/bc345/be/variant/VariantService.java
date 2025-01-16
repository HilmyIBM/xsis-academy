package com.xsis.bc345.be.variant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VariantService {

    private final VariantRepository variantRepository;

    @Autowired
    public VariantService(VariantRepository variantRepository) {
        this.variantRepository = variantRepository;
    }

    public List<VariantModel> getAllVariant() {
        try {
            var data = variantRepository.findAllByDeleted(false);

            return data.orElse(List.of());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public VariantModel createVariant(VariantModel variantModel) {
        try {
            return variantRepository.save(variantModel);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public VariantModel updateVariant(VariantModel variantModel) {
        var data = variantRepository.findByIdAndDeleted(variantModel.getId(), false);

        if (data.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Variant with id %s doesn't exists".formatted(variantModel.getId()));

        VariantModel existingData = data.get();

        variantModel.setCategoryId(existingData.getCategoryId());
        variantModel.setCreateBy(existingData.getCreateBy());
        variantModel.setCreateDate(existingData.getCreateDate());
        variantModel.setUpdateDate(LocalDateTime.now());

        return variantRepository.save(variantModel);
    }

    public void deleteVariant(VariantModel variantModel) {
        var data = variantRepository.findByIdAndDeleted(variantModel.getId(), false);

        if (data.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Variant with id %s doesn't exists".formatted(variantModel.getId()));

        VariantModel existingData = data.get();

        existingData.setUpdateDate(LocalDateTime.now());
        existingData.setUpdateBy(variantModel.getUpdateBy());
        existingData.setDeleted(true);

        variantRepository.save(existingData);
    }
}
