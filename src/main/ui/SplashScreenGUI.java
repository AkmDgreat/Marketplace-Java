package ui;

import model.Event;
import model.EventLog;

import javax.swing.*;

// represents a splash screen
public class SplashScreenGUI {
    JWindow window = new JWindow();

    // EFFECTS: creates a splashscreen
    public SplashScreenGUI() {
        EventLog.getInstance().logEvent(new Event("displaying the splash screen"));
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
