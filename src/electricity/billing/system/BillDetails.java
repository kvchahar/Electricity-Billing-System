package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

import electricity.database.Conn;
import net.proteanit.sql.DbUtils;

public class BillDetails extends JFrame {

    BillDetails(String meter) {

        setSize(700, 650);
        setLocation(400, 150);

        getContentPane().setBackground(Color.WHITE);

        JTable table = new JTable();

        try {
            Conn con = new Conn();
            String query = "select * from bill where meter_no = '" + meter + "'";
            ResultSet resultSet = con.statement.executeQuery(query);

            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 700, 650);
        add(scrollPane);

        setVisible(true);
    }

    public static void main(String[] args) {
        new BillDetails("");
    }
}