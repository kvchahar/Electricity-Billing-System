package electricity.billing.system;

import electricity.database.Conn;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class UpdateInformation extends JFrame implements ActionListener{

    JTextField addressTextField, stateTextField, cityTextField, emailTextField, phoneTextField;
    JButton update, cancel;
    String meter;
    JLabel name;
    UpdateInformation(String meter) {
        this.meter = meter;
        setBounds(300, 150, 1050, 450);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("UPDATE CUSTOMER INFORMATION");
        heading.setBounds(110, 0, 400, 30);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(heading);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(30, 70, 100, 20);
        add(lblname);

        name = new JLabel("");
        name.setBounds(230, 70, 200, 20);
        add(name);

        JLabel meterNumberLabel = new JLabel("Meter Number");
        meterNumberLabel.setBounds(30, 110, 100, 20);
        add(meterNumberLabel);

        JLabel meterNumber = new JLabel("");
        meterNumber.setBounds(230, 110, 200, 20);
        add(meterNumber);

        JLabel addressLabel = new JLabel("Address");
        addressLabel.setBounds(30, 150, 100, 20);
        add(addressLabel);

        addressTextField = new JTextField();
        addressTextField.setBounds(230, 150, 200, 20);
        add(addressTextField);

        JLabel cityLabel = new JLabel("City");
        cityLabel.setBounds(30, 190, 100, 20);
        add(cityLabel);

        cityTextField = new JTextField();
        cityTextField.setBounds(230, 190, 200, 20);
        add(cityTextField);

        JLabel stateLabel = new JLabel("State");
        stateLabel.setBounds(30, 230, 100, 20);
        add(stateLabel);

        stateTextField = new JTextField();
        stateTextField.setBounds(230, 230, 200, 20);
        add(stateTextField);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(30, 270, 100, 20);
        add(emailLabel);

        emailTextField = new JTextField();
        emailTextField.setBounds(230, 270, 200, 20);
        add(emailTextField);

        JLabel phoneLabel = new JLabel("Phone");
        phoneLabel.setBounds(30, 310, 100, 20);
        add(phoneLabel);

        phoneTextField = new JTextField();
        phoneTextField.setBounds(230, 310, 200, 20);
        add(phoneTextField);

        try {
            Conn con = new Conn();
            ResultSet rs = con.statement.executeQuery("select * from customer where meter_no = '"+meter+"'");
            while(rs.next()) {
                name.setText(rs.getString("name"));
                addressTextField.setText(rs.getString("address"));
                cityTextField.setText(rs.getString("city"));
                stateTextField.setText(rs.getString("state"));
                emailTextField.setText(rs.getString("email"));
                phoneTextField.setText(rs.getString("phone"));
                meterNumber.setText(rs.getString("meter_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        update = new JButton("Update");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setBounds(70, 360, 100, 25);
        add(update);
        update.addActionListener(this);

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(230, 360, 100, 25);
        add(cancel);
        cancel.addActionListener(this);

        ImageIcon imagePath = new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg"));
        Image scaleImage = imagePath.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(scaleImage);
        JLabel finalImage = new JLabel(image);
        finalImage.setBounds(550, 50, 400, 300);
        add(finalImage);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == update) {
            String address = addressTextField.getText();
            String city = cityTextField.getText();
            String state = stateTextField.getText();
            String email = emailTextField.getText();
            String phone = phoneTextField.getText();

            try {
                Conn con = new Conn();
                con.statement.executeUpdate("update customer set address = '"+address+"', city = '"+city+"', state = '"+state+"', email = '"+email+"', phone = '"+phone+"' where meter_no = '"+meter+"'");

                JOptionPane.showMessageDialog(null, "User Information Updated Successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new UpdateInformation("");
    }
}
