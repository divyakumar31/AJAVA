package Practical6;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;

/**
 *
 * @author DIVYA
 */
public class ValidFormatFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("ValidFormateFilter Execute");
        String num1 = req.getParameter("FirstNumber");
        String num2 = req.getParameter("SecondNumber");
        
        if(num1.matches("\\d+") && num2.matches("\\d+")){
            chain.doFilter(req, res);
        } else {
            res.getWriter().println("Invalid Number Format");
        }
        System.out.println("ValidFormateFilter Done");
    }

    @Override
    public void destroy() {}
    
}
