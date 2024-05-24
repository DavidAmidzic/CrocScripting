import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Switch extends JFrame {
    private JCheckBox vlanCheckBox;
    private JCheckBox stpCheckBox;
    private JTextField vlanNumberField;
    private JTextField vlanNameField;
    private JTextField vlanIPField;
    private JTextField cidrField;
    private JTextArea overview;
    private JPanel vlanPanel;
    private JPanel ripPanel;

    public Switch() {
        setTitle("Switch Configuration");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Configuration Panels
        JPanel configPanel = new JPanel();
        configPanel.setLayout(new BoxLayout(configPanel, BoxLayout.Y_AXIS));

        // VLAN Panel
        vlanPanel = new JPanel();
        vlanConfiguration();

        // RIP Panel
        ripPanel = new JPanel();

        // Add panels to the configuration panel
        configPanel.add(vlanPanel);
        configPanel.add(ripPanel);

        // Make configPanel scrollable
        JScrollPane scrollPane = new JScrollPane(configPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.WEST);

        // Generate-Button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JButton generateButton = new JButton("Generate");

        buttonPanel.add(generateButton);
        add(buttonPanel, BorderLayout.SOUTH);
        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: Implement generate action
            }
        });

        // Display Area
        overview = new JTextArea();
        overview.setEditable(false); // Set to false to prevent user editing
        add(new JScrollPane(overview), BorderLayout.CENTER);

        setVisible(true);
    }

    private void vlanConfiguration() {
        vlanPanel.setLayout(new BoxLayout(vlanPanel, BoxLayout.Y_AXIS));
        vlanPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        vlanCheckBox = new JCheckBox("VLAN");
        vlanPanel.add(vlanCheckBox);

        // Fields and Labels (initially hidden)
        JLabel numberOfVLAN = new JLabel("Number: ");
        vlanNumberField = new JTextField(20);
        vlanNumberField.setMaximumSize(new Dimension(Integer.MAX_VALUE, vlanNumberField.getPreferredSize().height));
        numberOfVLAN.setVisible(false);
        vlanNumberField.setVisible(false);
        vlanPanel.add(numberOfVLAN);
        vlanPanel.add(vlanNumberField);

        JLabel nameOfVLAN = new JLabel("Name: ");
        vlanNameField = new JTextField(20);
        vlanNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, vlanNameField.getPreferredSize().height));
        nameOfVLAN.setVisible(false);
        vlanNameField.setVisible(false);
        vlanPanel.add(nameOfVLAN);
        vlanPanel.add(vlanNameField);

        JLabel ipLabel = new JLabel("IP: ");
        vlanIPField = new JTextField(20);
        vlanIPField.setMaximumSize(new Dimension(Integer.MAX_VALUE, vlanIPField.getPreferredSize().height));
        ipLabel.setVisible(false);
        vlanIPField.setVisible(false);
        vlanPanel.add(ipLabel);
        vlanPanel.add(vlanIPField);

        JLabel cidrLabel = new JLabel("CIDR:");
        cidrField = new JTextField(20);
        cidrField.setMaximumSize(new Dimension(Integer.MAX_VALUE, vlanNameField.getPreferredSize().height));
        cidrLabel.setVisible(false);
        cidrField.setVisible(false);
        vlanPanel.add(cidrLabel);
        vlanPanel.add(cidrField);

        vlanCheckBox.addActionListener(e -> {
            boolean selected = vlanCheckBox.isSelected();
            numberOfVLAN.setVisible(selected);
            nameOfVLAN.setVisible(selected);
            ipLabel.setVisible(selected);
            cidrLabel.setVisible(selected);
            vlanNumberField.setVisible(selected);
            vlanNameField.setVisible(selected);
            vlanIPField.setVisible(selected);
            cidrField.setVisible(selected);
            revalidate();
            repaint();
        });

        // Action listeners to update the overview area
        vlanNumberField.addActionListener(e -> {
            overview.append("Number of VLAN: " + vlanNumberField.getText() + "\n");
            vlanNumberField.setText("");    
        });

        vlanNameField.addActionListener(e -> {
            overview.append("Name of VLAN: " + vlanNameField.getText() + "\n");
            vlanNameField.setText("");
        });

        vlanIPField.addActionListener(e -> {
            overview.append("IP: " + vlanIPField.getText() + "\n");
            vlanIPField.setText("");
        });
        cidrField.addActionListener(e -> {
            overview.append("CIDR: " + cidrField.getText() + "\n");
            cidrField.setText("");
        });
    }
/*
    private void ripConfiguration() {
        ripPanel.setLayout(new BoxLayout(ripPanel, BoxLayout.Y_AXIS));
        ripPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        stpCheckBox = new JCheckBox("RIP");
        ripPanel.add(stpCheckBox);

        // Fields and Labels (initially hidden)
        JLabel ripVersionLabel = new JLabel("Ver-sion:");
        ripVersionField = new JTextField(20);
        ripVersionField.setMaximumSize(new Dimension(Integer.MAX_VALUE, ripVersionField.getPreferredSize().height));
        ripVersionLabel.setVisible(false);
        ripVersionField.setVisible(false);
        ripPanel.add(ripVersionLabel);
        ripPanel.add(ripVersionField);

        JLabel ripNetworkLabel = new JLabel("Network:");
        ripNetworkField = new JTextField(20);
        ripNetworkField.setMaximumSize(new Dimension(Integer.MAX_VALUE, ripNetworkField.getPreferredSize().height));
        ripNetworkLabel.setVisible(false);
        ripNetworkField.setVisible(false);
        ripPanel.add(ripNetworkLabel);
        ripPanel.add(ripNetworkField);

        JLabel ripPassiveInterfaceLabel = new JLabel("Passive Interface:");
        ripPassiveInterfaceField = new JTextField(20);
        ripPassiveInterfaceField.setMaximumSize(new Dimension(Integer.MAX_VALUE, ripPassiveInterfaceField.getPreferredSize().height));
        ripPassiveInterfaceLabel.setVisible(false);
        ripPassiveInterfaceField.setVisible(false);
        ripPanel.add(ripPassiveInterfaceLabel);
        ripPanel.add(ripPassiveInterfaceField);

        stpCheckBox.addActionListener(e -> {
            boolean selected = stpCheckBox.isSelected();
            ripVersionLabel.setVisible(selected);
            ripVersionField.setVisible(selected);
            ripNetworkLabel.setVisible(selected);
            ripNetworkField.setVisible(selected);
            ripPassiveInterfaceLabel.setVisible(selected);
            ripPassiveInterfaceField.setVisible(selected);
            revalidate();
            repaint();
        });


        // Action listeners to update the overview area
        ripVersionField.addActionListener(e -> {
            overview.append("RIP Version: " + ripVersionField.getText() + "\n");
            ripVersionField.setText("");
        });

        ripNetworkField.addActionListener(e -> {
            overview.append("RIP Network: " + ripNetworkField.getText() + "\n");
            ripNetworkField.setText("");
        });

        ripPassiveInterfaceField.addActionListener(e -> {
            overview.append("RIP Passive Interface: " + ripPassiveInterfaceField.getText() + "\n");
            ripPassiveInterfaceField.setText("");
        });
    }
*/
}
