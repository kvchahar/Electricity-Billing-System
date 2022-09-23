package electricity.billing.system;

import electricity.database.Conn;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class PayBill extends JFrame implements ActionListener {

    Choice month;
    JButton pay, back;
    String meter;

    PayBill(String meter) {
        this.meter = meter;
        setLayout(null);
        setBounds(300, 150, 900, 600);

        JLabel heading = new JLabel("Electicity Bill");
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setBounds(120, 5, 400, 30);
        add(heading);

        JLabel meterNumberLabel = new JLabel("Meter Number");
        meterNumberLabel.setBounds(35, 80, 200, 20);
        add(meterNumberLabel);

        JLabel meterNumber = new JLabel("");
        meterNumber.setBounds(300, 80, 200, 20);
        add(meterNumber);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(35, 140, 200, 20);
        add(nameLabel);

        JLabel name = new JLabel("");
        name.setBounds(300, 140, 200, 20);
        add(name);

        JLabel monthName = new JLabel("Month");
        monthName.setBounds(35, 200, 200, 20);
        add(monthName);

        month = new Choice();
        month.setBounds(300, 200, 200, 20);
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

        JLabel unitsLabel = new JLabel("Units");
        unitsLabel.setBounds(35, 260, 200, 20);
        add(unitsLabel);

        JLabel units = new JLabel("");
        units.setBounds(300, 260, 200, 20);
        add(units);

        JLabel totalBillLabel = new JLabel("Total Bill");
        totalBillLabel.setBounds(35, 320, 200, 20);
        add(totalBillLabel);

        JLabel bill = new JLabel("");
        bill.setBounds(300, 320, 200, 20);
        add(bill);

        JLabel statusLabel = new JLabel("Status");
        statusLabel.setBounds(35, 380, 200, 20);
        add(statusLabel);

        JLabel status = new JLabel("");
        status.setBounds(300, 380, 200, 20);
        status.setForeground(Color.RED);
        add(status);

        try {
            Conn con = new Conn();
            ResultSet resultSet = con.statement.executeQuery("select * from customer where meter_no = '" + meter + "'");
            while (resultSet.next()) {
                meterNumber.setText(meter);
                name.setText(resultSet.getString("name"));
            }

            resultSet = con.statement.executeQuery("select * from bill where meter_no = '" + meter + "' AND month = 'January'");
            while (resultSet.next()) {
                units.setText(resultSet.getString("units"));
                bill.setText(resultSet.getString("totalbill"));
                status.setText(resultSet.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        month.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ae) {
                try {
                    Conn con = new Conn();
                    ResultSet resultSet = con.statement.executeQuery("select * from bill where meter_no = '" + meter + "' AND month = '" + month.getSelectedItem() + "'");
                    while (resultSet.next()) {
                        units.setText(resultSet.getString("units"));
                        bill.setText(resultSet.getString("totalbill"));
                        status.setText(resultSet.getString("status"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        pay = new JButton("Pay");
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        pay.setBounds(100, 460, 100, 25);
        pay.addActionListener(this);
        add(pay);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(230, 460, 100, 25);
        back.addActionListener(this);
        add(back);

        getContentPane().setBackground(Color.WHITE);

        ImageIcon billImagePath = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image scaleImage = billImagePath.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(scaleImage);
        JLabel finalImage = new JLabel(image);
        finalImage.setBounds(400, 120, 600, 300);
        add(finalImage);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == pay) {
            try {
                Conn con = new Conn();
                // status sets to be 'paid'.
                con.statement.executeUpdate("update bill set status = 'Paid' where meter_no = '" + meter + "' AND month='" + month.getSelectedItem() + "'");
            } catch (Exception e) {
                e.printStackTrace();
            }
            setVisible(false);
//            new Paytm(meter);
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new PayBill("");
    }
}