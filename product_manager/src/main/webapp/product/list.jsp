<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Banking Manager</title>
    <script type="text/javascript" src="jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="../resources/js/jquery.validate.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'/>
    <style>
        body {
            /*background-image: url("https://i2.wp.com/data.kenhsinhvien.net/files/2012/10/24/KenhSinhVien-tuyetroi15.gif");*/
            background-color: honeydew;
        }

        label.error {
            color: red;
        }
    </style>
    <script type="text/javascript"
            src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
    <script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

</head>
<body>
<div class="container">
    <div class="table-title">
        <div class="row">
            <div class="col-sm-5" style="color: red ">
                <h2><i class="fa fa-university" aria-hidden="true"></i>PRODUCT MANAGER</h2>
            </div>
            <div class="col-sm-7">
                <div class="box">
                    <a href="/products?action=create" class="btn btn-success"><i class="fa fa-plus-square-o"
                                                                                 aria-hidden="true"></i> <span>Add New Product</span></a>

                    <form class="sbox" action="${pageContext.request.contextPath}/products?action=search" method="get">
                        <input class="stext" type="text" name="q" placeholder="Tìm kiếm theo tên...">
                        <a class="sbutton" type="submit" href="javascript:void(0);">
                            <i class="fa fa-search"></i>
                        </a>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <table class="table table-striped table-hover table-light">
        <thead>
        <tr>
            <th class="table-warning">Product Name</th>
            <th class="table-warning">Price</th>
            <th class="table-warning">Quantity</th>
            <th class="table-warning">Color</th>
            <th class="table-warning">Category</th>
            <th colspan="5" class="table-warning" style="text-align: center">Action</th>
        </tr>
        </thead>

        <c:forEach var="user" items="${listProduct}">
            <tr>
                <td><c:out value="${user.getId()}"/></td>
                <td><c:out value="${user.getProductName()}"/></td>
                <td><c:out value="${user.getPrice()}"/></td>
                <td><c:out value="${user.getQuantity()}"/></td>
                <td><c:out value="${user.getColor()}"/></td>
                <td><c:out value="${user.getCategory()}"/></td>
                <td><a class="btn btn-info" href="/products?action=edit&id=${user.id}" title="Edit">
                    <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                </a></td>
                <td><a class="btn btn-danger" id="deleteAll" href="/products?action=delete&id=${user.id}"
                       title="Delete">
                    <i class="fa fa-trash-o" aria-hidden="true"></i>
                </a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>