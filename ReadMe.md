### About Voyager:

Spending time in the great outdoors is good for the soul. 
Though with so many gear choices and possible activities, it can be difficult to get started.
"Voyager" Shopping Web Application has a mission to make getting outside easier and more fun. So, gear up and venture out! 
It offers products for preparing your outdoor kit just the right way.

### Tech stack used for the web application:

- [ ] Frontend - Bootstrap, CSS
- [ ] Backend - JSP, Servlets, Java Model Classes
- [ ] Database - MySQL

### Key Features:

- [ ] User side - As a user, I can view, add, delete, update to the cart.
- [ ] Admin side - As an admin, I can view, add and update the product in the product list.
- [ ] Payment Confirmation - Invoice is generated to indicate the completion of payment by the user.
- [ ] Email Functionality - Mail Functionality is implemented which sends a welcome email to the 'user' email id as soon as the user does log in.
- [ ] Security - Hashing and salting is implemented and the password is stored in the form of a hash and a salt in the database.
- [ ] Uniqueness - Email ID is an unique key, the website sends an error message in case of repetition which says the 'user already exists'. Product name and Product description in 'products' table are also declared as unique keys.
- [ ] Authentication - Password and email are authenticated when user tries to log in and website sends a message 'Wrong email/pas' in all other cases. 
- [ ] Indexes - In the database, Email ID in 'user', product ID in 'products' and order ID in 'orders' are primary keys. All other tables refer to them as foreign keys.

### Website Demo

_Welcome_ Page - LOGIN PAGE

![image](https://user-images.githubusercontent.com/40745259/54705853-af57c880-4b0b-11e9-954c-94e752b565d6.png)

_HOME_ PAGE FOR USER SIDE FUNCTIONALITIES

![image](https://user-images.githubusercontent.com/40745259/54706018-f80f8180-4b0b-11e9-9d44-76660181c755.png)

_ADD_ TO CART AND UPDATE THE QUANTITY OF PRODUCT AS A USER, WHILE ADDING TO THE CART

![image](https://user-images.githubusercontent.com/40745259/54706169-41f86780-4b0c-11e9-85fb-6fa60b0c365a.png)

_CART PAGE_
![image](https://user-images.githubusercontent.com/40745259/54706339-a9aeb280-4b0c-11e9-8bf4-9f661655deeb.png)

_DELETED_ ONE PRODUCT, UPDATED ANOTHER, COULD SEE A CHANGE IN TOTAL PRICE AS WELL AS PRODUCT ORDER IN CART
![image](https://user-images.githubusercontent.com/40745259/54706462-fbefd380-4b0c-11e9-8592-297f9729cb3c.png)

_INVOICE_ GENERATED ONCE PAYMENT IS DONE
![image](https://user-images.githubusercontent.com/40745259/54706518-1fb31980-4b0d-11e9-9626-85494d7298ca.png)

_HOME_ PAGE OF WEB APPLICATION WHENEVER USER ACTION IS LOGOUT
![image](https://user-images.githubusercontent.com/40745259/54706609-512be500-4b0d-11e9-8f1e-595dc266ab8a.png)

_HOME_ PAGE FOR ADMIN SIDE FUNCTIONALITIES
![image](https://user-images.githubusercontent.com/40745259/54706712-8c2e1880-4b0d-11e9-9ebf-a61006812889.png)

_UPDATED_ ALL PRODUCTS AS ADMIN ON THE PRODUCT MANAGEMENT PAGE
![image](https://user-images.githubusercontent.com/40745259/54706895-f941ae00-4b0d-11e9-828c-e866d76bd5b4.png)

_ADD_ PRODUCT PAGE FOR ADMIN
![image](https://user-images.githubusercontent.com/40745259/54707103-53db0a00-4b0e-11e9-94dd-64474ce392de.png)

_SIGNUP_ PAGE FOR NEW USER
![image](https://user-images.githubusercontent.com/40745259/54707211-96044b80-4b0e-11e9-924f-b040d02dba49.png)

_OUR_ ORDER WE MADE ABOVE, IS UPDATED IN 'ORDER' AND 'ORDERDETAILS' TABLE AS WELL IN THE DATABASE.
![image](https://user-images.githubusercontent.com/40745259/54707346-d9f75080-4b0e-11e9-9609-85df98db19b4.png)

![image](https://user-images.githubusercontent.com/40745259/54707449-1e82ec00-4b0f-11e9-8ed3-62fbea3b1334.png)

_AS_ AN ADMIN, THE UPDATE IN THE PRODUCTS I MADE ABOVE, AS WELL AS THE ADDITION OF 'ABC' PRODUCT IS UPDATED IN THE 'PRODUCTS' TABLE IN DATABASE
![image](https://user-images.githubusercontent.com/40745259/54707555-5ee26a00-4b0f-11e9-8b70-f5ac007cc226.png)

_THE_ CART TABLE GOES EMPTY WHEN THE ORDER IS PLACED
![image](https://user-images.githubusercontent.com/40745259/54707638-8b968180-4b0f-11e9-8d2f-68155bbb3cce.png)

_THE_ USER TABLE IS STORED IN DATABASE AS FOLLOWING:
![image](https://user-images.githubusercontent.com/40745259/54707818-f47df980-4b0f-11e9-9811-831f2c7e344e.png)
![image](https://user-images.githubusercontent.com/40745259/54707862-14adb880-4b10-11e9-917e-ec295c83fa21.png)

