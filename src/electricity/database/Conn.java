package electricity.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conn {

    public Connection con;
    public Statement statement;

    public Conn() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs", "root", "");
        statement = con.createStatement();
    }
}
