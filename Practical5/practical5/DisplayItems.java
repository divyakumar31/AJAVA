package practical5;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

/**
 *
 * @author DIVYA
 */
public class DisplayItems extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        Cookie cookies[] = req.getCookies();
        for(Cookie c: cookies){
            if(!c.getName().equals("password")){
                out.println(c.getName() +": "+ URLDecoder.decode(c.getValue(), "UTF-8"));
            }
        }
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        doGet(req, res);
    }
}
