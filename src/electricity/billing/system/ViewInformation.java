package electricity.billing.system;

import electricity.database.Conn;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class ViewInformation extends JFrame implements ActionListener {

    JButton cancel;

    ViewInformation(String meter) {
        setBounds(350, 150, 850, 650);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);


        JLabel heading = new JLabel("VIEW CUSTOMER INFORMATION");
        heading.setBounds(250, 0, 500, 40);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(heading);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(70, 80, 100, 20);
        add(nameLabel);

        JLabel name = new JLabel("");
        name.setBounds(250, 80, 100, 20);
        add(name);

        JLabel meterNumberLabel = new JLabel("Meter Number");
        meterNumberLabel.setBounds(70, 140, 100, 20);
        add(meterNumberLabel);

        JLabel meterNumber = new JLabel("");
        meterNumber.setBounds(250, 140, 100, 20);
        add(meterNumber);

        JLabel addressLabel = new JLabel("Address");
        addressLabel.setBounds(70, 200, 100, 20);
        add(addressLabel);

        JLabel address = new JLabel("");
        address.setBounds(250, 200, 100, 20);
        add(address);

        JLabel cityLabel = new JLabel("City");
        cityLabel.setBounds(70, 260, 100, 20);
        add(cityLabel);

        JLabel city = new JLabel("");
        city.setBounds(250, 260, 100, 20);
        add(city);

        JLabel stateLabel = new JLabel("State");
        stateLabel.setBounds(500, 80, 100, 20);
        add(stateLabel);

        JLabel state = new JLabel("");
        state.setBounds(650, 80, 100, 20);
        add(state);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(500, 140, 100, 20);
        add(emailLabel);

        JLabel email = new JLabel("");
        email.setBounds(650, 140, 100, 20);
        add(email);

        JLabel phoneLabel = new JLabel("Phone");
        phoneLabel.setBounds(500, 200, 100, 20);
        add(phoneLabel);

        JLabel phone = new JLabel("");
        phone.setBounds(650, 200, 100, 20);
        add(phone);

        try {
            Conn con = new Conn();
            ResultSet resultSet = con.statement.executeQuery("select * from customer where meter_no = '" + meter + "'");
            while (resultSet.next()) {
                name.setText(resultSet.getString("name"));
                address.setText(resultSet.getString("address"));
                city.setText(resultSet.getString("city"));
                state.setText(resultSet.getString("state"));
                email.setText(resultSet.getString("email"));
                phone.setText(resultSet.getString("phone"));
                meterNumber.setText(resultSet.getString("meter_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(350, 340, 100, 25);
        add(cancel);
        cancel.addActionListener(this);

        ImageIcon customerImage = new ImageIcon(ClassLoader.getSystemResource("icon/viewcustomer.jpg"));
        Image scaleImage = customerImage.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(scaleImage);
        JLabel finalImage = new JLabel(image);
        finalImage.setBounds(20, 350, 600, 300);
        add(finalImage);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
    }

    public static void main(String[] args) {
        new ViewInformation("");
    }
}