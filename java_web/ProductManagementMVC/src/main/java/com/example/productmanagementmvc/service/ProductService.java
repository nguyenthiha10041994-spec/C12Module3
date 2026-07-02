package com.example.productmanagementmvc.service;

import com.example.productmanagementmvc.dto.ProductDTO;
import com.example.productmanagementmvc.entity.Product;
import com.example.productmanagementmvc.repository.IProductRepository;
import com.example.productmanagementmvc.repository.ProductRepository;

import java.util.List;

public class ProductService implements IProductService {

    private IProductRepository productRepository = new ProductRepository();

    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void create(Product product) {
        productRepository.create(product);
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public void update(Product product) {
        productRepository.update(product);
    }

    @Override
    public void delete(int id) {
        productRepository.delete(id);
    }

    @Override
    public List<ProductDTO> search(String name, Integer categoryId) {
        return productRepository.search(name, categoryId);
    }
}