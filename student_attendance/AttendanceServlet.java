```java
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AttendanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        String date = request.getParameter("date");
        boolean isPresent = Boolean.parseBoolean(request.getParameter("isPresent"));

        try {
            Connection con = dbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO attendance (studentId, date, isPresent) VALUES (?, ?, ?)");
            ps.setInt(1, studentId);
            ps.setDate(2, new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime()));
            ps.setBoolean(3, isPresent);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Attendance attendance = Attendance.getAttendance(id);
        if (attendance != null) {
            request.setAttribute("attendance", attendance);
            request.getRequestDispatcher("/attendance.jsp").forward(request, response);
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
```
