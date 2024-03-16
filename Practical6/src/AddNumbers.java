package Practical6;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author DIVYA
 */
public class AddNumbers extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
        int num1 = Integer.parseInt(req.getParameter("FirstNumber"));
        int num2 = Integer.parseInt(req.getParameter("SecondNumber"));
        
        res.getWriter().println("Sum of " + num1 + " and " + num2 + " is " + (num1+num2));
    }
    
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
        doGet(req, res);
    }
    
}
