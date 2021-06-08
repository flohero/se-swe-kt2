package at.fhooe;

import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcExample {

    private final static String CONNECTION_STRING = "jdbc:mariadb://127.0.0.1:3306/PhoneBookDb";

    public static void main(String[] args) {
//        Connection connection = null;
        try (var connection = DriverManager.getConnection(CONNECTION_STRING, "root", "toor")) {
//            connection = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/PhoneBookDb", "root", "toor");

            // Insert to db
            String sqlInsert =
                    "INSERT INTO Person (first_name, last_name, address, phone_number) VALUES ('Test', 'test', 'Street', '0043680')";
            var stmt = connection.createStatement();
            stmt.execute(sqlInsert);

            // Select from db with prepared statement
            var prepStmt = connection.prepareStatement("SELECT first_name FROM Person WHERE last_name LIKE ?");
            // set prepared statement parameter (the '?')
            prepStmt.setString(1, "%es%");
            // try with resources - resultSet is closed automatically even if there is an exception in the block
            try (var resultSet = prepStmt.executeQuery()) {
                while (resultSet.next()) {
                    System.out.println("flirt name: " + resultSet.getString("first_name"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
//        finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//            }
//        }
    }
}