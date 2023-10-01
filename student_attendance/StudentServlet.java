```java
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int id = Integer.parseInt(request.getParameter("id"));

        Student student = new Student(id, name, email);
        saveStudent(student);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = Student.getStudent(id);
        request.setAttribute("student", student);
        request.getRequestDispatcher("/student.jsp").forward(request, response);
    }

    private void saveStudent(Student student) {
        try {
            Connection con = dbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO students (id, name, email) VALUES (?, ?, ?)");
            ps.setInt(1, student.getId());
            ps.setString(2, student.getName());
            ps.setString(3, student.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```
