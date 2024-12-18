import frames.ReaderFrame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Запуск приложения.
 */
public class Reader extends JFrame {

    public static void main(String[] args) {
        ReaderFrame reader = new ReaderFrame();

        reader.setBounds(300, 300, 850, 350);
        reader.setVisible(true);
        reader.setDefaultCloseOperation(
                WindowConstants.DISPOSE_ON_CLOSE);
        reader.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}