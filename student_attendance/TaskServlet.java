```java
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int assignedTo = Integer.parseInt(request.getParameter("assignedTo"));
        String status = request.getParameter("status");

        try {
            Connection con = dbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO tasks (name, description, assignedTo, status) VALUES (?, ?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, assignedTo);
            ps.setString(4, status);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("dashboard.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Task task = Task.getTask(id);
        request.setAttribute("task", task);
        request.getRequestDispatcher("task.jsp").forward(request, response);
    }
}
```
