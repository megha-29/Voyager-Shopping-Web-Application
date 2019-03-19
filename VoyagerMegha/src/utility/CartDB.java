/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import data.Cart;
import data.OrderDetails;
import data.Orders;
import data.Products;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static utility.ProductDB.selectProduct;

public class CartDB {
    
    
    public static int insert(Cart cart) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO cart (product_id,email,quantity) "
                + "VALUES (?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, cart.getProduct_id());
            ps.setString(2, cart.getEmail());
            ps.setInt(3, cart.getQuantity());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    
    public static ArrayList<Products> getCartItems(String email) {
        // add code that returns an ArrayList<User> object of all users in the User table
        ArrayList<Products> cartItems = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM cart where email =?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            Products product = null;
            while (rs.next()) {
                product = selectProduct(rs.getInt("product_id"));
                product.setAvailableQuantity(rs.getInt("quantity"));
                cartItems.add(product);
            }
            return cartItems;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int update(Cart cart) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE cart SET "
                + "quantity = ? "
                + "WHERE email = ?"
                + " and  product_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, cart.getQuantity());
            ps.setString(2, cart.getEmail());
            ps.setInt(3, cart.getProduct_id());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int delete(Cart cart) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM cart "
                + "WHERE email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, cart.getEmail());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int insert(Orders order) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "INSERT INTO orders (product_id,email,quantity,orderId) "
                + "VALUES (?,?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, order.getProduct_id());
            ps.setString(2, order.getEmail());
            ps.setInt(3, order.getQuantity());
            ps.setInt(4, order.getOrderId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int insert(OrderDetails orderdetails) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "INSERT INTO orderdetails (orderId,email,orderDate,total) "
                + "VALUES (null,?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, orderdetails.getEmail());
            ps.setTimestamp(2, orderdetails.getOrderDate());
            ps.setDouble(3, orderdetails.getTotal());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static ArrayList<Cart> selectCart(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM cart "
                + "WHERE email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            Cart cart = null;
            ArrayList<Cart> items = new ArrayList<>();
            while(rs.next()) {
                cart = new Cart();
                cart.setEmail(email);
                cart.setProduct_id(rs.getInt("product_id"));
                cart.setQuantity(rs.getInt("quantity"));
                cart.setPrice();
                items.add(cart);
            }
            return items;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static OrderDetails selectLatestOrderDetails() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM orderdetails order by orderId DESC LIMIT 1";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            OrderDetails orderDetails = null;
            if (rs.next()) {
                orderDetails = new OrderDetails();
                orderDetails.setEmail(rs.getString("email"));
                orderDetails.setOrderId(rs.getInt("orderId"));
                orderDetails.setTotal(rs.getDouble("total"));
                orderDetails.setOrderDate(rs.getTimestamp("orderDate"));
            }
            return orderDetails;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    
    public static int deleteProduct(Cart cart) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM cart "
                + "WHERE email = ?"
                + " and product_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, cart.getEmail());
            ps.setInt(2, cart.getProduct_id());    
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
