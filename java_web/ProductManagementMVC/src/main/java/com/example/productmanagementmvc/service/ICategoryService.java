package com.example.productmanagementmvc.service;

import com.example.productmanagementmvc.entity.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> findAll();

}