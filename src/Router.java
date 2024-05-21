import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Router extends JFrame {

    /* Checkbox */

    private JCheckBox ospfCheckBox;
    private JCheckBox ripCheckBox;
    private JCheckBox dhcpCheckBox;
    private JCheckBox dhcpHelperCheckBox;
    private JCheckBox hsrpCheckBox;
    private JCheckBox hsrpPreemptionCheckBox;
    private JCheckBox aclCheckBox;


    /* OSPF */

    private JTextField ospfGroupField;
    private JTextField ospfRouterIdField;
    private JTextField ospfNetworkField;
    private JTextField ospfPassiveInterfaceField;

    /* RIP */

    private JTextField ripVersionField;
    private JTextField ripNetworkField;
    private JTextField ripPassiveInterfaceField;

    /* DHCP */

    private JTextField excludedAddressStartField;
    private JTextField excludedAddressEndField;
    private JTextField dhcpNameField;
    private JTextField dhcpNetworkField;
    private JTextField dhcpHelperInterfaceField;
    private JTextField dhcpHelperIpField;
    private JTextField dhcpDefaultRouterField;
    private JTextField dhcpDnsField;

    /* HSRP */

    private JTextField hsrpGroupField;
    private JTextField hsrpInterfaceField;
    private JTextField hsrpVersionField;
    private JTextField hsrpIpPriorityField;

    /* JPanels */
    private JTextArea overview;
    private JPanel ospfPanel;
    private JPanel ripPanel;
    private JPanel dhcpPanel;
    private JPanel hsrpPanel;
    private JPanel aclPanel;



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

        // DHCP
        dhcpPanel = new JPanel();
        dhcpConfiguration();

        // HSRP
        hsrpPanel = new JPanel();
        hsrpConfiguration();

        // ACL's

        // Basic-Config

        // SSH

        // NAT

        // PAT

        // IPv6

        // static routes

        // cdp run, no lldp run

        // Add panels to the configuration panel
        configPanel.add(ospfPanel);
        configPanel.add(ripPanel);
        configPanel.add(dhcpPanel);
        configPanel.add(hsrpPanel);

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

    private void hsrpConfiguration() {
        hsrpPanel.setLayout(new BoxLayout(hsrpPanel, BoxLayout.Y_AXIS));
        hsrpPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        hsrpCheckBox = new JCheckBox("HSRP");
        hsrpPanel.add(hsrpCheckBox);

        // Fields and Labels (initially hidden)
        JLabel hsrpGroupLabel = new JLabel("Group:");
        hsrpGroupField = new JTextField(20);
        hsrpGroupField.setMaximumSize(new Dimension(Integer.MAX_VALUE, hsrpGroupField.getPreferredSize().height));
        hsrpGroupLabel.setVisible(false);
        hsrpGroupField.setVisible(false);
        hsrpPanel.add(hsrpGroupLabel);
        hsrpPanel.add(hsrpGroupField);

        JLabel hsrpInterfaceLabel = new JLabel("Interface:");
        hsrpInterfaceField = new JTextField(20);
        hsrpInterfaceField.setMaximumSize(new Dimension(Integer.MAX_VALUE, hsrpInterfaceField.getPreferredSize().height));
        hsrpInterfaceLabel.setVisible(false);
        hsrpInterfaceField.setVisible(false);
        hsrpPanel.add(hsrpInterfaceLabel);
        hsrpPanel.add(hsrpInterfaceField);

        JLabel hsrpVersionLabel = new JLabel("Version:");
        hsrpVersionField = new JTextField(20);
        hsrpVersionField.setMaximumSize(new Dimension(Integer.MAX_VALUE, hsrpVersionField.getPreferredSize().height));
        hsrpVersionLabel.setVisible(false);
        hsrpVersionField.setVisible(false);
        hsrpPanel.add(hsrpVersionLabel);
        hsrpPanel.add(hsrpVersionField);

        JLabel hsrpIpPriorityLabel = new JLabel("IP Priority:");
        hsrpIpPriorityField = new JTextField(20);
        hsrpIpPriorityField.setMaximumSize(new Dimension(Integer.MAX_VALUE, hsrpIpPriorityField.getPreferredSize().height));
        hsrpIpPriorityLabel.setVisible(false);
        hsrpIpPriorityField.setVisible(false);
        hsrpPanel.add(hsrpIpPriorityLabel);
        hsrpPanel.add(hsrpIpPriorityField);

        JLabel hsrpPreemptionLabel = new JLabel("Preemption:");
        hsrpPreemptionCheckBox = new JCheckBox();
        hsrpPreemptionLabel.setVisible(false);
        hsrpPreemptionCheckBox.setVisible(false);
        hsrpPanel.add(hsrpPreemptionLabel);
        hsrpPanel.add(hsrpPreemptionCheckBox);

        hsrpCheckBox.addActionListener(e -> {
            boolean selected = hsrpCheckBox.isSelected();
            hsrpGroupLabel.setVisible(selected);
            hsrpGroupField.setVisible(selected);
            hsrpInterfaceLabel.setVisible(selected);
            hsrpInterfaceField.setVisible(selected);
            hsrpVersionLabel.setVisible(selected);
            hsrpVersionField.setVisible(selected);
            hsrpIpPriorityLabel.setVisible(selected);
            hsrpIpPriorityField.setVisible(selected);
            hsrpPreemptionLabel.setVisible(selected);
            hsrpPreemptionCheckBox.setVisible(selected);
            revalidate();
            repaint();
        });

        // Action listeners to update the overview area
        hsrpGroupField.addActionListener(e -> {
            overview.append("HSRP Group: " + hsrpGroupField.getText() + "\n");
            hsrpGroupField.setText("");
        });

        hsrpInterfaceField.addActionListener(e -> {
            overview.append("HSRP Interface: " + hsrpInterfaceField.getText() + "\n");
            hsrpInterfaceField.setText("");
        });

        hsrpVersionField.addActionListener(e -> {
            overview.append("HSRP Version: " + hsrpVersionField.getText() + "\n");
            hsrpVersionField.setText("");
        });

        hsrpIpPriorityField.addActionListener(e -> {
            overview.append("HSRP IP Priority: " + hsrpIpPriorityField.getText() + "\n");
            hsrpIpPriorityField.setText("");
        });

        hsrpPreemptionCheckBox.addActionListener(e -> {
            if (hsrpPreemptionCheckBox.isSelected()) {
                overview.append("HSRP Preemption: Enabled\n");
            } else {
                overview.append("HSRP Preemption: Disabled\n");
            }
        });
    }

    private void dhcpConfiguration() {
        dhcpPanel.setLayout(new BoxLayout(dhcpPanel, BoxLayout.Y_AXIS));
        dhcpPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        dhcpCheckBox = new JCheckBox("DHCP");
        dhcpPanel.add(dhcpCheckBox);

        // Fields and Labels (initially hidden)
        JLabel excludedStartLabel = new JLabel("Excluding from:");
        excludedAddressStartField = new JTextField(20);
        excludedAddressStartField.setMaximumSize(new Dimension(Integer.MAX_VALUE, excludedAddressStartField.getPreferredSize().height));
        excludedStartLabel.setVisible(false);
        excludedAddressStartField.setVisible(false);
        dhcpPanel.add(excludedStartLabel);
        dhcpPanel.add(excludedAddressStartField);

        JLabel excludedEndLabel = new JLabel("Excluding to:");
        excludedAddressEndField = new JTextField(20);
        excludedAddressEndField.setMaximumSize(new Dimension(Integer.MAX_VALUE, excludedAddressEndField.getPreferredSize().height));
        excludedEndLabel.setVisible(false);
        excludedAddressEndField.setVisible(false);
        dhcpPanel.add(excludedEndLabel);
        dhcpPanel.add(excludedAddressEndField);

        JLabel dhcpNameLabel = new JLabel("Pool Name:");
        dhcpNameField = new JTextField(20);
        dhcpNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, dhcpNameField.getPreferredSize().height));
        dhcpNameLabel.setVisible(false);
        dhcpNameField.setVisible(false);
        dhcpPanel.add(dhcpNameLabel);
        dhcpPanel.add(dhcpNameField);

        JLabel dhcpNetworkLabel = new JLabel("Network:");
        dhcpNetworkField = new JTextField(20);
        dhcpNetworkField.setMaximumSize(new Dimension(Integer.MAX_VALUE, dhcpNetworkField.getPreferredSize().height));
        dhcpNetworkLabel.setVisible(false);
        dhcpNetworkField.setVisible(false);
        dhcpPanel.add(dhcpNetworkLabel);
        dhcpPanel.add(dhcpNetworkField);

        JLabel dhcpDefaultRouterLabel = new JLabel("Default Router:");
        dhcpDefaultRouterField = new JTextField(20);
        dhcpDefaultRouterField.setMaximumSize(new Dimension(Integer.MAX_VALUE, dhcpDefaultRouterField.getPreferredSize().height));
        dhcpDefaultRouterLabel.setVisible(false);
        dhcpDefaultRouterField.setVisible(false);
        dhcpPanel.add(dhcpDefaultRouterLabel);
        dhcpPanel.add(dhcpDefaultRouterField);

        JLabel dhcpDnsLabel = new JLabel("DNS:");
        dhcpDnsField = new JTextField(20);
        dhcpDnsField.setMaximumSize(new Dimension(Integer.MAX_VALUE, dhcpDnsField.getPreferredSize().height));
        dhcpDnsLabel.setVisible(false);
        dhcpDnsField.setVisible(false);
        dhcpPanel.add(dhcpDnsLabel);
        dhcpPanel.add(dhcpDnsField);

        // DHCP Helper
        dhcpHelperCheckBox = new JCheckBox("DHCP Helper");
        dhcpHelperCheckBox.setVisible(false);
        dhcpPanel.add(dhcpHelperCheckBox);

        JLabel dhcpHelperInterfaceLabel = new JLabel("Interface:");
        dhcpHelperInterfaceField = new JTextField(20);
        dhcpHelperInterfaceField.setMaximumSize(new Dimension(Integer.MAX_VALUE, dhcpHelperInterfaceField.getPreferredSize().height));
        dhcpHelperInterfaceLabel.setVisible(false);
        dhcpHelperInterfaceField.setVisible(false);
        dhcpPanel.add(dhcpHelperInterfaceLabel);
        dhcpPanel.add(dhcpHelperInterfaceField);

        JLabel dhcpHelperIpLabel = new JLabel("IP Address:");
        dhcpHelperIpField = new JTextField(20);
        dhcpHelperIpField.setMaximumSize(new Dimension(Integer.MAX_VALUE, dhcpHelperIpField.getPreferredSize().height));
        dhcpHelperIpLabel.setVisible(false);
        dhcpHelperIpField.setVisible(false);
        dhcpPanel.add(dhcpHelperIpLabel);
        dhcpPanel.add(dhcpHelperIpField);

        dhcpCheckBox.addActionListener(e -> {
            boolean selected = dhcpCheckBox.isSelected();
            excludedStartLabel.setVisible(selected);
            excludedAddressStartField.setVisible(selected);
            excludedEndLabel.setVisible(selected);
            excludedAddressEndField.setVisible(selected);
            dhcpNameLabel.setVisible(selected);
            dhcpNameField.setVisible(selected);
            dhcpNetworkLabel.setVisible(selected);
            dhcpNetworkField.setVisible(selected);
            dhcpDefaultRouterLabel.setVisible(selected);
            dhcpDefaultRouterField.setVisible(selected);
            dhcpDnsLabel.setVisible(selected);
            dhcpDnsField.setVisible(selected);
            dhcpHelperCheckBox.setVisible(selected);
            revalidate();
            repaint();
        });

        dhcpHelperCheckBox.addActionListener(e -> {
            boolean selected = dhcpHelperCheckBox.isSelected();
            dhcpHelperInterfaceLabel.setVisible(selected);
            dhcpHelperInterfaceField.setVisible(selected);
            dhcpHelperIpLabel.setVisible(selected);
            dhcpHelperIpField.setVisible(selected);
            revalidate();
            repaint();
        });

        // Action listeners to update the overview area
        excludedAddressStartField.addActionListener(e -> {
            overview.append("DHCP Excluding Start Address: " + excludedAddressStartField.getText() + "\n");
            excludedAddressStartField.setText("");
        });

        excludedAddressEndField.addActionListener(e -> {
            overview.append("DHCP Excluding End Address: " + excludedAddressEndField.getText() + "\n");
            excludedAddressEndField.setText("");
        });

        dhcpNameField.addActionListener(e -> {
            overview.append("Pool Name: " + dhcpNameField.getText() + "\n");
            dhcpNameField.setText("");
        });

        dhcpNetworkField.addActionListener(e -> {
            overview.append("Network: " + dhcpNetworkField.getText() + "\n");
            dhcpNetworkField.setText("");
        });

        dhcpDefaultRouterField.addActionListener(e -> {
            overview.append("Default Router: " + dhcpDefaultRouterField.getText() + "\n");
            dhcpDefaultRouterField.setText("");
        });

        dhcpDnsField.addActionListener(e -> {
            overview.append("DNS: " + dhcpDnsField.getText() + "\n");
            dhcpDnsField.setText("");
        });

        dhcpHelperInterfaceField.addActionListener(e -> {
            overview.append("DHCP Helper Interface: " + dhcpHelperInterfaceField.getText() + "\n");
            dhcpHelperInterfaceField.setText("");
        });

        dhcpHelperIpField.addActionListener(e -> {
            overview.append("DHCP Helper IP Address: " + dhcpHelperIpField.getText() + "\n");
            dhcpHelperIpField.setText("");
        });
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
