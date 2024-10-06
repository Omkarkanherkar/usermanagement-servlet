package user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final static String query = "INSERT INTO user(name, email, mobile, dob, gender, city) VALUES (?, ?, ?, ?, ?, ?)";
    
    // Handle POST requests
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
        
        String name = req.getParameter("userName");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");
        String dob = req.getParameter("dob");
        String gender = req.getParameter("gender");
        String city = req.getParameter("city");
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usermgmt", "root", "Vaish2002");
             PreparedStatement ps = con.prepareStatement(query)) {
             
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, mobile);
            ps.setString(4, dob);
            ps.setString(5, gender);
            ps.setString(6, city);
            
            int count = ps.executeUpdate();
            pw.println("<div class='card' style='margin:auto; width:300px; margin-top:100px;'>");
            if (count == 1) {
                pw.println("<h2 class='bg-info text-light text-center' style='padding: 15px;'>"
                         + "Record registered successfully!</h2>");
            } else {
                pw.println("<h2 class='bg-danger text-light text-center' style='padding: 15px;'>"
                         + "Record registration failed!</h2>");
            }pw.println("<div class='d-flex flex-column justify-content-center align-items-center' style='height: 50px; margin-top: 20px;'>");
            pw.println("<a href='home.html'>");
            pw.println("<button class='btn btn-primary'>HOME</button>");
            pw.println("</a>");
            pw.println("</div>");
            pw.println("</div>");
            } catch (SQLException e) {
                pw.println("<h2 class='bg-warning text-dark text-center' style='padding: 15px;'>"
                         + "Error: " + e.getMessage() + "</h2>");
                e.printStackTrace();
            }
            pw.close();;
    }
}
