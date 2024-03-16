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
public class RangeFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("RangeFilter Execute");
        int num1 = Integer.parseInt(req.getParameter("FirstNumber"));
        int num2 = Integer.parseInt(req.getParameter("SecondNumber"));
//        number >= 0 && number <= 100
        if(inRange(num1) && inRange(num2)) {
            chain.doFilter(req, res);
        } else {
            res.getWriter().println("Invalid Number Range");
        }
        System.out.println("RangeFilter Execute");
    }

    private boolean inRange(int num){
        return (num >= 0 && num <= 1000);
    }
    
    @Override
    public void destroy() {}
    
}
