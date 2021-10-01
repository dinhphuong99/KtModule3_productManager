<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete customer</title>
</head>
<body>
<h1>Delete customer</h1>
<p>
    <a href="/products">Back to product list</a>
</p>
<form method="post" >
    <h3>Are you sure?</h3>
    <fieldset>
        <legend>Customer information</legend>
        <table>
            <tr>
                <td>Name: </td>
                <td>
                    <input type="text" value="<c:out value="${product.productName}"/>"/>
                </td>
            </tr>
            <tr>
                <td>Price: </td>
                <td><input type="text" value="<c:out value="${product.price}"/>"/></td>
            </tr>
            <tr>
                <td>Quantity: </td>
                <td>
                    <input type="text" value="<c:out value="${product.quantity}"/>"/>
                </td>
            </tr>
            <tr>
                <td>Color: </td>
                <td><input type="text" value="<c:out value="${product.color}"/>"/></td>
            </tr>

            <tr>
                <td>Category: </td>
                <td><input type="text" value="<c:out value="${product.category}"/>"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="Delete product"></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>