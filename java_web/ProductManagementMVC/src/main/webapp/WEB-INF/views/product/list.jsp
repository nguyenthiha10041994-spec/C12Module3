<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Danh sách sản phẩm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2 class="text-center mb-4">
        DANH SÁCH SẢN PHẨM
    </h2>
    <form action="${pageContext.request.contextPath}/products"
          method="get"
          class="row mb-3">
        <input type="hidden"
               name="action"
               value="search">
        <div class="col-md-4">
            <input type="text"
                   class="form-control"
                   name="name"
                   placeholder="Nhập tên sản phẩm..."
                   value="${name}">
        </div>
        <div class="col-md-3">
            <select class="form-select"
                    name="categoryId">
                <option value="">
                     Chọn danh mục
                </option>
                <c:forEach items="${categoryList}" var="category">
                    <option value="${category.id}">
                            ${category.name}
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="col-md-2">
            <button class="btn btn-primary">
                Tìm kiếm
            </button>
        </div>
    </form>
    <div class="mb-3">
        <a href="${pageContext.request.contextPath}/products?action=create"
           class="btn btn-success">
            Thêm mới
        </a>
    </div>
    <table class="table table-bordered table-hover">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Tên sản phẩm</th>
            <th>Giá</th>
            <th>Số lượng</th>
            <th>Màu sắc</th>
            <th>Mô tả</th>
            <th>Danh mục</th>
            <th>Sửa</th>
            <th>Xóa</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${productList}" var="product">
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.quantity}</td>
                <td>${product.color}</td>
                <td>${product.description}</td>
                <td>${product.categoryName}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/products?action=edit&id=${product.id}"
                       class="btn btn-warning">
                        Sửa
                    </a>
                </td>
                <td>
                    <button type="button"
                            class="btn btn-danger"
                            data-bs-toggle="modal"
                            data-bs-target="#deleteModal"
                            onclick="deleteInfo('${product.id}','${product.name}')">
                        Xóa
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- Modal Xóa -->
    <div class="modal fade" id="deleteModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">
                        XÁC NHẬN XÓA
                    </h5>
                    <button type="button"
                            class="btn-close"
                            data-bs-dismiss="modal">
                    </button>
                </div>
                <div class="modal-body">
                    <p>
                        <strong>ID:</strong>
                        <span id="deleteId"></span>
                    </p>
                    <p>
                        <strong>Tên sản phẩm:</strong>
                        <span id="deleteName"></span>
                    </p>
                    <p class="text-danger">
                        Bạn có chắc muốn xóa sản phẩm này?
                    </p>
                </div>
                <div class="modal-footer">
                    <button type="button"
                            class="btn btn-secondary"
                            data-bs-dismiss="modal">
                        Hủy
                    </button>
                    <a id="confirmDelete"
                       class="btn btn-danger">
                        Xóa
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function deleteInfo(id, name) {
        document.getElementById("deleteId").innerText = id;
        document.getElementById("deleteName").innerText = name;
        document.getElementById("confirmDelete").href =
            "${pageContext.request.contextPath}/products?action=delete&id=" + id;
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>