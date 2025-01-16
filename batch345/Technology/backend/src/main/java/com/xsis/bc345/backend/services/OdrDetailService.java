package com.xsis.bc345.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.bc345.backend.models.OdrDetailModel;
import com.xsis.bc345.backend.repositories.OdrDetailRepo;

@Service
public class OdrDetailService {
    @Autowired
    private OdrDetailRepo odrdetailrepo;

    public List<OdrDetailModel> getAll(){
        try {
            return odrdetailrepo.findByIsDeleted(false).get();
        } catch (Exception e) {
            throw e;
        }
    }

    public OdrDetailModel create(OdrDetailModel data){
        return odrdetailrepo.save(data);
    }

}
