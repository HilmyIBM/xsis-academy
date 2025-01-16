package com.xsis.bc345.be.order.detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderDetailService {

    private final OrderDetailRepository repository;

    @Autowired
    public OrderDetailService(OrderDetailRepository repository) {
        this.repository = repository;
    }

    public List<OrderDetailModel> getAllOrderDetail() {
        try {
            var data = repository.findAllByDeleted(false);

            return data.orElse(List.of());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public OrderDetailModel createDetailOrder(OrderDetailModel model) {
        try {
            return repository.save(model);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public OrderDetailModel updateDetailOrder(OrderDetailModel model) {
        var data = repository.findByIdAndDeleted(model.getId(), false);

        if (data.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order Detail with id %s doesn't exists".formatted(model.getId()));

        var existingData = data.get();

        model.setOrderHeaderId(existingData.getOrderHeaderId());
        model.setProductId(existingData.getProductId());
        model.setCreateBy(existingData.getCreateBy());
        model.setCreateDate(existingData.getCreateDate());
        model.setUpdateDate(LocalDateTime.now());

        return repository.save(model);
    }

    public void deleteDetailOrder(OrderDetailModel model) {
        var data = repository.findByIdAndDeleted(model.getId(), false);

        if (data.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order Detail with id %s doesn't exists".formatted(model.getId()));

        var existingData = data.get();

        existingData.setUpdateBy(model.getUpdateBy());
        existingData.setUpdateDate(LocalDateTime.now());
        existingData.setDeleted(true);

        repository.save(existingData);
    }

}
