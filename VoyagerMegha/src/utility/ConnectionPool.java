package utility;
//importing the package
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

public class ConnectionPool {

    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;
    
    private static Connection connection = null;
    
    private static String connectionString="jdbc:mysql://localhost:3306/voyager";
    private static String sqlUser="root";
    private static String sqlPass="Utd5529!";

    private ConnectionPool() {
     
         try {
        	 Class.forName("com.mysql.jdbc.Driver");         //loading the driver
             String dbName = "voyager";
             String userName = "root";
             String password = "Utd5529!";
             String hostname = "localhost";
             String port = "3306";
             String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
             
            connection =  DriverManager.getConnection(jdbcUrl); //Estabilishing the connection
        }
        catch(ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized ConnectionPool getInstance() {
        //if (pool == null) {
            pool = new ConnectionPool();
        //}
        return pool;
    }

    public Connection getConnection() {
    	try {
			System.out.println(connection.isValid(10) + " here");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return connection;
    }

    public void freeConnection(Connection c) {
        try {
            c.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
