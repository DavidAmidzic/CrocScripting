import javax.swing.*;
import java.awt.*;

public class WelcomePage extends JFrame {

    public WelcomePage() {
        // Basic JFrame Settings
        setTitle("Croc-Scripting");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // JPanels
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel, BorderLayout.CENTER);

        // Names
        JPanel namesPanel = new JPanel();
        namesPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        namesPanel.add(new JLabel("Amidzic,"));
        namesPanel.add(new JLabel("Patel"));
        mainPanel.add(namesPanel, BorderLayout.NORTH);

        // Description
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        JLabel welcomeLabel = new JLabel(
                "<html>" +
                        "<div style='text-align: center;'>" +
                        "  <h1>Welcome</h1>" +
                        "<br><p>Our project aims to develop a powerful and user-friendly script generator for Cisco networks that allows users to create network configurations quickly and easily. With an intuitive graphical user interface, users can enter network parameters and generate the desired configurations.</p> <br>" +
                        "<p>Our script generator offers a variety of features aligned with the last three years of network engineering curriculum. From basic configurations such as DHCP and static IP addresses to more advanced network elements such as VLANs, ACLs and routing protocols, our tool covers all aspects covered in class.</p> <br>" +
                        "<p>To ensure that our application is easily accessible and allows for seamless execution, we have chosen to host it on a Raspberry Pi. The Raspberry Pi serves as the central platform that enables the execution of our Java application.</p>" +
                        "</div>" +
                        "</html>");

        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomePanel.add(welcomeLabel);
        mainPanel.add(welcomePanel, BorderLayout.CENTER);

        // Add buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // Add router button
        JButton routerButton = new JButton("Router");
        routerButton.setPreferredSize(new Dimension(150, 50));
        routerButton.addActionListener(e -> SwingUtilities.invokeLater(Router::new));
        buttonsPanel.add(routerButton);

        // Add switch button
        JButton switchButton = new JButton("Switch");
        switchButton.setPreferredSize(new Dimension(150, 50));
        switchButton.addActionListener(e -> SwingUtilities.invokeLater(Switch::new));
        buttonsPanel.add(switchButton);

        // Add wiki button
        JButton wikiButton = new JButton("Wiki");
        wikiButton.setPreferredSize(new Dimension(150, 50));
        wikiButton.addActionListener(e -> SwingUtilities.invokeLater(Wiki::new));
        buttonsPanel.add(wikiButton);

        // Add buttons panel to main panel
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        System.out.println("Loading...");
        System.out.println("Welcome to Croc-Scripting!");
        SwingUtilities.invokeLater(WelcomePage::new);
    }
}
