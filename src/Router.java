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
    private JCheckBox basicCheckBox;
    private JCheckBox accessCheckBox;
    private JCheckBox sshCheckBox;
    private JCheckBox natCheckBox;
    private JCheckBox staticNatCheckBox;
    private JCheckBox dynamicNatCheckBox;

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

    /* Basic Configuration */

    private JTextField hostnameField;
    private JTextField bannerField;
    private JCheckBox noIpDomainLookupCheckBox;
    private JCheckBox ipCheckBox;
    private JCheckBox servicePasswordEncryptionCheckBox;
    private JTextField enablePasswordField;
    private JTextField interfaceField;
    private JTextField ipAddressField;

    /* Access */
    private JTextField accessLineField;
    private JTextField accessPasswordField;
    private JCheckBox accessLoginCheckBox;

    /* SSH */
    private JTextField sshDomainNameField;
    private JTextField sshKeyLengthField;
    private JTextField sshUsernameField;
    private JTextField sshPasswordField;
    private JTextField sshVtyLineField;

    /* NAT */
    private JTextField natInterfaceField;
    private JTextField directionField;
    private JTextField natPrivateAddressField;
    private JTextField natPublicAddressField;
    private JTextField natPoolNameField;
    private JTextField dynamicStartIpField;
    private JTextField dynamicEndIpField;
    private JTextField natNetmaskField;
    private JTextField natAccessListField;
    private JTextField natPermitDenyField;
    private JTextField natNetworkForAccessField;
    private JTextField natWildcardMaskField;

    /* JPanels */
    private JTextArea overview;
    private JPanel ospfPanel;
    private JPanel ripPanel;
    private JPanel dhcpPanel;
    private JPanel hsrpPanel;
    private JPanel basicPanel;
    private JPanel accessPanel;
    private JPanel sshPanel;
    private JPanel natPanel;


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

        // Basic-Config
        basicPanel = new JPanel();
        basicConfiguration();

        // Access
        accessPanel = new JPanel();
        accessConfiguration();

        // ACL's

        // SSH
        sshPanel = new JPanel();
        sshConfiguration();

        // NAT
        natPanel = new JPanel();
        //natConfiguration();

        // PAT

        // cdp run, no lldp run

        // Add panels to the configuration panel
        configPanel.add(basicPanel);
        configPanel.add(accessPanel);
        configPanel.add(ospfPanel);
        configPanel.add(ripPanel);
        configPanel.add(dhcpPanel);
        configPanel.add(hsrpPanel);
        configPanel.add(sshPanel);
        configPanel.add(natPanel);

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


    private void sshConfiguration() {
        sshPanel = new JPanel();
        sshPanel.setLayout(new BoxLayout(sshPanel, BoxLayout.Y_AXIS));
        sshPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        sshCheckBox = new JCheckBox("SSH");
        sshPanel.add(sshCheckBox);

        // Fields and Labels (initially hidden)
        JLabel domainNameLabel = new JLabel("Domain Name:");
        sshDomainNameField = new JTextField(20);
        sshDomainNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, sshDomainNameField.getPreferredSize().height));
        domainNameLabel.setVisible(false);
        sshDomainNameField.setVisible(false);
        sshPanel.add(domainNameLabel);
        sshPanel.add(sshDomainNameField);

        JLabel keyLengthLabel = new JLabel("Key Length:");
        sshKeyLengthField = new JTextField(20);
        sshKeyLengthField.setMaximumSize(new Dimension(Integer.MAX_VALUE, sshKeyLengthField.getPreferredSize().height));
        keyLengthLabel.setVisible(false);
        sshKeyLengthField.setVisible(false);
        sshPanel.add(keyLengthLabel);
        sshPanel.add(sshKeyLengthField);

        JLabel usernameLabel = new JLabel("Username:");
        sshUsernameField = new JTextField(20);
        sshUsernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, sshUsernameField.getPreferredSize().height));
        usernameLabel.setVisible(false);
        sshUsernameField.setVisible(false);
        sshPanel.add(usernameLabel);
        sshPanel.add(sshUsernameField);

        JLabel sshPasswordLabel = new JLabel("Password:");
        sshPasswordField = new JTextField(20);
        sshPasswordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, sshPasswordField.getPreferredSize().height));
        sshPasswordLabel.setVisible(false);
        sshPasswordField.setVisible(false);
        sshPanel.add(sshPasswordLabel);
        sshPanel.add(sshPasswordField);

        JLabel vtyLineLabel = new JLabel("VTY Line:");
        sshVtyLineField = new JTextField(20);
        sshVtyLineField.setMaximumSize(new Dimension(Integer.MAX_VALUE, sshVtyLineField.getPreferredSize().height));
        vtyLineLabel.setVisible(false);
        sshVtyLineField.setVisible(false);
        sshPanel.add(vtyLineLabel);
        sshPanel.add(sshVtyLineField);

        sshCheckBox.addActionListener(e -> {
            boolean selected = sshCheckBox.isSelected();
            domainNameLabel.setVisible(selected);
            sshDomainNameField.setVisible(selected);
            keyLengthLabel.setVisible(selected);
            sshKeyLengthField.setVisible(selected);
            usernameLabel.setVisible(selected);
            sshUsernameField.setVisible(selected);
            sshPasswordLabel.setVisible(selected);
            sshPasswordField.setVisible(selected);
            vtyLineLabel.setVisible(selected);
            sshVtyLineField.setVisible(selected);
            revalidate();
            repaint();
        });

        // Action listeners to update the overview area
        sshDomainNameField.addActionListener(e -> {
            overview.append("Domain Name: " + sshDomainNameField.getText() + "\n");
            sshDomainNameField.setText("");
        });

        sshKeyLengthField.addActionListener(e -> {
            overview.append("Key Length: " + sshKeyLengthField.getText() + "\n");
            sshKeyLengthField.setText("");
        });

        sshUsernameField.addActionListener(e -> {
            overview.append("Username: " + sshUsernameField.getText() + "\n");
            sshUsernameField.setText("");
        });

        sshPasswordField.addActionListener(e -> {
            overview.append("Password: " + sshPasswordField.getText() + "\n");
            sshPasswordField.setText("");
        });

        sshVtyLineField.addActionListener(e -> {
            overview.append("VTY Line: " + sshVtyLineField.getText() + "\n");
            sshVtyLineField.setText("");
        });
    }

    private void basicConfiguration() {
        basicPanel.setLayout(new BoxLayout(basicPanel, BoxLayout.Y_AXIS));
        basicPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        basicCheckBox = new JCheckBox("Basic Configuration");
        basicPanel.add(basicCheckBox);

        // Fields and Labels (initially hidden)
        JLabel hostnameLabel = new JLabel("Hostname:");
        hostnameField = new JTextField(20);
        hostnameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, hostnameField.getPreferredSize().height));
        hostnameLabel.setVisible(false);
        hostnameField.setVisible(false);
        basicPanel.add(hostnameLabel);
        basicPanel.add(hostnameField);

        JLabel bannerLabel = new JLabel("Banner:");
        bannerField = new JTextField(20);
        bannerField.setMaximumSize(new Dimension(Integer.MAX_VALUE, bannerField.getPreferredSize().height));
        bannerLabel.setVisible(false);
        bannerField.setVisible(false);
        basicPanel.add(bannerLabel);
        basicPanel.add(bannerField);

        JLabel enablePasswordLabel = new JLabel("Enable Password:");
        enablePasswordField = new JTextField(20);
        enablePasswordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, enablePasswordField.getPreferredSize().height));
        enablePasswordLabel.setVisible(false);
        enablePasswordField.setVisible(false);
        basicPanel.add(enablePasswordLabel);
        basicPanel.add(enablePasswordField);

        servicePasswordEncryptionCheckBox = new JCheckBox("Service Password-Encryption");
        servicePasswordEncryptionCheckBox.setVisible(false);
        basicPanel.add(servicePasswordEncryptionCheckBox);

        noIpDomainLookupCheckBox = new JCheckBox("No IP Domain Lookup");
        noIpDomainLookupCheckBox.setVisible(false);
        basicPanel.add(noIpDomainLookupCheckBox);

        // IP Configuration
        ipCheckBox = new JCheckBox("IP Configuration");
        ipCheckBox.setVisible(false);
        basicPanel.add(ipCheckBox);

        JLabel interfaceLabel = new JLabel("Interface:");
        interfaceField = new JTextField(20);
        interfaceField.setMaximumSize(new Dimension(Integer.MAX_VALUE, interfaceField.getPreferredSize().height));
        interfaceLabel.setVisible(false);
        interfaceField.setVisible(false);
        basicPanel.add(interfaceLabel);
        basicPanel.add(interfaceField);

        JLabel ipAddressLabel = new JLabel("IP Address:");
        ipAddressField = new JTextField(20);
        ipAddressField.setMaximumSize(new Dimension(Integer.MAX_VALUE, ipAddressField.getPreferredSize().height));
        ipAddressLabel.setVisible(false);
        ipAddressField.setVisible(false);
        basicPanel.add(ipAddressLabel);
        basicPanel.add(ipAddressField);

        basicCheckBox.addActionListener(e -> {
            boolean selected = basicCheckBox.isSelected();
            hostnameLabel.setVisible(selected);
            hostnameField.setVisible(selected);
            bannerLabel.setVisible(selected);
            bannerField.setVisible(selected);
            enablePasswordLabel.setVisible(selected);
            enablePasswordField.setVisible(selected);
            servicePasswordEncryptionCheckBox.setVisible(selected);
            noIpDomainLookupCheckBox.setVisible(selected);
            ipCheckBox.setVisible(selected);
            if (!selected) {
                // Hide IP configuration fields if the basic configuration is deselected
                interfaceLabel.setVisible(false);
                interfaceField.setVisible(false);
                ipAddressLabel.setVisible(false);
                ipAddressField.setVisible(false);
                ipCheckBox.setSelected(false);
            }
            revalidate();
            repaint();
        });

        ipCheckBox.addActionListener(e -> {
            boolean selected = ipCheckBox.isSelected();
            interfaceLabel.setVisible(selected);
            interfaceField.setVisible(selected);
            ipAddressLabel.setVisible(selected);
            ipAddressField.setVisible(selected);
            revalidate();
            repaint();
        });

        // Action listeners to update the overview area
        hostnameField.addActionListener(e -> {
            overview.append("Hostname: " + hostnameField.getText() + "\n");
            hostnameField.setText("");
        });

        bannerField.addActionListener(e -> {
            overview.append("Banner: " + bannerField.getText() + "\n");
            bannerField.setText("");
        });

        enablePasswordField.addActionListener(e -> {
            overview.append("Enable Password: " + enablePasswordField.getText() + "\n");
            enablePasswordField.setText("");
        });

        servicePasswordEncryptionCheckBox.addActionListener(e -> {
            if (servicePasswordEncryptionCheckBox.isSelected()) {
                overview.append("Service Password-Encryption: Enabled\n");
            } else {
                overview.append("Service Password-Encryption: Disabled\n");
            }
        });

        noIpDomainLookupCheckBox.addActionListener(e -> {
            if (noIpDomainLookupCheckBox.isSelected()) {
                overview.append("No IP Domain Lookup: Enabled\n");
            } else {
                overview.append("No IP Domain Lookup: Disabled\n");
            }
        });

        interfaceField.addActionListener(e -> {
            overview.append("Interface: " + interfaceField.getText() + "\n");
            interfaceField.setText("");
        });

        ipAddressField.addActionListener(e -> {
            overview.append("IP Address: " + ipAddressField.getText() + "\n");
            ipAddressField.setText("");
        });
    }

    private void accessConfiguration() {
        accessPanel.setLayout(new BoxLayout(accessPanel, BoxLayout.Y_AXIS));
        accessPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        accessCheckBox = new JCheckBox("Access Configuration");
        accessPanel.add(accessCheckBox);

        // Fields and Labels (initially hidden)
        JLabel lineLabel = new JLabel("Line:");
        accessLineField = new JTextField(20);
        accessLineField.setMaximumSize(new Dimension(Integer.MAX_VALUE, accessLineField.getPreferredSize().height));
        lineLabel.setVisible(false);
        accessLineField.setVisible(false);
        accessPanel.add(lineLabel);
        accessPanel.add(accessLineField);

        JLabel passwordLabel = new JLabel("Password:");
        accessPasswordField = new JTextField(20);
        accessPasswordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, accessPasswordField.getPreferredSize().height));
        passwordLabel.setVisible(false);
        accessPasswordField.setVisible(false);
        accessPanel.add(passwordLabel);
        accessPanel.add(accessPasswordField);

        accessLoginCheckBox = new JCheckBox("Login");
        accessLoginCheckBox.setVisible(false);
        accessPanel.add(accessLoginCheckBox);

        accessCheckBox.addActionListener(e -> {
            boolean selected = accessCheckBox.isSelected();
            lineLabel.setVisible(selected);
            accessLineField.setVisible(selected);
            passwordLabel.setVisible(selected);
            accessPasswordField.setVisible(selected);
            accessLoginCheckBox.setVisible(selected);
            revalidate();
            repaint();
        });

        // Action listeners to update the overview area
        accessLineField.addActionListener(e -> {
            overview.append("Line: " + accessLineField.getText() + "\n");
            accessLineField.setText("");
        });

        accessPasswordField.addActionListener(e -> {
            overview.append("Password: " + accessPasswordField.getText() + "\n");
            accessPasswordField.setText("");
        });

        accessLoginCheckBox.addActionListener(e -> {
            if (accessLoginCheckBox.isSelected()) {
                overview.append("Login: Enabled\n");
            } else {
                overview.append("Login: Disabled\n");
            }
        });
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
