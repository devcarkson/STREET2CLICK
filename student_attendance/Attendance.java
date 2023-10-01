```java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Attendance {
    private int id;
    private int studentId;
    private Date date;
    private boolean isPresent;

    public Attendance() {
    }

    public Attendance(int id, int studentId, Date date, boolean isPresent) {
        this.id = id;
        this.studentId = studentId;
        this.date = date;
        this.isPresent = isPresent;
    }

    public int getId() {
        return id;
    }

    public int getStudentId() {
        return studentId;
    }

    public Date getDate() {
        return date;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPresent(boolean present) {
        this.isPresent = present;
    }

    public static Attendance getAttendance(int id) {
        Attendance attendance = null;
        try {
            Connection con = dbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM attendance WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                attendance = new Attendance(rs.getInt("id"), rs.getInt("studentId"), rs.getDate("date"), rs.getBoolean("isPresent"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendance;
    }
}
```
