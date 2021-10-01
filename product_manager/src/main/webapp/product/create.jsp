<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new customer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="table-title mb-4">
        <div class="row">
            <div class="row">
                <div class="col-sm-6">
                    <h1>Create product</h1>
                    <h3>
                        <a href="${pageContext.request.contextPath}/products"> Black list product</a>
                    </h3>
                </div>
            </div>
        </div>
    </div>
    <form id="frmCreateProduct" method="post">
        <fieldset class="row g-3">
            <div class="col-md-6">
                <label class="form-label">Product name</label>
                <input type="text" class="form-control" name="product_name">
            </div>
            <div class="col-md-6">
                <label class="form-label">Price</label>
                <input type="text" class="form-control" name="price">
            </div>
            <div class="col-md-6">
                <label class="form-label">Quantity</label>
                <input type="tel" class="form-control" name="quantity">
            </div>
            <div class="col-md-6">
                <label class="form-label">Color</label>
                <input type="text" class="form-control" name="color">
            </div>
            <div class="col-md-6">
                <label class="form-label">Category</label>
                <input type="text" class="form-control" name="category">
            </div>
            <div class="col-md-12">
                <button type="submit" id="btnCreateCustomer" class="btn btn-outline-primary">
                    <i class="fa fa-plus" aria-hidden="true"></i>
                    Create customer
                </button>
            </div>
        </fieldset>
    </form>
    <div class="message">
        <c:if test='${requestScope["success"] != null}'>
            <div class="alert alert-success" role="alert" style="position: fixed; bottom: 0; right: 0; left: 0">
                    ${requestScope["success"]}
            </div>

        </c:if>
        <c:if test='${requestScope["error"] != null}'>
            <div class="alert alert-danger" role="alert" style="position: fixed; bottom: 0; right: 0; left: 0">
                <strong> ${requestScope["error"]}</strong>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
