package ui;

import javax.swing.*;
import java.net.MalformedURLException;

// represents a splash screen
public class SplashScreenGUI {
    JWindow window = new JWindow();

    // EFFECTS: creates a splashscreen
    public SplashScreenGUI() throws MalformedURLException {
        window.getContentPane().add(
                new JLabel("",
                        new ImageIcon("data/marketplace.png"),
                        SwingConstants.CENTER));
        window.setBounds(500, 150, 500, 500);
        window.setVisible(true);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        window.setVisible(false);
        window.dispose();
    }

}
