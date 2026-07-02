package com.example.productmanagementmvc.controller;

import com.example.productmanagementmvc.dto.ProductDTO;
import com.example.productmanagementmvc.entity.Category;
import com.example.productmanagementmvc.entity.Product;
import com.example.productmanagementmvc.service.CategoryService;
import com.example.productmanagementmvc.service.ICategoryService;
import com.example.productmanagementmvc.service.IProductService;
import com.example.productmanagementmvc.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private IProductService productService = new ProductService();
    private ICategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            case "search":
                searchProduct(request, response);
                break;
            default:
                showList(request, response);
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createProduct(request, response);
                break;
            case "edit":
                updateProduct(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/products");
                break;
        }
    }
    private void showList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ProductDTO> productDTOList = productService.findAll();
        List<Category> categoryList = categoryService.findAll();
        request.setAttribute("productList", productDTOList);
        request.setAttribute("categoryList", categoryList);
        request.getRequestDispatcher("/WEB-INF/views/product/list.jsp")
                .forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Category> categoryList = categoryService.findAll();
        request.setAttribute("categoryList", categoryList);
        request.getRequestDispatcher("/WEB-INF/views/product/add.jsp")
                .forward(request, response);
    }
    private void createProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        Product product = new Product(name, price, quantity, color, description, categoryId);
        productService.create(product);
        response.sendRedirect(request.getContextPath() + "/products");
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        List<Category> categoryList = categoryService.findAll();
        request.setAttribute("product", product);
        request.setAttribute("categoryList", categoryList);
        request.getRequestDispatcher("/WEB-INF/views/product/edit.jsp")
                .forward(request, response);
    }
    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        Product product = new Product(id, name, price, quantity, color, description, categoryId);
        productService.update(product);
        response.sendRedirect(request.getContextPath() + "/products");
    }
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productService.delete(id);
        response.sendRedirect(request.getContextPath() + "/products");
    }
    private void searchProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String category = request.getParameter("categoryId");
        Integer categoryId = null;
        if (category != null && !category.isEmpty()) {
            categoryId = Integer.parseInt(category);
        }
        List<ProductDTO> productDTOList = productService.search(name, categoryId);
        List<Category> categoryList = categoryService.findAll();
        request.setAttribute("productList", productDTOList);
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("name", name);
        request.setAttribute("categoryId", categoryId);
        request.getRequestDispatcher("/WEB-INF/views/product/list.jsp")
                .forward(request, response);
    }
}
