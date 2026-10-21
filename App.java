import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) throws SQLException {
        try (
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/college", "root", "root");
            Statement stmt = con.createStatement()
        ) {
            stmt.executeUpdate("DELETE FROM Students");
            stmt.executeUpdate("INSERT INTO Students VALUES (1, 'John', 'Doe', 'Computer Science', 20)");
            stmt.executeUpdate("INSERT INTO Students VALUES (2, 'Jane', 'Smith', 'Mathematics', 22)");
            stmt.executeUpdate("INSERT INTO Students VALUES (3, 'Alice', 'Brown', 'Physics', 21)");
            System.out.println("Records inserted.");

            try (ResultSet rs = stmt.executeQuery("SELECT * FROM Students")) {
                System.out.println("\nID | First Name | Last Name | Major             | Age");
                System.out.println("-----------------------------------------------------------");
                while (rs.next()) {
                    System.out.printf("%-3d| %-11s| %-10s| %-18s| %d%n",
                        rs.getInt("student_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("major"),
                        rs.getInt("age")
                    );
                }
            }
        }
    }
}
