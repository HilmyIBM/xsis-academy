package com.xsis.bc345.be.order.header;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderHeaderService {

    private final OrderHeaderRepository repository;

    @Autowired
    public OrderHeaderService(OrderHeaderRepository repository) {
        this.repository = repository;
    }

    public List<OrderHeaderModel> getAllOrderHeader() {
        try {
            return repository
                    .findAllByDeleted(false)
                    .orElseGet(List::of);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public OrderHeaderModel createOrderHeader(OrderHeaderModel model) {
        try {
            return repository.save(model);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public OrderHeaderModel updateOrderHeader(OrderHeaderModel model) {
        var data = repository.findByIdAndDeleted(model.getId(), false);

        if (data.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order Header with id %s doesn't exists".formatted(model.getId()));

        var existingData = data.get();

        model.setCustomerId(existingData.getCustomerId());
        model.setCreateBy(existingData.getCreateBy());
        model.setCreateDate(existingData.getCreateDate());
        model.setUpdateDate(LocalDateTime.now());

        return repository.save(model);
    }

    public void deleteOrderHeader(OrderHeaderModel model) {
        var data = repository.findByIdAndDeleted(model.getId(), false);

        if (data.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order Header with id %s doesn't exists".formatted(model.getId()));

        var existingData = data.get();

        existingData.setUpdateBy(model.getUpdateBy());
        existingData.setUpdateDate(LocalDateTime.now());
        existingData.setDeleted(true);

        repository.save(existingData);
    }
}
