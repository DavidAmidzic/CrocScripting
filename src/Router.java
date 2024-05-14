import javax.swing.*;
import java.awt.*;

public class Router extends JFrame {
    public Router() {
        //basic JFrame Settings
        setTitle("Router");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //JPanels
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }
}
