<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Simple Calculator</title>
</head>
<body>
<h2>Simple Calculator</h2>
<form action="calculator" method="post">
    <label>First Operand:</label>
    <input type="number" step="any" name="firstOperand" required>
    <br><br>
    <label>Operator:</label>
    <select name="operator">
        <option value="+">Addition (+)</option>
        <option value="-">Subtraction (-)</option>
        <option value="*">Multiplication (*)</option>
        <option value="/">Division (/)</option>
    </select>
    <br><br>
    <label>Second Operand:</label>
    <input type="number" step="any" name="secondOperand" required>
    <br><br>
    <button type="submit">Calculate</button>
</form>
<hr>
<c:if test="${not empty result}">
    <h3>Result: ${result}</h3>
</c:if>

<c:if test="${not empty error}">
    <h3 style="color:red">${error}</h3>
</c:if>
</body>
</html>