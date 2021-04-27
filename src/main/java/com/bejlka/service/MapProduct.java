package com.bejlka.service;

import com.bejlka.model.Product;

import java.util.HashMap;
import java.util.Map;

public class MapProduct {
  private final Map<Integer, Product> productMap = new HashMap<>();
  private Integer id = 0;
  private static MapProduct INSTANCE;

  private MapProduct() { }

  public static MapProduct getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new MapProduct();
    }
    return INSTANCE;
  }

  public Map<Integer, Product> getProductMap() {
    return productMap;
  }

  public void addProduct(Product product) {
    productMap.put(id, product);
    ++id;
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
