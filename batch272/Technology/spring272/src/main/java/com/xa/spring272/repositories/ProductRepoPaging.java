package com.xa.spring272.repositories;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.xa.spring272.models.Product;

@Repository
public interface ProductRepoPaging extends PagingAndSortingRepository<Product, Long> {


}
