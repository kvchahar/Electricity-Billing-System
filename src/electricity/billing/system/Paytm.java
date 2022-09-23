package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Paytm extends JFrame implements ActionListener{

    String meter;
    JButton back;
    Paytm(String meter) {
        this.meter = meter;

        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);

        try {
            editorPane.setPage("https://paytm.com/online-payments");
        } catch (Exception e) {
            editorPane.setContentType("text/html");
            editorPane.setText("<html>Could not load<html>");

        }

        JScrollPane pane = new JScrollPane(editorPane);
        add(pane);

        back = new JButton("Back");
        back.setBounds(640, 20, 80, 30);
        back.addActionListener(this);
        editorPane.add(back);

        setSize(800, 600);
        setLocation(400, 150);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new PayBill(meter);
    }

    public static void main(String[] args) {
        new Paytm("");
    }
}