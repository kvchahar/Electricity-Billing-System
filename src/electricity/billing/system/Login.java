package electricity.billing.system;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {

    public Login() {
        super("Login Page"); // title name
        getContentPane().setBackground(Color.white); // pick the frame and set background color.
        setLayout(null); // by default layout set to be null.

        // username
        JLabel username = new JLabel("Username"); // write on frame
        username.setBounds(300, 20, 100, 20); // set our own size
        add(username);
        // input field
        JTextField usernameTextField = new JTextField();
        usernameTextField.setBounds(400, 20, 150, 20);
        add(usernameTextField);

        // password
        JLabel password = new JLabel("Password"); // write on frame
        password.setBounds(300, 60, 100, 20); // set our own size
        add(password);
        // input field
        JTextField passwordTextField = new JTextField();
        passwordTextField.setBounds(400, 60, 150, 20);
        add(passwordTextField);

        // log in as
        JLabel logInAs = new JLabel("Log in as"); // write on frame
        logInAs.setBounds(300, 100, 100, 20); // set our own size
        add(logInAs);

        // Dropdown for admin and customer
        Choice logInChoice = new Choice();
        logInChoice.add("Admin");
        logInChoice.add("Customer");
        logInChoice.setBounds(400, 100, 150, 20);
        add(logInChoice);

        // login button
        ImageIcon loginImage = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image scaleLoginImage = loginImage.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        JButton logIn = new JButton("Login", new ImageIcon(scaleLoginImage));
        logIn.setBounds(330, 160, 100, 20);
        add(logIn);

        // cancel button
        ImageIcon cancelImage = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image scaleCancelImage = cancelImage.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        JButton cancel = new JButton("Cancel", new ImageIcon(scaleCancelImage));
        cancel.setBounds(450, 160, 100, 20);
        add(cancel);

        // signUp button
        ImageIcon signupImage = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image scaleSignupImage = signupImage.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        JButton signUp = new JButton("Signup", new ImageIcon(scaleSignupImage));
        signUp.setBounds(380, 200, 100, 20);
        add(signUp);

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

    public static void main(String[] args) {
        new Login();
    }
}
