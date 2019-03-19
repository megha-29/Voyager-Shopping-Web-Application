<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Voyager-login</title>

        <link rel="stylesheet" href="styles/loginStyle.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    </head>
    <body>
        
        <c:if test="${msg != null}">
            <div class="alert alert-info">
                <strong>Warning!</strong> ${msg}
            </div>
        </c:if>
        <div class="container">
            <div class="wrapper">
                <div class="main">
                    <div class="logo"><img src="images/logo.PNG" alt="LOGO here!"/></div>
                    <form action="user?action=login" method="post">

                        <div class="form-data">
                            <div class="form-column">

                                Email:</div><input class="form-input" type="email" name="email" id="email" placeholder="Please enter your email" required></div>
                        <div class="form-data">
                            <div class="form-column">
                                Password:</div><input class="form-input" type="password" name="password" id="password" placeholder="Password" required></div>
                        <div class="form-data">

                            <input class="submitButton form-input" type="submit" value="Login" name="login">
                            <input class="submitButton form-input" type="submit" formaction="signup.jsp" value="SignUp" name="signup" formnovalidate>
                        </div>

                    </form>
                </div>
            </div>
        </div>

        

    </body>
</html>
