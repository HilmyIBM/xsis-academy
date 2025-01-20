package com.xsis.bc345.backend.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.bc345.backend.models.CategoryModel;
import com.xsis.bc345.backend.models.ProductModel;
import com.xsis.bc345.backend.models.VariantModel;
import com.xsis.bc345.backend.repositories.VariantRepo;

@Service
public class VariantService {
    @Autowired
    private VariantRepo variantrepo;

    
    public List<VariantModel> getAll(){
        try {
            return variantrepo.findByIsDeleted(false).get();
        } catch (Exception e) {
            throw e;
        }   
    }

    public VariantModel create(VariantModel data) throws Exception{
        return variantrepo.save(data);
    }

    public VariantModel update(VariantModel data)throws Exception{
        Optional<VariantModel>variantexist=variantrepo.findById(data.getId());
        if(variantexist.isPresent()){
            data.setCreateBy(variantexist.get().getCreateBy());
            data.setCreateDate(variantexist.get().getCreateDate());
            data.setUpdateDate(LocalDateTime.now());
            return variantrepo.save(data);
        }else{
            throw new Exception("Data Tidak Ada");
        }
    }

    public Optional<VariantModel> getbyId(Integer id){
        return variantrepo.findByIdAndIsDeleted(id,false);
    }
}
