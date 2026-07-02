package com.example.productmanagementmvc.service;

import com.example.productmanagementmvc.entity.Category;
import com.example.productmanagementmvc.repository.CategoryRepository;
import com.example.productmanagementmvc.repository.ICategoryRepository;

import java.util.List;

public class CategoryService implements ICategoryService {
    private ICategoryRepository categoryRepository = new CategoryRepository();

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}