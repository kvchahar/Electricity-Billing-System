package electricity.billing.system;

import electricity.database.Conn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    JButton login, cancel, signup;
    JTextField usernameTextField, passwordTextField;
    Choice logInChoice;

    public Login() {
        super("Login Page"); // title name
        getContentPane().setBackground(Color.white); // pick the frame and set background color.
        setLayout(null); // by default layout set to be null.

        // username
        JLabel username = new JLabel("Username"); // write on frame
        username.setBounds(300, 20, 100, 20); // set our own size
        add(username);
        // input field
        usernameTextField = new JTextField();
        usernameTextField.setBounds(400, 20, 150, 20);
        add(usernameTextField);

        // password
        JLabel password = new JLabel("Password"); // write on frame
        password.setBounds(300, 60, 100, 20); // set our own size
        add(password);
        // input field
        passwordTextField = new JTextField();
        passwordTextField.setBounds(400, 60, 150, 20);
        add(passwordTextField);

        // log in as
        JLabel logInAs = new JLabel("Log in as"); // write on frame
        logInAs.setBounds(300, 100, 100, 20); // set our own size
        add(logInAs);

        // Dropdown for admin and customer
        logInChoice = new Choice();
        logInChoice.add("Admin");
        logInChoice.add("Customer");
        logInChoice.setBounds(400, 100, 150, 20);
        add(logInChoice);

        // login button
        ImageIcon loginImage = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image scaleLoginImage = loginImage.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        login = new JButton("Login", new ImageIcon(scaleLoginImage));
        login.setBounds(330, 160, 100, 20);
        login.addActionListener(this);
        add(login);

        // cancel button
        ImageIcon cancelImage = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image scaleCancelImage = cancelImage.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        cancel = new JButton("Cancel", new ImageIcon(scaleCancelImage));
        cancel.setBounds(450, 160, 100, 20);
        cancel.addActionListener(this);
        add(cancel);

        // signup button
        ImageIcon signupImage = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image scaleSignupImage = signupImage.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        signup = new JButton("Signup", new ImageIcon(scaleSignupImage));
        signup.setBounds(380, 200, 100, 20);
        signup.addActionListener(this);
        add(signup);

        // Human image
        ImageIcon humanImage = new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image scaleHumanImage = humanImage.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon human = new ImageIcon(scaleHumanImage);
        JLabel image = new JLabel(human);
        image.setBounds(0, 0, 250, 250);
        add(image);


        setSize(640, 300);
        setLocation(400, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            String username = usernameTextField.getText();
            String password = passwordTextField.getText();
            String userChoice = logInChoice.getSelectedItem();

            try {
                Conn conn = new Conn();
                String query = "select * from login where username = '" + username + "' and password = '" + password + "' and user = '" + userChoice + "'";
                ResultSet resultSet = conn.statement.executeQuery(query);

                if (resultSet.next()) {
                    setVisible(false);
                    new Project(userChoice);
                } else {
                    JOptionPane.showMessageDialog(null,"Invalid Login");
                    usernameTextField.setText("");
                    passwordTextField.setText("");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            // cancel the login frame
        } else if (e.getSource() == cancel) {
            setVisible(false);
            // cancel the login frame and call Signup constructor to open that file.
        } else if (e.getSource() == signup) {
            setVisible(false);
            new Signup();
        }
    }

    public static void main(String[] args) {
        new Login();
    }


}
