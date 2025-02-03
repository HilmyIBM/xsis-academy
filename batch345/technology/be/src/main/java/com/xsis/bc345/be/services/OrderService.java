package com.xsis.bc345.be.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.bc345.be.models.Order;
import com.xsis.bc345.be.models.OrderDetail;
import com.xsis.bc345.be.models.OrderHeader;
import com.xsis.bc345.be.models.Product;
import com.xsis.bc345.be.repositories.OrderDetailsRepository;
import com.xsis.bc345.be.repositories.OrderHeaderRepository;
import com.xsis.bc345.be.repositories.OrderRepository;
import com.xsis.bc345.be.repositories.ProductRepository;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepo;

    @Autowired
    OrderHeaderRepository orderHeaderRepo;

    @Autowired
    OrderDetailsRepository orderDetailsRepo;

    @Autowired
    ProductRepository productRepo;

    private String generateTrxCode(Long orderHeaderId) {
        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
        return String.format("XA-%s-%05d", LocalDateTime.now().format(dtFormat), orderHeaderId) ;
    }

    public List<Order> getAll() {
        return orderRepo.findAll();
    }

    public Optional<Order> getBy(Long id) {
        return orderRepo.findById(id);
    }

    public Order create(Order order) throws Exception {
        try {
            OrderHeader newOrderHeader = new OrderHeader();
            newOrderHeader.setCustomerId(order.getCustomerId());
            newOrderHeader.setTotalQty(order.getTotalQty());
            newOrderHeader.setAmount(order.getAmount());
            newOrderHeader.setCheckedOut(order.getCheckedOut());
            newOrderHeader.setDeleted(false);
            newOrderHeader.setCreateBy(order.getCreateBy());
            newOrderHeader.setCreateDate(LocalDateTime.now());
    
            //Save the New Order Header
            newOrderHeader = orderHeaderRepo.save(newOrderHeader);
            
            if (newOrderHeader.getId() > 0) {
                String trxCode = generateTrxCode(newOrderHeader.getId());
                newOrderHeader.setTrxCode(trxCode);

                //Update the TRXCode
                newOrderHeader = orderHeaderRepo.save(newOrderHeader);
    
                //Process each Items
                for (OrderDetail newOrderDetail : order.getOrderDetails()) {
                    newOrderDetail.setOrderHeaderId(newOrderHeader.getId());
                    newOrderDetail.setCreateBy(newOrderHeader.getCreateBy());
                    newOrderDetail.setCreateDate(newOrderHeader.getCreateDate());
    
                    orderDetailsRepo.save(newOrderDetail);

                    //Update Product Stock
                    Optional<Product> product = productRepo.findById((int)newOrderDetail.getProductId());
                    if (product.isPresent()) {
                        if (product.get().getStock() >= newOrderDetail.getQty()) {
                            product.get().setStock(product.get().getStock() - newOrderDetail.getQty());
                            product.get().setUpdateBy(newOrderHeader.getCreateBy());
                            product.get().setUpdateDate(newOrderHeader.getUpdateDate());

                            productRepo.save(product.get());
                        }
                    }
                }
            }
            else {
                throw new Exception("Failed to create new Order");
            }

            return orderRepo.findById(newOrderHeader.getId()).get();
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public Order update(Order order) throws Exception {
        Optional<Order> existingOrder = orderRepo.findById(order.getId());

        if (existingOrder.isPresent()) {
            order.setCreateBy(existingOrder.get().getCreateBy());
            order.setCreateDate(existingOrder.get().getCreateDate());
            order.setUpdateDate(LocalDateTime.now());

            return orderRepo.save(order);
        } else {
            throw new Exception("Order with ID = " + order.getId() + " does not Exist!");
        }
    }
}
