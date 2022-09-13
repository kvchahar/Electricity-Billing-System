package electricity.billing.system;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame implements Runnable {
    Thread t;

    public Splash() {
        // extract image from icon folder
        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("icon/elect.jpg"));

        // Scaling of image
        Image img2 = img1.getImage().getScaledInstance(730, 550, Image.SCALE_DEFAULT);

        ImageIcon actualImage = new ImageIcon(img2);
        JLabel image = new JLabel(actualImage); // to add the image over the frame.
        add(image);

        setVisible(true); // to visible the frame.

        int x = 1;

        for (int i = 2; i < 600; i += 4, x += 1) {
            setSize(i + x, i);
            setLocation(700 - ((i + x) / 2), 400 - (i / 2));
            try {
                Thread.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        t = new Thread(this);
        t.start();

        setVisible(true);

//        setSize(730,550); // define the width and height of the frame.
//        setLocation(400,170); // to define the position of frame.

    }

    @Override
    public void run() {
        try {
            Thread.sleep(7000);
            setVisible(false); // frame will be closed after 7 seconds.

            // login frame
            new Login(); // and login frame will be displaying there.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Splash();
    }


}
