package com.xsis.bc345.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.bc345.backend.models.OdrHeaderModel;
import com.xsis.bc345.backend.repositories.OdrHeaderRepo;

@Service
public class OdrHeaderService {
    @Autowired
    private OdrHeaderRepo odrheaderepo;

     public List<OdrHeaderModel> getAll(){
        try {
            return odrheaderepo.findByIsDeleted(false).get();
        } catch (Exception e) {
            throw e;
        }
    }
}
