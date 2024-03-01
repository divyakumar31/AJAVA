/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practical4;

import jakarta.servlet.Servlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

/**
 *
 * @author acer
 */
public class FirstServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        out.print(username);
        out.print(password);

        Cookie c1 = new Cookie("username", username);
        Cookie c2 = new Cookie("password", password);
        res.addCookie(c1);
        res.addCookie(c2);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/divy?useSSL=false";
            Connection con = DriverManager.getConnection(url, "admin", "Admin@123456");
            Statement st = con.createStatement();
            String query = "SELECT password FROM users WHERE username='" + username + "'";

            ResultSet rs = st.executeQuery(query);
            String datapassword = "";
            while(rs.next()){
                datapassword = rs.getString(1);
            }
            if (datapassword.equals(password)){
                
                res.sendRedirect("products.html");
            }
            else {
                res.sendRedirect("index.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        doGet(req, res);
    }
}
