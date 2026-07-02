package com.example.productmanagementmvc.repository;

import com.example.productmanagementmvc.dto.ProductDTO;
import com.example.productmanagementmvc.entity.Product;

import java.util.List;

public interface IProductRepository {
    List<ProductDTO> findAll();
    void create(Product product);
    Product findById(int id);
    void update(Product product);
    void delete(int id);
    List<ProductDTO> search(String name, Integer categoryId);
}