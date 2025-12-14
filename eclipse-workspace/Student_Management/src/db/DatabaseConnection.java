package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    
    private static final String URL = "jdbc:mysql://localhost:3306/student_management";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    
    private static Connection connection = null;

  
    private DatabaseConnection() {
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
           
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                
                System.out.println("Database connected successfully!");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found!");
            System.err.println("Make sure mysql-connector-j JAR is added to Build Path");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database connection failed!");
            System.err.println("Check if:");
            System.err.println("  1. MySQL is running in XAMPP");
            System.err.println("  2. Database 'student_management' exists");
            System.err.println("  3. Username and password are correct");
            e.printStackTrace();
        }
        return connection;
    }

        public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection!");
            e.printStackTrace();
        }
    }
    
        public static void main(String[] args) {
        System.out.println("Testing database connection...");
        
        Connection conn = DatabaseConnection.getConnection();
        
        if (conn != null) {
            System.out.println("SUCCESS! Database connection is working!");
            DatabaseConnection.closeConnection();
        } else {
            System.out.println("FAILED! Could not connect to database.");
            System.out.println("Please check:");
            System.out.println("1. Is MySQL running in XAMPP?");
            System.out.println("2. Did you create the student_management database?");
            System.out.println("3. Is the JDBC driver added to Build Path?");
        }
    }
}