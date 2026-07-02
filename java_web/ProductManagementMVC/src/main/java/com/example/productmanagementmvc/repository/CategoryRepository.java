package com.example.productmanagementmvc.repository;

import com.example.productmanagementmvc.entity.Category;
import com.example.productmanagementmvc.util.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository implements ICategoryRepository {
    private static final String SELECT_ALL = "SELECT * FROM category";
    @Override
    public List<Category> findAll() {
        List<Category> categoryList = new ArrayList<>();
        try (
                Connection connection = ConnectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Category category = new Category(id, name);
                categoryList.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoryList;
    }
}