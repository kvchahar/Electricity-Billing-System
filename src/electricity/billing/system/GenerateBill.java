package electricity.billing.system;

import electricity.database.Conn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class GenerateBill extends JFrame implements ActionListener{

    String meter;
    JButton bill;

    Choice month;
    JTextArea area;
    GenerateBill(String meter) {
        this.meter = meter;

        setSize(500, 800);
        setLocation(550, 30);

        setLayout(new BorderLayout());

        JPanel panel = new JPanel();

        JLabel heading = new JLabel("Generate Bill");
        JLabel meterNumber = new JLabel(meter);

        month = new Choice();

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

        area = new JTextArea(50, 15);
        area.setText("\n\n\t--------Click on the---------\n\t Generate Bill Button to get\n\tthe bill of the Selected Month");
        area.setFont(new Font("Senserif", Font.ITALIC, 18));

        JScrollPane pane = new JScrollPane(area);

        bill = new JButton("Generate Bill");
        bill.addActionListener(this);

        panel.add(heading);
        panel.add(meterNumber);
        panel.add(month);
        add(panel, "North");

        add(pane, "Center");
        add(bill, "South");

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            Conn con = new Conn();

            String selectMonth = month.getSelectedItem();

            area.setText("\tReliance Power Limited\nELECTRICITY BILL GENERATED FOR THE MONTH\n\tOF "+month+", 2022\n\n\n");

            ResultSet resultSet = con.statement.executeQuery("select * from customer where meter_no = '"+meter+"'");

            if(resultSet.next()) {
                area.append("\n    Customer Name: " + resultSet.getString("name"));
                area.append("\n    Meter Number   : " + resultSet.getString("meter_no"));
                area.append("\n    Address             : " + resultSet.getString("address"));
                area.append("\n    City                 : " + resultSet.getString("city"));
                area.append("\n    State                : " + resultSet.getString("state"));
                area.append("\n    Email                : " + resultSet.getString("email"));
                area.append("\n    Phone                 : " + resultSet.getString("phone"));
                area.append("\n---------------------------------------------------");
                area.append("\n");
            }

            resultSet = con.statement.executeQuery("select * from meter_info where meter_no = '"+meter+"'");

            if(resultSet.next()) {
                area.append("\n    Meter Location: " + resultSet.getString("meter_location"));
                area.append("\n    Meter Type:     " + resultSet.getString("meter_type"));
                area.append("\n    Phase Code:        " + resultSet.getString("phase_code"));
                area.append("\n    Bill Type:          " + resultSet.getString("bill_type"));
                area.append("\n    Days:                " + resultSet.getString("days"));
                area.append("\n---------------------------------------------------");
                area.append("\n");
            }

            resultSet = con.statement.executeQuery("select * from tax");

            if(resultSet.next()) {
                area.append("\n");
                area.append("\n    Cost Per Unit: " + resultSet.getString("cost_per_unit"));
                area.append("\n    Meter Rent:     " + resultSet.getString("cost_per_unit"));
                area.append("\n    Service Charge:        " + resultSet.getString("service_charge"));
                area.append("\n    Service Tax:          " + resultSet.getString("service_charge"));
                area.append("\n    Swacch Bharat Cess:                " + resultSet.getString("swacch_bharat_cess"));
                area.append("\n    Fixed Tax: " + resultSet.getString("fixed_tax"));
                area.append("\n");
            }

            resultSet = con.statement.executeQuery("select * from bill where meter_no = '"+meter+"' and month='"+selectMonth+"'");

            if(resultSet.next()) {
                area.append("\n");
                area.append("\n    Current Month: " + resultSet.getString("month"));
                area.append("\n    Units Consumed:     " + resultSet.getString("units"));
                area.append("\n    Total Charges:        " + resultSet.getString("totalbill"));
                area.append("\n-------------------------------------------------------");
                area.append("\n    Total Payable: " + resultSet.getString("totalbill"));
                area.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new GenerateBill("");
    }
}
