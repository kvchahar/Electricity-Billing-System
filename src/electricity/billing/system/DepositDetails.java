package electricity.billing.system;

import electricity.database.Conn;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import net.proteanit.sql.DbUtils;

public class DepositDetails extends JFrame implements ActionListener {

    Choice meterNumber, month;
    JTable table;
    JButton search, print;

    DepositDetails() {
        super("Deposit Details");

        setSize(700, 700);
        setLocation(400, 100);

        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel labelMeterNumber = new JLabel("Search by Meter Number");
        labelMeterNumber.setBounds(20, 20, 150, 20);
        add(labelMeterNumber);

        meterNumber = new Choice();
        meterNumber.setBounds(180, 20, 150, 20);
        add(meterNumber);

        try {
            Conn con = new Conn();
            ResultSet resultSet = con.statement.executeQuery("select * from customer");
            while (resultSet.next()) {
                meterNumber.add(resultSet.getString("meter_no")); // search by meter number
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // search by month
        JLabel monthLabel = new JLabel("Search By Month");
        monthLabel.setBounds(400, 20, 100, 20);
        add(monthLabel);

        month = new Choice();
        month.setBounds(520, 20, 150, 20);
        month.add("January");
        month.add("February");
        month.add("March");
        month.add("April");
        month.add("May");
        month.add("June");
        month.add("July");
        month.add("August");
        month.add("September");
        month.add("October");
        month.add("November");
        month.add("December");
        add(month);

        table = new JTable();

        try {
            Conn con = new Conn();
            ResultSet resultSet = con.statement.executeQuery("select * from bill");

            // table creation
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // to add scroll bar on table
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 100, 700, 600);
        add(scrollPane);

        search = new JButton("Search");
        search.setBounds(20, 70, 80, 20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        add(print);


        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            String query = "select * from bill where meter_no = '" + meterNumber.getSelectedItem() + "' and month = '" + month.getSelectedItem() + "'";
            try {
                Conn con = new Conn();
                ResultSet resultSet = con.statement.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            } catch (Exception e) {

            }
        } else {
            try {
                table.print(); // print the table, also can download the table
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new DepositDetails();
    }
}