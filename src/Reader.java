import frames.ReaderFrame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Запуск приложения.
 */
public class Reader extends JFrame {

    public static void main(String[] args) {
        ReaderFrame analyzer = new ReaderFrame();

        analyzer.setBounds(300, 300, 850, 350);
        analyzer.setVisible(true);
        analyzer.setDefaultCloseOperation(
                WindowConstants.DISPOSE_ON_CLOSE);
        analyzer.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}