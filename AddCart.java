package practical4;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author DIVYA
 */

@WebServlet(name = "AddCart", urlPatterns = {"/AddCart"})
public class AddCart extends HttpServlet {
    SmartPhone phone = new SmartPhone();
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        String smartphone = req.getParameter("SmartPhone");
        System.out.println(smartphone);
        
        
        Cookie cookies[] = req.getCookies();
        boolean flag = true;
        for(Cookie c: cookies){
            if (c.getName().equals("items")) {
                phone.addItem(smartphone);
                c.setValue(phone.toString());
                res.addCookie(c);
                System.out.println(phone.toString());
                flag = false;
            }
        }
        
        if(flag){
            phone.addItem(smartphone);
            Cookie c1 = new Cookie("items", phone.toString());
            res.addCookie(c1);
        }
        
        res.sendRedirect("products.html");
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        doGet(req, res);
    }
}