package com.example.productmanagementmvc.repository;

import com.example.productmanagementmvc.entity.Category;

import java.util.List;

public interface ICategoryRepository {
    List<Category> findAll();
}