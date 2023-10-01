import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the username and password from the request parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Perform any necessary validation or authentication logic here
        // For example, you can check if the username and password match a user in your database

        // Redirect -the user to a different page based on the authentication result
        if (authenticated(username, password)) {
            response.sendRedirect("dashboard.jsp"); // Replace "dashboard.jsp" with the desired page
        } else {
            response.sendRedirect("login.jsp?error=1"); // Replace "login.jsp" with the login page and add any error parameter if needed
        }
    }

    private boolean authenticated(String username, String password) {
        // Implement your authentication logic here
        // For example, you can check if the username and password match a user in your database
        // Return true if authenticated, false otherwise
        return username.equals("admin") && password.equals("password");
    }
}