package practical5;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
/**
 *
 * @author DIVYA
 */
public class Login extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        out.print(username);
        out.print(password);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/dbname?useSSL=false";
            Connection con = DriverManager.getConnection(url, "username", "password");
            Statement st = con.createStatement();
            String query = "SELECT password FROM users WHERE username='" + username + "'";

            ResultSet rs = st.executeQuery(query);
            String datapassword = "";
            while(rs.next()){
                datapassword = rs.getString(1);
            }
            if (datapassword.equals(password)){
                Cookie c1 = new Cookie("username", username);
                Cookie c2 = new Cookie("password", password);
                res.addCookie(c1);
                res.addCookie(c2);
                res.sendRedirect("products.html");
            }
            else {
                res.sendRedirect("index.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        doGet(req, res);
    }
}
