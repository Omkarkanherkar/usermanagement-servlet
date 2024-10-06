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
@WebServlet("/editurl")
public class EditServlet extends HttpServlet {

	    private static final long serialVersionUID = 1L;
	    private static final String query = "SELECT name, email, mobile, dob, gender, city FROM user where id=?";

	    // Handle GET requests
	    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
	        doPost(req, res); // Forwarding GET to POST for convenience
	    }

	    // Handle POST requests
	    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
	        res.setContentType("text/html");
	        PrintWriter pw = res.getWriter();
	        pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
	        //logic to get id
	        int id=Integer.parseInt(req.getParameter("id"));

	        try {
	            // Load JDBC driver
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            pw.println("<h2>Error loading database driver: " + e.getMessage() + "</h2>");
	            return;
	        }

	        // Try-with-resources to manage database resources
	        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usermgmt", "root", "Vaish2002");
	             PreparedStatement ps = con.prepareStatement(query);){
	        		ps.setInt(1, id);
	        		
	        		
	        		
	             ResultSet rs = ps.executeQuery() ;{
                  rs.next();
                  pw.println("<div style='margin:auto;width:1000px;margin-top:100px;'>");
                  pw.println("<form action='edit?id=" + id + "' method='post'>"); // Added space before 'method'
                  pw.println("<table class='table table-hover table-striped'>");
                  pw.println("<tr>");
                  pw.println("<td>Name</td>");
                  pw.println("<td><input type='text' name='name' value='"+rs.getString(1)+"'></td>");
                  pw.println("</tr>");
                  pw.println("<tr>");
                  pw.println("<td>Email</td>");
                  pw.println("<td><input type='email' name='email' value='"+rs.getString(2)+">'</td>");
                  pw.println("</tr>");
                  pw.println("<tr>");
                  pw.println("<td>Mobile</td>");
                  pw.println("<td><input type='text' name='mobile' value='"+rs.getString(3)+".'</td>");
                  pw.println("</tr>");
                  pw.println("<tr>");
                  pw.println("<td>dob</td>");
                  pw.println("<td><input type='date' name='dob' value='"+rs.getString(4)+">'</td>");
                  pw.println("</tr>");
                  pw.println("<tr>");
                  pw.println("<td>Gender</td>");
                  pw.println("<td><input type='text' name='gender' value='"+rs.getString(5)+">'</td>");
                  pw.println("</tr>");
                  pw.println("<tr>");
                  pw.println("<td>City</td>");
                  pw.println("<td><input type='text' name='city' value='"+rs.getString(6)+">'</td>");
                  pw.println("</tr>");
                  pw.println("<tr>");
                  pw.println("<td><button type='submit' class= 'btn btn-outline-success'>Edit</button></td>");
                  pw.println("<td><button type='reset' class='btn btn-outline-danger'>Cancel</button></td>");
                  
                  pw.println("</tr>");
                  pw.println("</table>");
                  pw.println("</form>");

	           
	            
	          
	           
	            pw.println("<div class='d-flex flex-column justify-content-center align-items-center' style='height: 50px; margin-top: 20px;'>");
	            pw.println("<a href='home.html'>");
	            pw.println("<button class='btn btn-primary'>HOME</button>");
	            pw.println("</a>");
	            pw.println("</div>");
	            pw.println("</div>");

	        } 
	        }catch (SQLException e) {
	            pw.println("<h2>Error retrieving data: " + e.getMessage() + "</h2>");
	            e.printStackTrace();
	        }
	    }
}
	


