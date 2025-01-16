package com.xa.spring272.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.xa.spring272.models.Product;
import com.xa.spring272.repositories.ProductRepoPaging;

@Service
public class ProductService {
	@Autowired
	ProductRepoPaging prorepo;
	
	public List<Product> getAllProduct(Integer pageNo, Integer pageSize, String sortBy) {
		
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		
		Page<Product> pagedProduct = this.prorepo.findAll(paging);
		
		if(pagedProduct.hasContent()) {
			return pagedProduct.getContent();
		} else {
            return new ArrayList<Product>();
		}
		
	}
}
