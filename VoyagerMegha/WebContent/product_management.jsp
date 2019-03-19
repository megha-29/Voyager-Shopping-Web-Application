<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Product Management</title>
        <link rel="stylesheet" href="styles/pmStyle.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div id="wrapper">
            <div class="logo">
                <img src="images/logo.PNG" alt="logo"/>
                <div style="float:right; margin-right: 10px; margin-top: 10px; margin-right: 21px;">Welcome, <strong>${theAdmin.firstName}</strong></div>
            </div>
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">
                 <ul class="nav navbar-nav">
                            <li class="active"><a href="user?action=home">Home</a></li>
                        </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li style="color: #fff; font-weight: bold; border-right: 1px solid #fefefe;"><a href="user?action=logout"> Log Out </a></li>
                    </ul>
                </div>
            </nav>
            <div class='container'>
                <a class= 'addbutton' href='addproduct.jsp'>Add a Product</a>

                <table class='table table-hover' border='0' cellpadding='10'>

                    <tr><th>Image</th><th>Name</th><th>Product ID</th><th>Description</th><th>Price</th><th>Available Quantity</th><th></th></tr>
                    <c:forEach var="product" items="${userproductList}">
                        <form name="myForm" class="form-inline" role="role" action="product?action=updateproduct" method="post">
                            <input type="hidden" name="product_id" id="product_id" value="<c:out value='${product.product_id}'/>">
                            <tr class='table-row'>
                                <td><img src="data:image/jpeg;base64,${product.imageData}" alt="image"></td>
                                <td>${product.product_name}</td>
                                <td>${product.product_id}</td>
                                <td>${product.description}</td>
                                <td>$${product.price}</td>
                                <td><input type="text" name="quantity" id="quantity" value="<c:out value='${product.availableQuantity}'/>"></td>
                                <td><input type="submit" class="btn btn-default" value="Update" name="Uupdate"></td>
                            </tr>
                        </form>
                    </c:forEach>
                </table>

            </div>
            <div class="footer">
                <div class="footer-position">
                    <span class="copyright"> Copyright © VoyagerMegha</span>
                </div>    
            </div>
        </div>
    </body>
</html>