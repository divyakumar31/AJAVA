package practical4;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author DIVYA
 */
public class FirstServlet extends HttpServlet {

    private static final double[] TAX_BRACKETS = {250000, 500000, 1000000};
    private static final double[] TAX_RATES = {0.10, 0.20, 0.30};

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        PrintWriter out = res.getWriter();
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        int age = Integer.parseInt(req.getParameter("age"));
        String gender = req.getParameter("gender");
        double income = Double.parseDouble(req.getParameter("income"));

        out.println("<style>body {background-color: aliceblue;font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif; font-weight: 500;}h1{color: blue; margin: 15px 5px;}p{font-size:20px;margin: 5px;}</style>");
        out.println("<h1>Details of " + name + "</h1>");
        out.println("<p>Age: <b>" + age + "</b></p>");
        out.println("<p>Address: <b>" + address + "</b></p>");
        out.println("<p>Email: <b>" + email + "</b></p>");
        out.println("<p>Gender: <b>" + gender + "</b></p>");
        out.println("<p>Income: <b>" + income + "</b></p>");

        double taxableIncome = income; // Assuming no deductions

        // Calculate tax based on income brackets
        double tax = 0;
        for (int i = 0; i < TAX_BRACKETS.length; i++) {
            if (taxableIncome > TAX_BRACKETS[i]) {
                tax += (TAX_BRACKETS[i] - (i > 0 ? TAX_BRACKETS[i - 1] : 0)) * TAX_RATES[i];
                taxableIncome -= TAX_BRACKETS[i];
            } else {
                tax += taxableIncome * TAX_RATES[i];
                break;
            }
        }

        out.println("<p style='color: red;'>Taxable Income: <b>" + taxableIncome + "</b></p>");
        out.println("<p style='color: red;'>Calculated Tax: <b>" + tax + "</b></p>");
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        doGet(req, res);
    }
}
