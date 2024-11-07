package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/sample";
        String username = "root";
        String password = "Karpaga@2005";

        Connection con = DriverManager.getConnection(url, username, password);

        if (con != null) {
            System.out.println("Connection Established");
        } else {
            System.out.println("Connection not established");
        }

        Scanner sc = new Scanner(System.in);

        // Read the users
        String query = "select * from users";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            System.out.println("Username: " + rs.getString("username"));
            System.out.println("Password: " + rs.getString("password"));
            System.out.println("User Id: " + rs.getString("user_id"));
        }

        // Update the values
        System.out.println("Enter the user details you want to update");
        String name1 = sc.nextLine();
        String pass1 = sc.nextLine();
        int id1 = sc.nextInt();

        String sql1 = "UPDATE users SET username = ?, password = ? WHERE user_id = ?";
        PreparedStatement ps1 = con.prepareStatement(sql1);

        // Set the parameters for the query
        ps1.setString(1, name1); // New username
        ps1.setString(2, pass1); // New password
        ps1.setInt(3, id1);      // user_id of the record to update

        int res1 = ps1.executeUpdate(); // Execute the update
        if (res1 > 0) {
            System.out.println("User details updated successfully!");
        } else {
            System.out.println("No user found with the specified ID.");
        }

        // Deleting a row
        System.out.println("Enter the user Id you want to delete:");
        int id2 = sc.nextInt();
        String sql2 = "DELETE FROM users WHERE user_id = ?";
        PreparedStatement ps2 = con.prepareStatement(sql2);

        // Set the parameter for the query
        ps2.setInt(1, id2); // user_id of the record to delete

        int res2 = ps2.executeUpdate(); // Execute the delete
        if (res2 > 0) {
            System.out.println("User deleted successfully!");
        } else {
            System.out.println("No user found with the specified ID.");
        }

        // Selecting a particular row
        System.out.println("Enter the user id:");
        int id3 = sc.nextInt();
        String sql3 = "SELECT * FROM users WHERE user_id = ?";
        PreparedStatement ps3 = con.prepareStatement(sql3);

        // Set the parameter for the query
        ps3.setInt(1, id3); // user_id of the record to select
        ResultSet rs3 = ps3.executeQuery(); // Execute the query

        // Check if a record was found and retrieve data
        if (rs3.next()) {
            String username3 = rs3.getString("username");
            String password3 = rs3.getString("password");
            int userId3 = rs3.getInt("user_id");

            System.out.println("User ID: " + userId3);
            System.out.println("Username: " + username3);
            System.out.println("Password: " + password3);
        } else {
            System.out.println("No user found with the specified ID.");
        }
    }
}

