package com.example.ss9_bai1.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/display-discount")
public class DiscountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        double discount = Double.parseDouble(request.getParameter("discount"));

        double discountAmount = price * discount / 100;
        double discountPrice = price - discountAmount;

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Discount Result</title></head>");
        out.println("<body>");

        out.println("<h2>Discount Result</h2>");
        out.println("<p>Product Description: " + description + "</p>");
        out.println("<p>List Price: " + price + "</p>");
        out.println("<p>Discount Percent: " + discount + "%</p>");
        out.println("<p>Discount Amount: " + discountAmount + "</p>");
        out.println("<p>Discount Price: " + discountPrice + "</p>");

        out.println("<br>");
        out.println("<a href='index.jsp'>Back</a>");

        out.println("</body>");
        out.println("</html>");
    }
}