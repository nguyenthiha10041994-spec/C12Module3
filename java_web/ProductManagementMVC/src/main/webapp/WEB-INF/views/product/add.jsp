<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Thêm sản phẩm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2 class="text-center mb-4">
        THÊM MỚI SẢN PHẨM
    </h2>
    <form action="${pageContext.request.contextPath}/products?action=create"
          method="post">
        <div class="mb-3">
            <label class="form-label">Tên sản phẩm</label>
            <input type="text"
                   class="form-control"
                   name="name">
        </div>
        <div class="mb-3">
            <label class="form-label">Giá</label>
            <input type="number"
                   class="form-control"
                   name="price">
        </div>
        <div class="mb-3">
            <label class="form-label">Số lượng</label>
            <input type="number"
                   class="form-control"
                   name="quantity">
        </div>
        <div class="mb-3">
            <label class="form-label">Màu sắc</label>
            <input type="text"
                   class="form-control"
                   name="color">
        </div>
        <div class="mb-3">
            <label class="form-label">Mô tả</label>
            <textarea class="form-control"
                      name="description"></textarea>
        </div>
        <div class="mb-3">
            <label class="form-label">Danh mục</label>
            <select class="form-select" name="categoryId">
                <c:forEach items="${categoryList}" var="category">
                    <option value="${category.id}">
                            ${category.name}
                    </option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-success">
            Thêm mới
        </button>
        <a href="${pageContext.request.contextPath}/products"
           class="btn btn-secondary">
            Quay lại
        </a>
    </form>
</div>
</body>
</html>