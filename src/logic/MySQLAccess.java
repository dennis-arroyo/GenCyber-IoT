package de.vogella.mysql.first;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MySQLAccess {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private String ip = " ";
    private String Table = "Name";
    private String username = " ";
    private boolean found = false;
    private  String password = "";


    public void setEmail(String username) {
        this.username = username;
    }

    public void setUrl(String url) {

        if ((!url.equals("192.168.0.132"))) {
            if (!url.equals("192.168.0.104")) {
                System.out.println("Wrong IP!");
                System.exit(1);
            } else {
                this.ip = url;
            }
        }
    }


    public void connect() throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            try {


                //if ((!connect.isClosed()) && connect != null) { System.out.println(("Eror"));}

                connect = DriverManager.getConnection("jdbc:mysql://" + ip + "/Test", "pi", "raspberry");
                if (connect == null){ System.out.println("Error connecting to database"); }

                System.out.println("Connected!");


                // Statements allow to issue SQL queries to the database
                statement = connect.createStatement();

                // Result set get the result of the SQL query
                String query = "SELECT * FROM Users WHERE User = '" + username + "'";


                resultSet = statement.executeQuery(query);
                //writeResultSet(resultSet);



                while (resultSet.next() != false){
                    System.out.println("Email: " + resultSet.getString("Email"));
                    System.out.println(("Password: " + resultSet.getString("Password")));
                }




            } catch (SQLException e) {
                System.out.println(e);

                e.getMessage();
            }
        } catch (Exception e) {
            e.getMessage();


        } finally {
            close();
        }
    }




    private void writeMetaData(ResultSet resultSet) throws SQLException {
        //  Now get some metadata from the database
        // Result set get the result of the SQL query

        System.out.println("The columns in the table are: ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
            System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
        }
    }

    private void writeResultSet(ResultSet resultSet) throws SQLException {
//        // ResultSet is initially before the first data set
//        while (resultSet.next()) {
//            // It is possible to get the columns via name
//            // also possible to get the columns via the column number
//            // which starts at 1
//            // e.g. resultSet.getSTring(2);
//            String user = resultSet.getString("myuser");
//            String website = resultSet.getString("webpage");
//            String summary = resultSet.getString("summary");
//            Date date = resultSet.getDate("datum");
//            String comment = resultSet.getString("comments");
//            System.out.println("User: " + user);
//            System.out.println("Website: " + website);
//            System.out.println("summary: " + summary);
//            System.out.println("Date: " + date);
//            System.out.println("Comment: " + comment);
//        }
    }

    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

}