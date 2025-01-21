package com.xsis.bc345.backend.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
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
    private Optional<VariantModel> variantExist;
    
    public List<VariantModel> getAll(){
        try {
            return variantrepo.findByIsDeleted(false).get();
        } catch (Exception e) {
            throw e;
        }   
    }

    public List<Map<String,Object>> getNative() throws Exception{
        return variantrepo.findByNativeQuery().get();
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

    public List<Map<String,Object>> getNativeId(int id) throws Exception{
        return variantrepo.findByNativeQueryId(id).get();
    }

    public VariantModel delete(int id,int userId) throws Exception{
        variantExist=variantrepo.findById(id);
        if (variantExist.isPresent()) {
            variantExist.get().setIsDeleted(true);
            variantExist.get().setUpdateBy(userId);
            variantExist.get().setUpdateDate(LocalDateTime.now());
            return variantrepo.save(variantExist.get());   
        }else{
            throw new Exception("Data Tidak Adak");
        }
    }
}
