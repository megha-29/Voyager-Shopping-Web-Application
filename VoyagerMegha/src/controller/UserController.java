/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import data.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static utility.ProductDB.selectProducts;
import static utility.UserDB.*;

public class UserController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ServletContext sc = getServletContext();
        HttpSession session = request.getSession();
        
        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "home";  // default action
        }

        // perform action and set URL to appropriate page
        String url = "/index.jsp";
        
        if (action.equals("login")) {  
            
            try {
                String email = request.getParameter("email");
                String pass = request.getParameter("password");
                /*if(session.getAttribute("theUser") !=null || session.getAttribute("theAdmin") !=null){
                session.invalidate();
                }*/
                if(userAuth(email,pass)){
                    User user = selectUser(email);
                    if(user.getType().equals("customer")){
                        session.setAttribute("theUser",user);
                        ArrayList<Products> productList = selectProducts();
                        request.setAttribute("productList", productList);
                        url = "/index.jsp";    // the user home page
                    }
                    else{
                        session.setAttribute("theAdmin",user);
                        ArrayList<Products> productList = selectProducts();
                        productList = selectProducts(email);
                        request.setAttribute("userproductList", productList);
                        url = "/product_management.jsp";    // the admin home page
                    }
                }
                else{
                    request.setAttribute("msg", "Incorect email/pass");
                    url = "/login.jsp";
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         else if (action.equals("signup")) {
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String conf_password = request.getParameter("confirm_password");
            String phone = request.getParameter("phone");
            String addr = request.getParameter("addr");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            int zip = Integer.parseInt(request.getParameter("zip_code"));
            
            if(!conf_password.equals(password)){
                request.setAttribute("msg", "passwords do not match");
                url = "/signup.jsp";
            }
            else if(selectUser(email) != null){
                request.setAttribute("msg", "user already exists");
                url = "/signup.jsp";
            }
            else{
                User user = new User();
                user.setEmail(email);
                user.setPassword(password);
                user.setFirstName(fname);
                user.setLastName(lname);
                user.setAddress(addr);
                user.setPhone(phone);
                user.setState(state);
                user.setCity(city);
                user.setType("customer");
                user.setZip(zip);
                insert(user);
                session.setAttribute("theUser",user);
                ArrayList<Products> productList = selectProducts();
                request.setAttribute("productList", productList);
                url = "/index.jsp";
            }
        }
       
        else if (action.equals("home")) {
            
            User user = (User)session.getAttribute("theUser");
            User user_a = (User)session.getAttribute("theAdmin");
            
            
            ArrayList<Products> productList = selectProducts();
            request.setAttribute("productList", productList);
                url="/index.jsp";
            if(user_a != null){
                productList = selectProducts(user_a.getEmail());
                request.setAttribute("userproductList", productList);
                url = "/product_management.jsp";
            }            
        }
        else if(action.equals("logout")){
            User user_p = (User)session.getAttribute("theUser");
            User user_a = (User)session.getAttribute("theAdmin");
            if(user_a != null || user_p !=null){
                session.invalidate();
            }
            ArrayList<Products> productList = selectProducts();
            request.setAttribute("productList", productList);
            url = "/index.jsp";
        }

        sc.getRequestDispatcher(url).forward(request, response);
    }


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
