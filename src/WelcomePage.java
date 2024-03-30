import javax.swing.*;
import java.awt.*;

public class WelcomePage extends JFrame {

    private JFrame frame;
    public WelcomePage() {
        //basic JFrame Settings
        frame = new JFrame("Welcome to Croc-Scripting!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);



        frame.setVisible(true);

    }
    public static void main(String[] args) {
        System.out.println("Welcome to Croc-Scripting!");
        SwingUtilities.invokeLater(() -> new WelcomePage());
    }
}