import javax.swing.*;
import java.awt.*;

public class Switch extends JFrame {
    public Switch() {
        //basic JFrame Settings
        setTitle("Switch");
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
