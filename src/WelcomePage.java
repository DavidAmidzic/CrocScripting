import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WelcomePage extends JFrame {

    private JFrame frame;
    private ImageIcon logo = null;
    private JLabel logoLabel = null;
    public WelcomePage() {
        //basic JFrame Settings
        frame = new JFrame("Welcome to Croc-Scripting!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLayout(new BorderLayout());

        //JPanels
        JPanel textPanel = new JPanel();
        JPanel logoPanel = new JPanel();

        //add logo
        try {
            logo = new ImageIcon("images/logo.png");
            System.out.println(logo.getIconWidth());
            System.out.println(logo.getIconHeight());
            logoLabel = new JLabel(logo);
            logoLabel.setSize(500,500);
            logoPanel.add(logoLabel);
        } catch (Exception e) {
            System.out.println("Image successfully created");
        }

        //add title and description
        textPanel.setLayout(new BorderLayout());
        textPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel titleLabel = new JLabel("Welcome");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        textPanel.add(titleLabel);

        JTextArea description = new JTextArea(("Our project aims to develop a powerful and user-friendly script generator for Cisco networks that allows users to create network configurations quickly and easily. With an intuitive graphical user interface, users can enter network parameters and generate the desired configurations.\n" +
                "Our script generator offers a variety of features aligned with the last three years of network engineering curriculum. From basic configurations such as DHCP and static IP addresses to more advanced network elements such as VLANs, ACLs and routing protocols, our tool covers all aspects covered in class.\n" +
                "To ensure that our application is easily accessible and allows for seamless execution, we have chosen to host it on a Raspberry Pi. The Raspberry Pi serves as the central platform that enables the execution of our Java application\n"
                 ));
        description.setEditable(false);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setBackground(textPanel.getBackground());
        textPanel.add(description);

        //add Panels to JFrame
        add(textPanel, BorderLayout.CENTER);
        add(logoPanel);

        //set visible
        setLocationRelativeTo(null);
        setVisible(true);

    }
    public static void main(String[] args) {
        System.out.println("Welcome to Croc-Scripting!");
        SwingUtilities.invokeLater(() -> new WelcomePage());
    }
}