<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.example.ss10_baitap1.model.Customer" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%
    List<Customer> customers = new ArrayList<>();

    customers.add(new Customer("Mai Văn Hoàn","1983-08-20","Hà Nội","a.jpg"));
    customers.add(new Customer("Nguyễn Văn Nam","1983-08-21","Bắc Giang","b.jpg"));
    customers.add(new Customer("Nguyễn Thái Hòa","1983-08-22","Nam Định","c.jpg"));
    customers.add(new Customer("Trần Đăng Khoa","1983-08-17","Hà Tây","d.jpg"));
    customers.add(new Customer("Nguyễn Đình Thi","1983-08-19","Hà Nội","a.jpg"));

    request.setAttribute("customers", customers);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách khách hàng</title>

    <style>
        body{
            font-family: Arial, sans-serif;
        }

        h1{
            text-align:center;
        }

        table{
            width:80%;
            margin:auto;
            border-collapse:collapse;
        }

        th,td{
            border:1px solid black;
            padding:10px;
            text-align:center;
        }

        img{
            width:80px;
            height:80px;
        }
    </style>
</head>

<body>

<h1>Danh sách khách hàng</h1>

<table>

    <tr>
        <th>Tên</th>
        <th>Ngày sinh</th>
        <th>Địa chỉ</th>
        <th>Ảnh</th>
    </tr>

    <c:forEach items="${customers}" var="customer">

        <tr>
            <td>${customer.name}</td>
            <td>${customer.birthday}</td>
            <td>${customer.address}</td>
            <td>
                <img src="images/${customer.image}" alt="Customer">
            </td>
        </tr>

    </c:forEach>

</table>

</body>
</html>