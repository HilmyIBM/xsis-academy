package com.xa.spring272.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xa.spring272.models.OrderDetail;
import com.xa.spring272.repositories.OrderDetailRepo;

@Service
@Transactional
public class OrderServices {

		@Autowired OrderDetailRepo odrepo;
		
		public List<OrderDetail> listAll() {
			return this.odrepo.findAll();
		}
}
