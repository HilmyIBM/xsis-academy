package com.xsis.bc345.fe.models;

import java.util.List;
import java.util.Map;

public class CartView {
    private List<Map<String, Object>> listCart;
    private Integer totProduct;
    private Double estPrice;

    public List<Map<String,Object>> getListCart() {
        return this.listCart;
    }

    public void setListCart(List<Map<String,Object>> listCart) {
        this.listCart = listCart;
    }

    public Integer getTotProduct() {
        return this.totProduct;
    }

    public void setTotProduct(Integer totProduct) {
        this.totProduct = totProduct;
    }

    public Double getEstPrice() {
        return this.estPrice;
    }

    public void setEstPrice(Double estPrice) {
        this.estPrice = estPrice;
    }

}
