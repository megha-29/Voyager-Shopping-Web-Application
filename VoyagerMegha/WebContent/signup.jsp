<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="styles/signupStyle.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </head>


    <body>
        <div id="wrapper">
            <div class="logo" style="background-color: #fff;">
               <img src="images/logo.PNG" alt="LOGO here!"/>
            </div>

            <div class="container container-width">
                <form action="user?action=signup" method="post" class="form-inline">
             
                    <table>
                        <label class="label-width">First Name:</label><input type="text" name="fname" id="fname" class="form-control" required><br><br>
                        <label class="label-width">Last Name:</label><input type="text" name="lname" id="lname" class="form-control" required><br><br>    
                        <label class="label-width">Username:</label><input type="text" name="email" id="email" class="form-control" required><br><br>
                        <label class="label-width">Password:</label><input type="password" name="password" class="form-control" id="password" required><br><br>
                        <label class="label-width">Confirm Password:</label><input type="password" name="confirm_password" class="form-control" id="password" required><br><br>
                        <label class="label-width">Phone:</label><input type="text" name="phone" id="phone" class="form-control" required><br><br>
                        <label class="label-width">Address:</label><input type="textarea" name="addr" class="form-control" id="addr_line1" required><br><br>
                        <label class="label-width">City:</label><input type="text" name="city" id="city" class="form-control" required><br><br>
                        <label class="label-width">State:</label><input type="text" name="state" id="state" class="form-control" required><br><br>
                        <label class="label-width">Zip Code:</label><input type="text" name="zip_code" id="zip_code" class="form-control" required><br><br>
                        <a class="btn btn-default" href="login.jsp">Cancel</a>
                        <input class="btn btn-default" type="submit" value="submit" name="Submit">
                    </table>
                </form>

            </div>
        </div>
        <div class="footer">
            <div class="footer-position">
                <span class="copyright"> Copyright © VoyagerMegha</span>
            </div>    
        </div>
    </body>
</html>




