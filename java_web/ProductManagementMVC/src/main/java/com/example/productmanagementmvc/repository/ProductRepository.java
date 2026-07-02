package com.example.productmanagementmvc.repository;

import com.example.productmanagementmvc.dto.ProductDTO;
import com.example.productmanagementmvc.entity.Product;
import com.example.productmanagementmvc.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository {
    private static final String SELECT_ALL = "SELECT p.*, c.name AS category_name " + "FROM product p " + "JOIN category c ON p.category_id = c.id";
    private static final String INSERT_PRODUCT = "INSERT INTO product(name, price, quantity, color, description, category_id) " + "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM product WHERE id = ?";
    private static final String UPDATE_PRODUCT = "UPDATE product " + "SET name=?, price=?, quantity=?, color=?, description=?, category_id=? " + "WHERE id=?";
    private static final String DELETE_PRODUCT = "DELETE FROM product WHERE id=?";
    private static final String SEARCH_PRODUCT = "SELECT p.*, c.name AS category_name " + "FROM product p " + "JOIN category c ON p.category_id = c.id " + "WHERE p.name LIKE ? " + "AND (? IS NULL OR p.category_id = ?)";

    @Override
    public List<ProductDTO> findAll() {
        List<ProductDTO> productDTOList = new ArrayList<>();
        try (
                Connection connection = ConnectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                int categoryId = resultSet.getInt("category_id");
                String categoryName = resultSet.getString("category_name");

                ProductDTO productDTO = new ProductDTO(id,name,price,quantity,color,description,categoryId,categoryName);
                productDTOList.add(productDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productDTOList;
    }
    @Override
    public void create(Product product) {
        try (
                Connection connection = ConnectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT);
        ) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getCategoryId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Product findById(int id) {
        Product product = null;
        try (
                Connection connection = ConnectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int productId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                int categoryId = resultSet.getInt("category_id");
                product = new Product(productId,name,price,quantity,color,description,categoryId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public void update(Product product) {
        try (
                Connection connection = ConnectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT);
        ) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getCategoryId());
            preparedStatement.setInt(7, product.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (
                Connection connection = ConnectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT);
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProductDTO> search(String name, Integer categoryId) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        try (
                Connection connection = ConnectionDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_PRODUCT);
        ) {
            preparedStatement.setString(1, "%" + name + "%");
            if (categoryId == null) {
                preparedStatement.setNull(2, Types.INTEGER);
                preparedStatement.setNull(3, Types.INTEGER);
            } else {
                preparedStatement.setInt(2, categoryId);
                preparedStatement.setInt(3, categoryId);
            }
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String productName = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                int category_Id = resultSet.getInt("category_id");
                String categoryName = resultSet.getString("category_name");

                ProductDTO productDTO = new ProductDTO(id,productName,price,quantity,color,description,category_Id,categoryName);
                productDTOList.add(productDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productDTOList;
    }
}