package electricity.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conn {
    public Conn() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs", "root", "");
        Statement statement = con.createStatement();
    }
}
