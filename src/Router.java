import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Router extends JFrame {
    private JCheckBox ospfCheckBox;
    private JCheckBox ripCheckBox;
    private JTextField ospfGroupField;
    private JTextField ospfRouterIdField;
    private JTextField ospfNetworkField;
    private JTextField ospfPassiveInterfaceField;
    private JTextField ripVersionField;
    private JTextField ripNetworkField;
    private JTextField ripPassiveInterfaceField;
    private JTextArea overview;
    private JPanel ospfPanel;
    private JPanel ripPanel;

    public Router() {
        setTitle("Router Configuration");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Configuration Panels
        JPanel configPanel = new JPanel();
        configPanel.setLayout(new BoxLayout(configPanel, BoxLayout.Y_AXIS));

        // OSPF Panel
        ospfPanel = new JPanel();
        ospfConfiguration();

        // RIP Panel
        ripPanel = new JPanel();
        ripConfiguration();

        // Add panels to the configuration panel
        configPanel.add(ospfPanel);
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

    private void ospfConfiguration() {
        ospfPanel.setLayout(new BoxLayout(ospfPanel, BoxLayout.Y_AXIS));
        ospfPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        ospfCheckBox = new JCheckBox("OSPF");
        ospfPanel.add(ospfCheckBox);

        // Fields and Labels (initially hidden)
        JLabel groupLabel = new JLabel("Group:");
        ospfGroupField = new JTextField(20);
        ospfGroupField.setMaximumSize(new Dimension(Integer.MAX_VALUE, ospfGroupField.getPreferredSize().height));
        groupLabel.setVisible(false);
        ospfGroupField.setVisible(false);
        ospfPanel.add(groupLabel);
        ospfPanel.add(ospfGroupField);

        JLabel routerIdLabel = new JLabel("Router ID:");
        ospfRouterIdField = new JTextField(20);
        ospfRouterIdField.setMaximumSize(new Dimension(Integer.MAX_VALUE, ospfRouterIdField.getPreferredSize().height));
        routerIdLabel.setVisible(false);
        ospfRouterIdField.setVisible(false);
        ospfPanel.add(routerIdLabel);
        ospfPanel.add(ospfRouterIdField);

        JLabel networkLabel = new JLabel("Network:");
        ospfNetworkField = new JTextField(20);
        ospfNetworkField.setMaximumSize(new Dimension(Integer.MAX_VALUE, ospfNetworkField.getPreferredSize().height));
        networkLabel.setVisible(false);
        ospfNetworkField.setVisible(false);
        ospfPanel.add(networkLabel);
        ospfPanel.add(ospfNetworkField);

        JLabel passiveInterfaceLabel = new JLabel("Passive Interface:");
        ospfPassiveInterfaceField = new JTextField(20);
        ospfPassiveInterfaceField.setMaximumSize(new Dimension(Integer.MAX_VALUE, ospfPassiveInterfaceField.getPreferredSize().height));
        passiveInterfaceLabel.setVisible(false);
        ospfPassiveInterfaceField.setVisible(false);
        ospfPanel.add(passiveInterfaceLabel);
        ospfPanel.add(ospfPassiveInterfaceField);

        ospfCheckBox.addActionListener(e -> {
            boolean selected = ospfCheckBox.isSelected();
            groupLabel.setVisible(selected);
            ospfGroupField.setVisible(selected);
            routerIdLabel.setVisible(selected);
            ospfRouterIdField.setVisible(selected);
            networkLabel.setVisible(selected);
            ospfNetworkField.setVisible(selected);
            passiveInterfaceLabel.setVisible(selected);
            ospfPassiveInterfaceField.setVisible(selected);
            revalidate();
            repaint();
        });

        // Action listeners to update the overview area
        ospfGroupField.addActionListener(e -> {
            overview.append("OSPF Group: " + ospfGroupField.getText() + "\n");
            ospfGroupField.setText("");
        });

        ospfRouterIdField.addActionListener(e -> {
            overview.append("OSPF Router ID: " + ospfRouterIdField.getText() + "\n");
            ospfRouterIdField.setText("");
        });

        ospfNetworkField.addActionListener(e -> {
            overview.append("OSPF Network: " + ospfNetworkField.getText() + "\n");
            ospfNetworkField.setText("");
        });

        ospfPassiveInterfaceField.addActionListener(e -> {
            overview.append("OSPF Passive Interface: " + ospfPassiveInterfaceField.getText() + "\n");
            ospfPassiveInterfaceField.setText("");
        });
    }

    private void ripConfiguration() {
        ripPanel.setLayout(new BoxLayout(ripPanel, BoxLayout.Y_AXIS));
        ripPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        ripCheckBox = new JCheckBox("RIP");
        ripPanel.add(ripCheckBox);

        // Fields and Labels (initially hidden)
        JLabel ripVersionLabel = new JLabel("Version:");
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

        ripCheckBox.addActionListener(e -> {
            boolean selected = ripCheckBox.isSelected();
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

}
