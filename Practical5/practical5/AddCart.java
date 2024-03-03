package practical5;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.net.URLDecoder;

/**
 *
 * @author DIVYA
 */
@WebServlet(name = "AddCart", urlPatterns = {"/AddCart"})
public class AddCart extends HttpServlet {
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        SmartPhone phone = new SmartPhone();
        String smartphone = req.getParameter("SmartPhone");
        int quantities = Integer.parseInt(req.getParameter("quantities"));
        
        // Get cookies from request
        Cookie cookies[] = req.getCookies();
        boolean flag = true;
        
        for(Cookie c: cookies){
            // If any item in cart then update the cookie
            if (c.getName().equals("items")) {
                phone.addItem(smartphone, quantities);
                c.setValue(URLEncoder.encode( phone.toString(), "UTF-8" ));
                res.addCookie(c);
                flag = false;
            }
        }
        
        // If 0 items in cart then add new cookie.
        if(flag){
            phone.addItem(smartphone, quantities);
            // URLEncoder.encode( valeu, "UTF-8" );
            Cookie c1 = new Cookie("items",  URLEncoder.encode( phone.toString(), "UTF-8" ));
            res.addCookie(c1);
            // res.addCookie(c1);
        }
        out.println("<script> alert('"+ smartphone +" added Successfully.') </script>");
        res.sendRedirect("products.html");
    }
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.sendRedirect("login.html");
    }
}
