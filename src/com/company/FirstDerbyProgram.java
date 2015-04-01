package com.company;

import java.sql.*;

public class FirstDerbyProgram {

    private static String protocol = "jdbc:derby:";
    private static String dbName = "firstDB";

    //  Database credentials - for embedded, usually defaults. A client-server DB would need to authenticate connections
    private static final String USER = "username";
    private static final String PASS = "password";

    public static void main(String[] args) {
        Statement statement = null;
        Connection conn = null;
        ResultSet rs = null;
        try{
            conn = DriverManager.getConnection(protocol + dbName + ";create=true", USER, PASS);
            statement = conn.createStatement();
        try {
            //Create a table in the database
            String createTableSQL = "CREATE TABLE Dogs (Name varchar(30), Age int, Breed VARCHAR(25))";
            statement.executeUpdate(createTableSQL);
            System.out.println("Created Dogs table");
        } catch (SQLException se) {
            System.out.println("Dogs table already exists");
        }

                //Add some data
                String addDataSQL = "INSERT INTO Dogs VALUES ('Poppy', 3, 'Black Lab')";
                statement.executeUpdate(addDataSQL);
                addDataSQL = "INSERT INTO Dogs VALUES ('Stan', 4, 'Golden Retriever')";
                statement.executeUpdate(addDataSQL);
                addDataSQL = "INSERT INTO Dogs VALUES ('Meg', 2, 'Pug')";
                statement.executeUpdate(addDataSQL);
                addDataSQL = "INSERT INTO Dogs VALUES ('Zelda', 5, 'Great Dane')";
                statement.executeUpdate(addDataSQL);
                System.out.println("Added two rows of data");

            //Fetch all the data and display it.
            String fetchAllDataSQL = "SELECT * FROM Dogs";
            rs = statement.executeQuery(fetchAllDataSQL);
            while (rs.next()) {
                String dogName = rs.getString("Name");
                int age = rs.getInt("Age");
                String breed = rs.getString("Breed");
                System.out.println("Dog name = " + dogName + " dog age = " + age + " dog breed = " + breed);
            }

            String deleteTableSQL = "DROP TABLE dogs";
            statement.executeUpdate(deleteTableSQL);
            System.out.println("Deleted dog table");

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //A finally block runs whether an exception is thrown or not. Close resources and tidy up whether this code worked or not.
            try {
                if (statement != null) {
                    statement.close();
                    System.out.println("Statement closed");
                }
            } catch (SQLException se){
                //Closing the connection could throw an exception too
                se.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();  //Close connection to database
                    System.out.println("Database connection closed");
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("End of program");
    }
}
