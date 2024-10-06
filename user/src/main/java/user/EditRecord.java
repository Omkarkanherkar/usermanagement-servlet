package user;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
@WebServlet("/edit")

public class EditRecord extends HttpServlet {




	    private static final long serialVersionUID = 1L;
	    private static final String query = "update user set  name=?, email=?, mobile=?, dob=?, gender=?, city=?  where id=?";

	    // Handle POST requests
	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
	        res.setContentType("text/html");
	        PrintWriter pw = res.getWriter();
	        pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
            int id = Integer.parseInt(req.getParameter("id")); // Get the ID from the request
            String name=req.getParameter("name");
            String email=req.getParameter("email");
            String mobile=req.getParameter("mobile");
            String dob=req.getParameter("dob");
            String gender=req.getParameter("gender");
            String city=req.getParameter("city");


	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver"); // Load the JDBC driver
	            
	            // Use try-with-resources to manage database resources
	            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usermgmt", "root", "Vaish2002");
	                 PreparedStatement ps = con.prepareStatement(query)) {
	                
	                ps.setString(1, name); // Set the ID in the query
	                ps.setString(2, email); // Set the ID in the query
	                ps.setString(3, mobile);// Set the ID in the query
	                ps.setString(4, dob); // Set the ID in the query
	                ps.setString(5, gender); // Set the ID in the query
	                ps.setString(6, city); // Set the ID in the query
	                ps.setInt(7, id); // Set the ID in the query
	                
	                int count = ps.executeUpdate(); // Execute the delete operation
	                pw.println("<div class='card' style='margin:auto; width:300px; margin-top:100px;'>");
	                if (count == 1) {
	                    pw.println("<h2 class='bg-info text-light text-center' style='padding: 15px;'>"
	                             + "Record Edited successfully!</h2>");
	                } else {
	                    pw.println("<h2 class='bg-danger text-light text-center' style='padding: 15px;'>"
	                             + "Record update failed!</h2>");
	                }

	                // Create buttons for navigation
	                pw.println("<div class='d-flex flex-column justify-content-center align-items-center' style='height: 50px; margin-top: 20px;'>");
	                pw.println("<a href='home.html'>");
	                pw.println("<button class='btn btn-primary'>HOME</button>");
	                pw.println("</a>");
	                pw.println("</div>");
	                pw.println("&nbsp;&nbsp;");
	                pw.println("<div class='d-flex flex-column justify-content-center align-items-center' style='height: 50px; margin-top: 20px;'>");
	                pw.println("<a href='showdata'>");
	                pw.println("<button class='btn btn-primary'>Show Data</button>");
	                pw.println("</a>");
	                pw.println("</div>");
	                pw.println("</div>");
	            }
	        } catch (SQLException e) {
	            pw.println("<h2 class='bg-warning text-dark text-center' style='padding: 15px;'>"
	                     + "Error: " + e.getMessage() + "</h2>");
	            e.printStackTrace();
	        } catch (NumberFormatException e) {
	            pw.println("<h2 class='bg-warning text-dark text-center' style='padding: 15px;'>"
	                     + "Invalid ID format!</h2>");
	        } catch (ClassNotFoundException e) {
	            pw.println("<h2 class='bg-warning text-dark text-center' style='padding: 15px;'>"
	                     + "Error loading database driver: " + e.getMessage() + "</h2>");
	        } finally {
	            pw.close(); // Ensure the PrintWriter is closed
	        }
	    }
	}


