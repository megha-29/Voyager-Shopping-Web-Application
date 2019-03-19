/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import data.Cart;
import data.OrderDetails;
import data.Orders;
import data.Products;
import data.User;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import static utility.CartDB.delete;
import static utility.CartDB.deleteProduct;
import static utility.CartDB.getCartItems;
import static utility.ProductDB.insert;
import static utility.CartDB.insert;
import static utility.CartDB.selectCart;
import static utility.CartDB.selectLatestOrderDetails;
import static utility.CartDB.update;
import static utility.ProductDB.selectProducts;
import static utility.ProductDB.delete;
import static utility.ProductDB.selectProduct;
import static utility.ProductDB.update;

public class ProductController extends HttpServlet {

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
            out.println("<title>Servlet ProductController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductController at " + request.getContextPath() + "</h1>");
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

        // perform action and set URL to appropriate page
        String url = "/index.jsp";

        if (action.equals("addproduct")) {
            User admin = (User) session.getAttribute("theAdmin");
            if (admin == null) {
                url = "/login.jsp";
            } else {
                Products product = new Products();
                List<FileItem> items;
                Map<String, String> fieldValues = new HashMap<>();
                try {
                    items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                    for (FileItem item : items) {
                        if (item.isFormField()) {

                            String fieldname = item.getFieldName();
                            String fieldvalue = item.getString();
                            fieldValues.put(fieldname, fieldvalue);

                        } else {
                            //String fieldname = item.getFieldName();
                            //String filename = FilenameUtils.getName(item.getName());
                            InputStream filecontent = item.getInputStream();
                            byte[] bytes = IOUtils.toByteArray(filecontent);
                            product.setImage(bytes);
                        }
                    }
                } catch (FileUploadException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }

                product.setProduct_name(fieldValues.get("name"));
                product.setPrice(Double.parseDouble(fieldValues.get("price")));
                product.setAdded_by(admin.getEmail());
                product.setAvailableQuantity(Integer.parseInt(fieldValues.get("quantity")));
                product.setDescription(fieldValues.get("description"));
                int result = insert(product);
                ArrayList<Products> productList = selectProducts(admin.getEmail());
                request.setAttribute("userproductList", productList);
                url = "/product_management.jsp";
            }
        } else if (action.equals("cart")) {
            User user = (User) session.getAttribute("theUser");
            ArrayList<Products> cartItems = getCartItems(user.getEmail());
            double total = 0;
            for (Products item : cartItems) {
                total = total + (item.getPrice() * item.getAvailableQuantity());
            }
            request.setAttribute("cartItems", cartItems);
            request.setAttribute("cartTotal", total);
            url = "/cart.jsp";
        } else if (action.equals("updateCart")) {
            User user = (User) session.getAttribute("theUser");
            Cart cart = new Cart();
            cart.setEmail(user.getEmail());
            String product_id = request.getParameter("product_id");
            String quantity = request.getParameter("productQuantity");
            cart.setProduct_id(Integer.parseInt(product_id));
            cart.setQuantity(Integer.parseInt(quantity));
            update(cart);

            ArrayList<Products> cartItems = getCartItems(user.getEmail());
            double total = 0;
            for (Products item : cartItems) {
                total = total + (item.getPrice() * item.getAvailableQuantity());
            }
            request.setAttribute("cartItems", cartItems);
            request.setAttribute("cartTotal", total);
            url = "/cart.jsp";
        } else if (action.equals("buy")) {
            boolean buyFlag = true;
            User user = (User) session.getAttribute("theUser");
            ArrayList<Cart> items = selectCart(user.getEmail());
            double total = 0;
            Products product = null;
            for (Cart item : items) {
                product = selectProduct(item.getProduct_id());
                if (product.getAvailableQuantity() < item.getQuantity()) {
                    buyFlag = false;
                    break;
                }
                total = total + item.getPrice();
            }
            if (buyFlag) {
                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setOrderDate(new Timestamp(new Date().getTime()));
                orderDetails.setEmail(user.getEmail());
                orderDetails.setTotal(total);
                insert(orderDetails);
                int orderId = selectLatestOrderDetails().getOrderId();
                orderDetails.setOrderId(orderId);
                for (Cart item : items) {
                    Orders order = new Orders();
                    order.setEmail(user.getEmail());
                    order.setProduct_id(item.getProduct_id());
                    order.setQuantity(item.getQuantity());
                    order.setOrderId(orderId);
                    insert(order);
                    product = selectProduct(item.getProduct_id());
                    product.setAvailableQuantity(product.getAvailableQuantity() - item.getQuantity());
                    update(product);
                }
                ArrayList<Products> cartItems = getCartItems(user.getEmail());
                delete(items.get(0));
                request.setAttribute("orderDetails", orderDetails);
                request.setAttribute("items", items);
                request.setAttribute("cartItems", cartItems);
                url = "/invoice.jsp";
            } else {
                ArrayList<Products> cartItems = getCartItems(user.getEmail());
                total = 0;
                for (Products item : cartItems) {
                    total = total + (item.getPrice() * item.getAvailableQuantity());
                }
                request.setAttribute("cartItems", cartItems);
                request.setAttribute("cartTotal", total);
                request.setAttribute("productquantityerror", 
                        product.getProduct_name() + " not available in enough quantity"
                                + "\n\nAvailable Quantity: " + product.getAvailableQuantity());
                url = "/cart.jsp";
            }

        } else if (action.equals("addtocart")) {
            User user = (User) session.getAttribute("theUser");
            Cart cart = new Cart();
            cart.setEmail(user.getEmail());
            String product_id = request.getParameter("product_id");
            String quantity = request.getParameter("quantity");
            cart.setProduct_id(Integer.parseInt(product_id));
            cart.setQuantity(Integer.parseInt(quantity));
            insert(cart);
            ArrayList<Products> productList = selectProducts();
            request.setAttribute("productList", productList);
            url = "/index.jsp";
        } else if (action.equals("deletecartproduct")) {
            User user = (User) session.getAttribute("theUser");
            Cart cart = new Cart();
            cart.setEmail(user.getEmail());
            String product_id = request.getParameter("product_id");
            cart.setProduct_id(Integer.parseInt(product_id));
            deleteProduct(cart);
            ArrayList<Products> cartItems = getCartItems(user.getEmail());
            double total = 0;
            for (Products item : cartItems) {
                total = total + (item.getPrice() * item.getAvailableQuantity());
            }
            request.setAttribute("cartItems", cartItems);
            request.setAttribute("cartTotal", total);
            url = "/cart.jsp";
        } else if (action.equals("updateproduct")) {
            User admin = (User) session.getAttribute("theAdmin");
            String product_id = request.getParameter("product_id");
            String quantity = request.getParameter("quantity");
            Products product = new Products();
            product.setProduct_id(Integer.parseInt(product_id));
            product.setAvailableQuantity(Integer.parseInt(quantity));
            update(product);
            ArrayList<Products> productList = selectProducts(admin.getEmail());
            request.setAttribute("userproductList", productList);
            url = "/product_management.jsp";
        }

        sc.getRequestDispatcher(url)
                .forward(request, response);
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
