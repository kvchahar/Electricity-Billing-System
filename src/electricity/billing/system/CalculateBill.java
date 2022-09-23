package electricity.billing.system;


import electricity.database.Conn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CalculateBill extends JFrame implements ActionListener {

    JTextField unitsTextField;
    JButton submit, cancel;
    JLabel nameLabel, addressLabel;
    Choice meterNumber, month;

    CalculateBill() {
        setSize(700, 500);
        setLocation(400, 150);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(173, 216, 230));
        add(panel);

        JLabel heading = new JLabel("Calculate Electricity Bill");
        heading.setBounds(100, 10, 400, 25);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        panel.add(heading);

        JLabel meterNumberLabel = new JLabel("Meter Number");
        meterNumberLabel.setBounds(100, 80, 100, 20);
        panel.add(meterNumberLabel);

        meterNumber = new Choice();

        try {
            Conn con = new Conn();
            ResultSet rs = con.statement.executeQuery("select * from customer");
            while (rs.next()) {
                meterNumber.add(rs.getString("meter_no")); // fetching meter number from database
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        meterNumber.setBounds(240, 80, 200, 20);
        panel.add(meterNumber);

        JLabel name = new JLabel("Name");
        name.setBounds(100, 120, 100, 20);
        panel.add(name);

        nameLabel = new JLabel("");
        nameLabel.setBounds(240, 120, 100, 20);
        panel.add(nameLabel);

        JLabel address = new JLabel("Address");
        address.setBounds(100, 160, 100, 20);
        panel.add(address);

        addressLabel = new JLabel();
        addressLabel.setBounds(240, 160, 200, 20);
        panel.add(addressLabel);

        try {
            Conn con = new Conn();
            ResultSet rs = con.statement.executeQuery("select * from customer where meter_no = '" + meterNumber.getSelectedItem() + "'");
            while (rs.next()) {
                // set name as static on page, fetching from database
                nameLabel.setText(rs.getString("name"));

                // set name as static on page, fetching from database
                addressLabel.setText(rs.getString("address"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // addItemListener is used to fetch the data with the help of meterNumber as a primary key.
        meterNumber.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    Conn con = new Conn();
                    ResultSet rs = con.statement.executeQuery("select * from customer where meter_no = '" + meterNumber.getSelectedItem() + "'");
                    while (rs.next()) {
                        nameLabel.setText(rs.getString("name"));
                        addressLabel.setText(rs.getString("address"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        JLabel cityLabel = new JLabel("Units Consumed");
        cityLabel.setBounds(100, 200, 100, 20);
        panel.add(cityLabel);

        unitsTextField = new JTextField();
        unitsTextField.setBounds(240, 200, 200, 20);
        panel.add(unitsTextField);

        JLabel stateLabel = new JLabel("Month");
        stateLabel.setBounds(100, 240, 100, 20);
        panel.add(stateLabel);

        month = new Choice();
        month.setBounds(240, 240, 200, 20);
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
        panel.add(month);

        submit = new JButton("Submit");
        submit.setBounds(120, 350, 100, 25);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        panel.add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(250, 350, 100, 25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        panel.add(cancel);

        setLayout(new BorderLayout());

        add(panel, "Center");

        ImageIcon imagePath = new ImageIcon(ClassLoader.getSystemResource("icon/hicon2.jpg"));
        Image scaledImage = imagePath.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(scaledImage);
        JLabel finalImage = new JLabel(image);
        add(finalImage, "West");

        getContentPane().setBackground(Color.WHITE);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String meter = meterNumber.getSelectedItem();
            String units = unitsTextField.getText();
            String selectedMonth = month.getSelectedItem();

            int totalBill = 0;
            int unit_consumed = Integer.parseInt(units);

            // fetch values from tax table to calculate the total bill
            String query = "select * from tax";

            try {
                Conn con = new Conn();
                ResultSet resultSet = con.statement.executeQuery(query);

                // calculate total bill
                while (resultSet.next()) {
                    totalBill += unit_consumed * Integer.parseInt(resultSet.getString("cost_per_unit"));
                    totalBill += Integer.parseInt(resultSet.getString("meter_rent"));
                    totalBill += Integer.parseInt(resultSet.getString("service_charge"));
                    totalBill += Integer.parseInt(resultSet.getString("service_tax"));
                    totalBill += Integer.parseInt(resultSet.getString("swacch_bharat_cess"));
                    totalBill += Integer.parseInt(resultSet.getString("fixed_tax"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // to store the total bill
            String query2 = "insert into bill values('" + meter + "', '" + selectedMonth + "', '" + units + "', '" + totalBill + "', 'Not Paid')";

            try {
                Conn con = new Conn();
                con.statement.executeUpdate(query2);
                JOptionPane.showMessageDialog(null, "Customer Bill Updated Successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new CalculateBill();
    }
}
