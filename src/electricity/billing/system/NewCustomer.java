package electricity.billing.system;

import electricity.database.Conn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NewCustomer extends JFrame implements ActionListener {

    JTextField nameTextField, addressTextField, stateTextField, cityTextField, emailTextField, phoneTextField;
    JButton next, cancel;
    JLabel labelMeter;

    public NewCustomer() {
        setSize(700, 500);
        setLocation(400, 200);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(173, 216, 230));
        add(panel);

        JLabel heading = new JLabel("New Customer");
        heading.setBounds(180, 10, 200, 25);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        panel.add(heading);


        JLabel customerName = new JLabel("Customer Name");
        customerName.setBounds(100, 80, 100, 20);
        panel.add(customerName);

        nameTextField = new JTextField();
        nameTextField.setBounds(240, 80, 200, 20);
        panel.add(nameTextField);

        JLabel labelMeterNumber = new JLabel("Meter Number");
        labelMeterNumber.setBounds(100, 120, 100, 20);
        panel.add(labelMeterNumber);

        labelMeter = new JLabel("");
        labelMeter.setBounds(240, 120, 100, 20);
        panel.add(labelMeter);

        Random ran = new Random();
        long number = ran.nextLong() % 1000000; // generate random meter number of six digits
        labelMeter.setText("" + Math.abs(number));

        JLabel labelAddress = new JLabel("Address");
        labelAddress.setBounds(100, 160, 100, 20);
        panel.add(labelAddress);

        addressTextField = new JTextField();
        addressTextField.setBounds(240, 160, 200, 20);
        panel.add(addressTextField);

        JLabel labelCity = new JLabel("City");
        labelCity.setBounds(100, 200, 100, 20);
        panel.add(labelCity);

        cityTextField = new JTextField();
        cityTextField.setBounds(240, 200, 200, 20);
        panel.add(cityTextField);

        JLabel labelState = new JLabel("State");
        labelState.setBounds(100, 240, 100, 20);
        panel.add(labelState);

        stateTextField = new JTextField();
        stateTextField.setBounds(240, 240, 200, 20);
        panel.add(stateTextField);

        JLabel labelEmail = new JLabel("Email");
        labelEmail.setBounds(100, 280, 100, 20);
        panel.add(labelEmail);

        emailTextField = new JTextField();
        emailTextField.setBounds(240, 280, 200, 20);
        panel.add(emailTextField);

        JLabel labelPhone = new JLabel("Phone Number");
        labelPhone.setBounds(100, 320, 100, 20);
        panel.add(labelPhone);

        phoneTextField = new JTextField();
        phoneTextField.setBounds(240, 320, 200, 20);
        panel.add(phoneTextField);

        next = new JButton("Next");
        next.setBounds(120, 390, 100, 25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        panel.add(next);

        cancel = new JButton("Cancel");
        cancel.setBounds(250, 390, 100, 25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        panel.add(cancel);

        setLayout(new BorderLayout());

        add(panel, "Center");

        ImageIcon imagePath = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image scaleImage = imagePath.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(scaleImage);
        JLabel finalImage = new JLabel(image);
        add(finalImage, "West");

        getContentPane().setBackground(Color.WHITE);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            String name = nameTextField.getText();
            String meter = labelMeter.getText();
            String address = addressTextField.getText();
            String city = cityTextField.getText();
            String state = stateTextField.getText();
            String email = emailTextField.getText();
            String phone = phoneTextField.getText();

            String query1 = "insert into customer values('" + name + "', '" + meter + "', '" + address + "', '" + city + "', '" + state + "', '" + email + "', '" + phone + "')";
            String query2 = "insert into login values('" + meter + "', '', '" + name + "', '', '')";

            try {
                Conn con = new Conn();
                con.statement.executeUpdate(query1);
                con.statement.executeUpdate(query2);

                JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");
                setVisible(false);

                new MeterInfo(meter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new NewCustomer();
    }
}
