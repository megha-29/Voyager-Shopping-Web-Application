package utility;

import java.sql.*;
import java.util.ArrayList;

import data.Products;

public class ProductDB {

    public static int insert(Products product) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO products (product_id,product_name,description,availableQuantity,price,added_by,image) "
                + "VALUES (null, ?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
           // ps.setInt(1, null);
            ps.setString(1, product.getProduct_name());
            ps.setString(2, product.getDescription());
            ps.setInt(3, product.getAvailableQuantity());
            ps.setDouble(4, product.getPrice());
            ps.setString(5, product.getAdded_by());
            Blob blob = new javax.sql.rowset.serial.SerialBlob(product.getImage());
            ps.setBlob(6, blob);

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int update(Products product) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE products SET "
                + "availableQuantity = ? "
                + "WHERE product_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, product.getAvailableQuantity());
            ps.setInt(2, product.getProduct_id());
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int delete(Products product) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM products "
                + "WHERE product_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, product.getProduct_id());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    public static ArrayList<Products> selectProducts() {
        // add code that returns an ArrayList<User> object of all users in the User table
        ArrayList<Products> productList = new ArrayList<Products>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM products";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            Products product = null;
            while (rs.next()) {
                product = new Products();
                product.setProduct_id(rs.getInt("product_id"));
                product.setProduct_name(rs.getString("product_name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setAvailableQuantity(rs.getInt("availableQuantity"));
                Blob blob = rs.getBlob("image");
                int blobLength = (int) blob.length();
                byte[] blobAsBytes = blob.getBytes(1, blobLength);
                //release the blob and free up memory. (since JDBC 4.0)
                blob.free();
                product.setImage(blobAsBytes);
                productList.add(product);
            }
            return productList;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList<Products> selectProducts(String email) {
        // add code that returns an ArrayList<User> object of all users in the User table
        ArrayList<Products> productList = new ArrayList<Products>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM products where added_by =?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            Products product = null;
            while (rs.next()) {
                product = new Products();
                product.setProduct_id(rs.getInt("product_id"));
                product.setProduct_name(rs.getString("product_name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setAvailableQuantity(rs.getInt("availableQuantity"));
                product.setAdded_by(email);
                Blob blob = rs.getBlob("image");
                int blobLength = (int) blob.length();
                byte[] blobAsBytes = blob.getBytes(1, blobLength);
                //release the blob and free up memory. (since JDBC 4.0)
                blob.free();
                product.setImage(blobAsBytes);
                productList.add(product);
            }
            return productList;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static Products selectProduct(int product_id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM products "
                + "WHERE product_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, product_id);
            rs = ps.executeQuery();
            Products product = null;
            if (rs.next()) {
                product = new Products();
                product.setProduct_name(rs.getString("product_name"));
                product.setProduct_id(product_id);
                product.setAvailableQuantity(rs.getInt("availableQuantity"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setAdded_by(rs.getString("added_by"));
                Blob blob = rs.getBlob("image");
                int blobLength = (int) blob.length();
                byte[] blobAsBytes = blob.getBytes(1, blobLength);
                //release the blob and free up memory. (since JDBC 4.0)
                blob.free();
                product.setImage(blobAsBytes);
            }
            return product;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
}
