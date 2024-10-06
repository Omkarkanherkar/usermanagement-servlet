package user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/showdata")
public class ShowUser extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String query = "SELECT id,name, email, mobile, dob, city, gender FROM user";

    // Handle GET requests
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        doPost(req, res); // Forwarding GET to POST for convenience
    }

    // Handle POST requests
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
        // Background image style
        pw.println("<style>");
        pw.println("body {");
        pw.println("    background-image: url('images/data.jpg');"); // Change this to your image path
        pw.println("    background-size: cover;");
        pw.println("    background-position: center;");
        pw.println("    color: white;"); // Change text color for better visibility
        pw.println("}");
        pw.println("</style>");

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            pw.println("<h2>Error loading database driver: " + e.getMessage() + "</h2>");
            return;
        }

        // Try-with-resources to manage database resources
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usermgmt", "root", "Vaish2002");
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            // Print table headers
        	
        	pw.println("<div style='margin:auto;width:1000px;margin-top:100px; '>");
        	pw.println("<h1 style='text-align:center;font-family:fantasy;'>User  Information</h1>");
            pw.println("<table class='table table-hover table-striped ' style='background-color:#b0e0e6;'>");
            pw.println("<tr>");
            pw.println("<th>ID</th>");
            pw.println("<th>Name</th>");
            pw.println("<th>Email</th>");
            pw.println("<th>Mobile</th>");
            pw.println("<th>DOB</th>");
            pw.println("<th>City</th>");
            pw.println("<th>Gender</th>");
            pw.println("<th>EDIT</th>");
            pw.println("<th>DELETE</th>");
            
            pw.println("</tr>");

            // Print table rows
            while (rs.next()) {
            	pw.println("<tr>");
                // Use the correct column index based on your SELECT statement
                pw.println("<td>" + rs.getInt(1) + "</td>"); // ID
                pw.println("<td>" + rs.getString(2) + "</td>"); // Name
                pw.println("<td>" + rs.getString(3) + "</td>"); // Email
                pw.println("<td>" + rs.getString(4) + "</td>"); // Mobile
                pw.println("<td>" + rs.getString(5) + "</td>"); // DOB
                pw.println("<td>" + rs.getString(6) + "</td>"); // City
                pw.println("<td>" + rs.getString(7) + "</td>"); // City
                pw.println("<td>");
                pw.println("<form action='editurl' method='POST'>");
                pw.println("<input type='hidden' name='id' value='" + rs.getInt(1) + "'/>"); // Assuming ID is in the first column
                pw.println("<button type='submit' class='btn btn-danger'>EDIT</button>");
                pw.println("</form>");
                pw.println("</td>"); 
                pw.println("<td>");
                pw.println("<form action='deleteurl' method='POST'>");
                pw.println("<input type='hidden' name='id' value='" + rs.getInt(1) + "'/>"); // Assuming ID is in the first column
                pw.println("<button type='submit' class='btn btn-danger'>Delete</button>");
                pw.println("</form>");
                pw.println("</td>"); 
                pw.println("</tr>");
            }
          
            pw.println("</table>");
            pw.println("<div class='d-flex flex-column justify-content-center align-items-center' style='height: 50px; margin-top: 20px;'>");
            pw.println("<a href='home.html'>");
            pw.println("<button class='btn btn-primary'>HOME</button>");
            pw.println("</a>");
            pw.println("</div>");
            pw.println("</div>");

        } catch (SQLException e) {
            pw.println("<h2>Error retrieving data: " + e.getMessage() + "</h2>");
            e.printStackTrace();
        }
    }
}
