<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Cart</title>
        <link rel="stylesheet" type = "text/css" href="styles/cartStyle.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </head>
    <body>
        <c:if test="${productquantityerror != null}">
            <div class="alert alert-info">
                <strong>Warning!</strong> ${productquantityerror}
            </div>
        </c:if>
        <div id="wrapper">
            <div class="logo">
                <img src="images/logo.PNG" alt="logo"/>
            </div>
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">

                    <ul class="nav navbar-nav">
                        <li><a href='user?action=home'>Home</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">

                        <li style="color: #fff; font-weight: bold; border-right: 1px solid #fefefe;"><a href="user?action=logout"> Log Out </a></li>
                    </ul>
                </div>
            </nav>
            <div class='container'>
                <table class='table table-hover' border='0' cellpadding='10'>
                    <tr><th>Cart Items</th></tr>
                    <tr><th>Product Name</th><th>Quantity</th><th>Price</th></tr>

                    <c:forEach var="product" items="${cartItems}">
                        <tr class='table-row'>

                        <form action="product?action=updateCart" method="post">
                            <td>${product.product_name}</td>
                            <td>${product.availableQuantity}</td>
                            <td>$${product.price * product.availableQuantity}</td>

                            <input type="hidden" name="product_id" value="<c:out value='${product.product_id}'/>"> 
                            <td> <select class="productQuantity" name="productQuantity" id= "productQuantity" >
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                </select>    </td>  
                            <td><input class='addtocart' type ="submit" formaction='product?action=deletecartproduct' value="Delete"></td>
                            <td><input class="addtocart" type="submit" value="Update" name="Update"></td>
                        </form>
                        </tr>
                    </c:forEach>

                    <tr><td><br><b> Total Price: </b></td>
                        <td><br></td>
                        <td><br></td>
                        <td><br>$${cartTotal}</td>

                        <td><br><a href='product?action=buy'>Place Order</a></td>
                    </tr>

                </table>



            </div>
        </div>
        <div class="footer">
            <div class="footer-position">
                <img class="branding" src="" style="width: 30px; height: 30px;"><span class="copyright"> Copyright © VoyagerMegha</span>     
            </div>    
        </div>

    </body>
</html>
