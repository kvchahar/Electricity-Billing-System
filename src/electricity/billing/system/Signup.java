package electricity.billing.system;

import electricity.database.Conn;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Signup extends JFrame implements ActionListener {

    JButton create, back;
    Choice accountType;
    JTextField meter, usernameTextField, nameTextField, passwordTextField;


    public Signup() {
        setBounds(450, 150, 700, 400);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JPanel panel = new JPanel(); // panel inside frame
        panel.setBounds(30, 30, 650, 300);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 2), "Create Account",
                TitledBorder.LEADING, TitledBorder.TOP, null, new Color(172, 216, 230)));
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setForeground(new Color(34, 139, 34));
        add(panel);

        JLabel heading = new JLabel("Create account as");
        heading.setBounds(100, 50, 140, 20);
        heading.setForeground(Color.GRAY);
        heading.setFont(new Font("Tahoma", Font.BOLD, 15));
        panel.add(heading); // add on panel

        // account type
        accountType = new Choice();
        accountType.add("Admin");
        accountType.add("Customer");
        accountType.setBounds(260, 50, 150, 20);
        panel.add(accountType);

        // meter number
        JLabel meterNumber = new JLabel("Meter Number");
        meterNumber.setBounds(100, 90, 140, 20);
        meterNumber.setForeground(Color.GRAY);
        meterNumber.setFont(new Font("Tahoma", Font.BOLD, 15));
        panel.add(meterNumber); // add on panel

        meter = new JTextField();
        meter.setBounds(260, 90, 150, 20);
        panel.add(meter);

        // username
        JLabel username = new JLabel("Username");
        username.setBounds(100, 130, 140, 20);
        username.setForeground(Color.GRAY);
        username.setFont(new Font("Tahoma", Font.BOLD, 15));
        panel.add(username); // add on panel

        usernameTextField = new JTextField();
        usernameTextField.setBounds(260, 130, 150, 20);
        panel.add(usernameTextField);

        // Name
        JLabel name = new JLabel("Name");
        name.setBounds(100, 170, 140, 20);
        name.setForeground(Color.GRAY);
        name.setFont(new Font("Tahoma", Font.BOLD, 15));
        panel.add(name); // add on panel

        nameTextField = new JTextField();
        nameTextField.setBounds(260, 170, 150, 20);
        panel.add(nameTextField);
        setVisible(true);


        // Password
        JLabel password = new JLabel("Password");
        password.setBounds(100, 210, 140, 20);
        password.setForeground(Color.GRAY);
        password.setFont(new Font("Tahoma", Font.BOLD, 15));
        panel.add(password); // add on panel

        passwordTextField = new JTextField();
        passwordTextField.setBounds(260, 210, 150, 20);
        panel.add(passwordTextField);

        create = new JButton("Create");
        create.setBackground(Color.BLACK);
        create.setForeground(Color.WHITE);
        create.setBounds(140, 260, 120, 25);
        create.addActionListener(this);
        panel.add(create);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(300, 260, 120, 25);
        back.addActionListener(this);
        panel.add(back);

        // Signup image
        ImageIcon signupImage = new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
        Image scaleSignupImage = signupImage.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(scaleSignupImage);
        JLabel finalImage = new JLabel(image);
        finalImage.setBounds(415, 30, 250, 250);
        panel.add(finalImage);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == create) {
            String accType = accountType.getSelectedItem();
            String username = usernameTextField.getText();
            String name = nameTextField.getText();
            String password = passwordTextField.getText();
            String meterField = meter.getText();

            try {
                Conn con = new Conn();
                String query = "INSERT INTO login VALUES('" + meterField + "','" + username + "','" + name + "','" + password + "','" + accType + "')";
                con.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Account Created Successfully \nClick Ok to go to login page");
                setVisible(false);
                new Login();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else if (e.getSource() == back) {
            setVisible(false);
            new Login(); // back to login page
        }
    }

    public static void main(String[] args) {
        new Signup();
    }


}
