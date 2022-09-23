package electricity.billing.system;

import electricity.database.Conn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MeterInfo extends JFrame implements ActionListener {

    JButton next;
    Choice meterLocation, meterType, phaseCode, billType;
    String meterNumber;

    public MeterInfo(String meterNumber) {
        this.meterNumber = meterNumber;
        setSize(700, 500);
        setLocation(400, 200);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(173, 216, 230));
        add(panel);

        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(180, 10, 200, 25);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        panel.add(heading);


        JLabel labelMeterName = new JLabel("Meter Number");
        labelMeterName.setBounds(100, 80, 100, 20);
        panel.add(labelMeterName);

        JLabel labelMeterNumber = new JLabel(meterNumber);
        labelMeterNumber.setBounds(240, 80, 100, 20);
        panel.add(labelMeterNumber);

        JLabel labelMeterLocation = new JLabel("Meter Location");
        labelMeterLocation.setBounds(100, 120, 100, 20);
        panel.add(labelMeterLocation);

        meterLocation = new Choice();
        meterLocation.add("Outside");
        meterLocation.add("Inside");
        meterLocation.setBounds(240, 120, 200, 20);
        panel.add(meterLocation);

        JLabel labelMeterType = new JLabel("Meter Type");
        labelMeterType.setBounds(100, 160, 100, 20);
        panel.add(labelMeterType);

        meterType = new Choice();
        meterType.add("Electric Meter");
        meterType.add("Solar Meter");
        meterType.add("Small Meter");
        meterType.setBounds(240, 160, 200, 20);
        panel.add(meterType);


        JLabel labelPhaseCode = new JLabel("Phase Code");
        labelPhaseCode.setBounds(100, 200, 100, 20);
        panel.add(labelPhaseCode);

        phaseCode = new Choice();
        phaseCode.add("011");
        phaseCode.add("022");
        phaseCode.add("033");
        phaseCode.add("044");
        phaseCode.add("055");
        phaseCode.add("066");
        phaseCode.add("077");
        phaseCode.add("088");
        phaseCode.add("099");
        phaseCode.setBounds(240, 200, 200, 20);
        panel.add(phaseCode);

        JLabel labelBillType = new JLabel("Bill Type");
        labelBillType.setBounds(100, 240, 100, 20);
        panel.add(labelBillType);

        billType = new Choice();
        billType.add("Normal");
        billType.add("Industrial");
        billType.setBounds(240, 240, 200, 20);
        panel.add(billType);

        JLabel labelDays = new JLabel("Days");
        labelDays.setBounds(100, 280, 100, 20);
        panel.add(labelDays);

        JLabel totalDays = new JLabel("30 days");
        totalDays.setBounds(240, 280, 100, 20);
        panel.add(totalDays);


        JLabel labelNote = new JLabel("Note");
        labelNote.setBounds(100, 320, 100, 20);
        panel.add(labelNote);

        JLabel billCalculateDaysLimit = new JLabel("By Default Bill is calculated for 30 days only");
        billCalculateDaysLimit.setBounds(240, 320, 500, 20);
        panel.add(billCalculateDaysLimit);

        next = new JButton("Submit");
        next.setBounds(220, 390, 100, 25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        panel.add(next);

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

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            String meter = meterNumber;
            String location = meterLocation.getSelectedItem();
            String type = meterType.getSelectedItem();
            String code = phaseCode.getSelectedItem();
            String typeBill = billType.getSelectedItem();
            String days = "30";

            String query = "insert into meter_info values('" + meter + "','" + location + "', '" + type + "', '" + code + "', '" + typeBill + "', '" + days + "')";

            try {
                Conn con = new Conn();
                con.statement.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Meter Information Added Successfully");
                setVisible(false);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new MeterInfo("");
    }
}
