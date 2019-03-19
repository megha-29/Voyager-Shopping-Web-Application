<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Voyager-Home</title>

        <link rel="stylesheet" type = "text/css" href="styles/homeStyle.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <link rel=”stylesheet” type = "text/css" href=”styles/bootstrap-select.min.css”>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script> 
        <script src=”js/bootstrap-select.min.js”></script>

    </head>
    <body>
        <div id="wrapper">
            <div class="container1">
                <div class="logo">
                    <img src="images/logo.PNG" alt="logo"/>
                    <c:if test="${theUser == null}">
                        <div style="float:right; margin-right: 10px; margin-top: 10px; margin-right: 21px;">Welcome, Guest!</div>
                    </c:if>
                    <c:if test="${theUser != null}">
                        <div style="float:right; margin-right: 10px; margin-top: 10px; margin-right: 21px;">Welcome, <strong>${theUser.firstName}</strong></div>
                    </c:if>
                </div>
                <nav class="navbar navbar-inverse">
                    <div class="container-fluid">

                        <ul class="nav navbar-nav">
                            <li class="active"><a href="user?action=home">Home</a></li>

                        </ul>

                        <c:if test="${theUser == null}">


                            <ul class="nav navbar-nav navbar-right">


                                <li style="color: #fff; font-weight: bold; border-right: 1px solid #fefefe; "><a href="login.jsp" style="color: #FFD700;"> Log in/Signup </a></li>
                            </ul>
                        </c:if>
                        <c:if test="${theUser != null}">




                            <ul class="nav navbar-nav navbar-right">

                                <li style="color: #fff; font-weight: bold; border-right: 1px solid #fefefe; "><a href="product?action=cart"> Cart</a></li>
                                <li style="color: #fff; font-weight: bold; border-right: 1px solid #fefefe; "><a href="user?action=logout"> LogOut</a></li>
                            </ul>
                        </c:if>
                    </div>
                </nav>

            </div>

            <div class="col-sm-9">
                <h3>Products</h3>
                <div class="flowers">
                    <div class="flower">
                        <div class="grid2">	
                            <c:forEach var="product" items="${productList}">
                                <form action="product?action=addtocart" method="post">
                                    <input type="hidden" name="product_id" value="<c:out value='${product.product_id}'/>">
                                    <div class="grid2content">
                                        <div class="imagediv"><img src="data:image/jpeg;base64,${product.imageData}" alt="image"/></div>
                                        <div class="contentofimage">
                                            <div class="contentofimage">
                                                <h4>${product.product_name}</h4>
                                                <b>${product.description}</b><br>
                                                <b>Available in stock: </b> ${product.availableQuantity}<br> 
                                                <b>Price: $</b>${product.price}<br>
                                                <div>  
                                                    <div>  
                                                        <c:if test="${theUser != null}">
                                                            <input class="addtocart" type="submit" value="addtocart" name="submit">
                                                            <select class="productQuantity selectpicker" data-width="fit" name="quantity" id= "productQuantity" >
                                                                <c:forEach var="i" begin="1" end="${product.availableQuantity}">
                                                                    <option value="<c:out value='${i}'/>">${i}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </c:if>    
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>	
        <div class="footer">
            <div class="footer-position">
                <span class="copyright"> Copyright © VoyagerMegha</span>
            </div>    
        </div>  
    </body>
</html>
