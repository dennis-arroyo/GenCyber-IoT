package logic;

import gui.CredentialsController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLAccess {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public String[] connect(String ip, String username, String password, String[] countryInfo) {
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
                String query = "SELECT * FROM Users WHERE User = '" + username +"'";

                resultSet = statement.executeQuery(query);
                //writeResultSet(resultSet);
                if(resultSet.next()){
                    query = "Select * from Country";
                    resultSet = statement.executeQuery(query);
                    while (resultSet.next()){
                        System.out.println("---------");
                        System.out.println("Language: " + resultSet.getString("Language"));
                        System.out.println(("Culture: " + resultSet.getString("Culture")));
                        System.out.println(("Tourist Attraction: " + resultSet.getString("TouristAttraction")));
                        System.out.println(("Continent: " + resultSet.getString("Continent")));
                        System.out.println("---------");
                        countryInfo[0] = resultSet.getString("Language");
                        countryInfo[1] = resultSet.getString("Culture");
                        countryInfo[2] = resultSet.getString("TouristAttraction");
                        countryInfo[3] = resultSet.getString("Continent");
                    }
                }


            } catch (SQLException e) {
                System.out.println(e);

                e.getMessage();
            }
        } catch (Exception e) {
            e.getMessage();


        } finally {
            close();
            return countryInfo;
        }
    }
    // You need to close the resultSet
    public void close() {
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