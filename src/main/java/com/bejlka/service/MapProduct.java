package com.bejlka.service;

import com.bejlka.model.Product;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@NoArgsConstructor
public class MapProduct {
    private final Map<Integer, Product> productMap = new HashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    public Map<Integer, Product> getProductMap() {
        return productMap;
    }

    public void addProduct(Product product) {
        productMap.put(id.incrementAndGet(), product);
    }

    public void updateProduct(Integer key, Product product) {
        productMap.put(key, product);
    }

    public void deleteProduct(Integer key) {
        productMap.remove(key);
    }

    @Override
    public String toString() {
        return "MapProduct{" +
                "productMap=" + productMap +
                '}';
    }
}
