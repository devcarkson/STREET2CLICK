```java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Task {
    private int id;
    private String name;
    private String description;
    private int assignedTo;
    private String status;

    public Task() {
    }

    public Task(int id, String name, String description, int assignedTo, String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.assignedTo = assignedTo;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getAssignedTo() {
        return assignedTo;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAssignedTo(int assignedTo) {
        this.assignedTo = assignedTo;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static Task getTask(int id) {
        Task task = null;
        try {
            Connection con = dbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tasks WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                task = new Task(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getInt("assignedTo"), rs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }
}
```
