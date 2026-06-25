<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Discount Calculator</title>
</head>
<body>

<h2>Product Discount Calculator</h2>

<form action="display-discount" method="post">

    <p>
        <label>Product Description:</label><br>
        <input type="text" name="description" required>
    </p>

    <p>
        <label>List Price:</label><br>
        <input type="number" name="price" step="0.01" required>
    </p>

    <p>
        <label>Discount Percent:</label><br>
        <input type="number" name="discount" step="0.01" required>
    </p>

    <button type="submit">Calculate Discount</button>

</form>

</body>
</html>