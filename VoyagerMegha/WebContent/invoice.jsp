<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Invoice</title>
        <link rel="stylesheet" href="styles/style.css">
    </head>
    <body>
        <div id="wrapper">
            <div class="container1">
                <nav class="navbar navbar-inverse">
                    <div class="container-fluid">

                        <ul class="nav navbar-nav">
                            <li class="active"><a href="user?action=home">Home</a></li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>

        <header>
            <h1>Invoice</h1>
            <address>

                <p>${theUser.firstName} ${theUser.lastName}</p>
                <p>${theUser.address}</p>
                <p>${theUser.city}, ${theUser.state}, ${theUser.zip}</p>
                <p>${theUser.phone}</p>
                
            </address>
            <span><img alt="" src="logo.png"><input type="file" accept="image/*"></span>
        </header>
        <article>
            <h1>Recipient</h1>
            <address  >
                <p>Voyager - Payment Confirmation<br></p>
            </address>
            <table class="meta">
                <tr>
                    <th><span  >Invoice ID/ Order Number</span></th>
                    <td><span>${orderDetails.orderId}</span></td>
                </tr>
                <tr>
                    <th><span  >Date</span></th>
                    <td><span>${orderDetails.orderDate}</span></td>
                </tr>
                <tr>
                    <th><span  >Amount Due</span></th>
                    <td><span id="prefix"  >$</span><span>${orderDetails.total}</span></td>
                </tr>
            </table>
            <table class="inventory">
                <thead>
                    <tr>
                        <th><span  >Item</span></th>
                        <th><span  >Description</span></th>

                        <th><span  >Quantity</span></th>
                        <th><span  >Price</span></th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach var="product" items="${cartItems}">
                        <tr>
                            <td><span  >${product.product_name}</span></td>
                            <td><span  >${product.description}</span></td>

                            <td><span >${product.availableQuantity}</span></td>
                            <td><span data-prefix>$</span><span>${product.price * product.availableQuantity}</span></td>
                        </tr>
                    </c:forEach>    


                </tbody>
            </table>

            <table class="balance">

                <tr>
                    <th><span  >Amount Paid</span></th>
                    <td><span data-prefix>$</span><span  >${orderDetails.total}</span></td>
                </tr>

            </table>
        </article>
        <aside>
            <h1><span  >Additional Notes</span></h1>
            <div  >
                <!-- <p>A finance charge of 1.5% will be made on unpaid balances after 30 days.</p> -->
            </div>
        </aside>
    </body>
</html>
