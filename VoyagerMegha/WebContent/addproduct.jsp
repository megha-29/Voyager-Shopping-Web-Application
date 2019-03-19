<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="styles/addproductStyle.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </head>
 
    <body>
        <div id="wrapper">
            <div class="logo"><img src="images/logo.PNG" alt="LOGO here!"/>
            </div>
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">
                    <ul class="nav navbar-nav">

                        <li class="active"><a href="user?action=home">Home</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li style="color: #fff; font-weight: bold; border-right: 1px solid #fefefe; "><a href="user?action=logout"> LogOut</a></li>
                    </ul>
                </div>
            </nav>
            <div class="container container-width">
                <div class="heading">Add Product</div>
                <form name="myForm" class="form-inline" role="role" action="product?action=addproduct" method="post" enctype="multipart/form-data">
                    <table>
                        <label class="label-width">Name:</label><input type="text" name="name" id="name"><br><br>
                        <label class="label-width">Description:</label><input type="text" name="description" id="description"><br><br>
                        <label class="label-width">Price: $</label><input type="text" name="price" id="price"><br><br>
                        <label class="label-width">Quantity:</label><input type="text" name="quantity" id="quantity"><br><br>
                        <label class="label-width-image">Select image to upload:</label><input type="file" name="fileToUpload" id="fileToUpload"><br><br>
                        <a class="btn btn-default" href="product_management.jsp">Cancel</a>
                        <input type="submit" class="btn btn-default" value="Submit" name="submit">
                    </table>
                </form>

            </div>
            <div class="footer">
                <div class="footer-position">
                    <span class="copyright"> Copyright © VoyagerMegha</span>
                </div>    
            </div>
        </div>
    </body>
</html>
