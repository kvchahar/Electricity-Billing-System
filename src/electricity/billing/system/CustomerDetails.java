package electricity.billing.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;

import electricity.database.Conn;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class CustomerDetails extends JFrame implements ActionListener{
    JTable table;
    JButton print;

    CustomerDetails(){
        super("Customer Details");

        setSize(1200, 650);
        setLocation(200, 150);

        table = new JTable();

        try {
            Conn con = new Conn();
            ResultSet rs = con.statement.executeQuery("select * from customer");

            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        print = new JButton("Print");
        print.addActionListener(this);
        add(print, "South"); // print button added towards south


        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            table.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new CustomerDetails();
    }
}