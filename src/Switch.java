import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Switch extends JFrame {
    private JCheckBox basicConfigCheckBox;
    private JCheckBox servicePasswordEncryptionCheckBox;
    private JCheckBox noIPDomainLookupCheckBox;
    private JCheckBox ipConfigurationCheckBox;
    private JCheckBox vlanCheckBox;
    private JCheckBox sshCheckBox;
    private JCheckBox stpCheckBox;
    private JCheckBox etherChannelBox;
    private JTextField hostnameOfSwitchField;
    private JTextField bannerField;
    private JTextField enablePasswordField;
    private JTextField interfaceField;
    private JTextField ipAddressField;
    private String interfaceFieldSave;
    private JTextField vlanNumberField;
    private JTextField vlanNameField;
    private JTextField vlanIPField;
    private JTextField cidrField;
    private String[] choices = {"pvst", "rapid-pvst", "mvst"};
    private String[] choicesForEther = {"active","passive","desirable","auto"};
    private final JComboBox<String> stpModeOptions = new JComboBox<>(choices);
    private final JComboBox<String> etherChannelOptions = new JComboBox<>(choicesForEther);
    private JTextField vlanForPriorityField;
    private JTextField vlanPriorityField;
    private JTextField vlanRootPrimaryField;
    private JTextField vlanRootSecondaryField;
    private JTextField channelGroupNumberField;
    private JTextField sshDomainNameField;
    private JTextField sshKeyLengthField;
    private JTextField sshUsernameField;
    private JTextField sshPasswordField;
    private JTextField sshVtyLineField;
    private JCheckBox aclCheckBox;
    private JTextField aclNumberField;
    private JTextField aclNameField;
    private JTextField aclRuleField;
    private JTextField aclActionField;
    private JPanel aclPanel;
    private JTextArea overview;
    private JPanel basicConfigPanel;
    private JPanel vlanPanel;
    private JPanel stpPanel;
    private JPanel etherChannelPanel;
    private JPanel sshPanel;

    public Switch() {
        setTitle("Switch Configuration");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Configuration Panels
        JPanel configPanel = new JPanel();
        configPanel.setLayout(new BoxLayout(configPanel, BoxLayout.Y_AXIS));

        basicConfigPanel = new JPanel();
        basicConfigConfiguration();

        // VLAN Panel
        vlanPanel = new JPanel();
        vlanConfiguration();

        // STP Panel
        stpPanel = new JPanel();
        stpConfiguration();

        // EtherChannel Panel
        etherChannelPanel = new JPanel();
        etherChannelConfiguration();

        // SSH Panel
        sshPanel = new JPanel();
        sshConfiguration();

        // ACL Panel
        aclPanel = new JPanel();
        aclConfiguration();


        // Add panels to the configuration panel
        configPanel.add(basicConfigPanel);
        configPanel.add(vlanPanel);
        configPanel.add(stpPanel);
        configPanel.add(etherChannelPanel);
        configPanel.add(sshPanel);
        configPanel.add(aclPanel);

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

    private void basicConfigConfiguration() {
        basicConfigPanel.setLayout(new BoxLayout(basicConfigPanel, BoxLayout.Y_AXIS));
        basicConfigPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        basicConfigCheckBox = new JCheckBox("Basic Configuration");
        basicConfigPanel.add(basicConfigCheckBox);

        JLabel hostnameOfSwitch = new JLabel("Hostname: ");
        hostnameOfSwitchField = new JTextField(20);
        hostnameOfSwitchField.setMaximumSize(new Dimension(Integer.MAX_VALUE, hostnameOfSwitchField.getPreferredSize().height));
        hostnameOfSwitch.setVisible(false);
        hostnameOfSwitchField.setVisible(false);
        basicConfigPanel.add(hostnameOfSwitch);
        basicConfigPanel.add(hostnameOfSwitchField);

        JLabel banner = new JLabel("Banner: ");
        bannerField = new JTextField(20);
        bannerField.setMaximumSize(new Dimension(Integer.MAX_VALUE, hostnameOfSwitchField.getPreferredSize().height));
        banner.setVisible(false);
        bannerField.setVisible(false);
        basicConfigPanel.add(banner);
        basicConfigPanel.add(bannerField);

        JLabel enablePassword = new JLabel("Enable Password: ");
        enablePasswordField = new JTextField(20);
        enablePasswordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, enablePasswordField.getPreferredSize().height));
        enablePassword.setVisible(false);
        enablePasswordField.setVisible(false);
        basicConfigPanel.add(enablePassword);
        basicConfigPanel.add(enablePasswordField);

        servicePasswordEncryptionCheckBox = new JCheckBox("Service Password-Encryption");
        servicePasswordEncryptionCheckBox.setVisible(false);
        basicConfigPanel.add(servicePasswordEncryptionCheckBox);
        noIPDomainLookupCheckBox = new JCheckBox("No IP Domain Lookup");
        noIPDomainLookupCheckBox.setVisible(false);
        basicConfigPanel.add(noIPDomainLookupCheckBox);
        ipConfigurationCheckBox = new JCheckBox("IP Configuration");
        ipConfigurationCheckBox.setVisible(false);
        basicConfigPanel.add(ipConfigurationCheckBox);

        JLabel interfaceBasicConfig = new JLabel("Interface: ");
        interfaceField = new JTextField(20);
        interfaceField.setMaximumSize(new Dimension(Integer.MAX_VALUE, interfaceField.getPreferredSize().height));
        interfaceBasicConfig.setVisible(false);
        interfaceField.setVisible(false);
        basicConfigPanel.add(interfaceBasicConfig);
        basicConfigPanel.add(interfaceField);

        JLabel ipAddressOfInterface = new JLabel("IP Address: ");
        ipAddressField = new JTextField(20);
        ipAddressField.setMaximumSize(new Dimension(Integer.MAX_VALUE, ipAddressField.getPreferredSize().height));
        ipAddressOfInterface.setVisible(false);
        ipAddressField.setVisible(false);
        basicConfigPanel.add(ipAddressOfInterface);
        basicConfigPanel.add(ipAddressField);


        basicConfigCheckBox.addActionListener(e -> {
            boolean selected = basicConfigCheckBox.isSelected();
            banner.setVisible(selected);
            enablePassword.setVisible(selected);
            hostnameOfSwitchField.setVisible(selected);
            bannerField.setVisible(selected);
            enablePasswordField.setVisible(selected);
            servicePasswordEncryptionCheckBox.setVisible(selected);
            noIPDomainLookupCheckBox.setVisible(selected);
            ipConfigurationCheckBox.setVisible(selected);
            revalidate();
            repaint();
        });

        hostnameOfSwitchField.addActionListener(e -> {
            overview.append("Hostname of Switch: " + hostnameOfSwitchField.getText() + "\n");
            hostnameOfSwitchField.setText("");
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
                overview.append("Service Password Encryption: Enabled\n");
            } else {
                overview.append("Service Password Encryption: Disabled\n");
            }
        });

        noIPDomainLookupCheckBox.addActionListener(e -> {
            if (noIPDomainLookupCheckBox.isSelected()) {
                overview.append("Service Password Encryption: Enabled\n");
            } else {
                overview.append("Service Password Encryption: Disabled\n");
            }
        });

        ipConfigurationCheckBox.addActionListener(e -> {
            boolean selected = ipConfigurationCheckBox.isSelected();
            interfaceBasicConfig.setVisible(selected);
            ipAddressOfInterface.setVisible(selected);
            interfaceField.setVisible(selected);
            ipAddressField.setVisible(selected);
            revalidate();
            repaint();
        });

        interfaceField.addActionListener(e -> {
            overview.append("Interface: " + interfaceField.getText() + "\n");
            interfaceFieldSave = interfaceField.getText();
            interfaceField.setText("");
        });
        ipAddressField.addActionListener(e -> {
            overview.append("IP Address of Interface "+ interfaceFieldSave + ": " + ipAddressField.getText() + "\n");
            ipAddressField.setText("");
        });
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

    private void stpConfiguration() {
        stpPanel.setLayout(new BoxLayout(stpPanel, BoxLayout.Y_AXIS));
        stpPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        stpCheckBox = new JCheckBox("STP");
        stpPanel.add(stpCheckBox);

        JLabel stpMode = new JLabel("STP Mode:");
        stpMode.setVisible(false);
        stpPanel.add(stpMode);

        // Ensure stpModeOptions is properly added to the panel
        stpModeOptions.setVisible(false);
        stpModeOptions.setMaximumSize(new Dimension(100,50));
        stpPanel.add(stpModeOptions);

        JLabel vlanForSTP = new JLabel("VLAN for Priority:");
        vlanForPriorityField = new JTextField(20);
        vlanForPriorityField.setMaximumSize(new Dimension(Integer.MAX_VALUE, vlanForPriorityField.getPreferredSize().height));
        vlanForSTP.setVisible(false);
        vlanForPriorityField.setVisible(false);
        stpPanel.add(vlanForSTP);
        stpPanel.add(vlanForPriorityField);

        JLabel vlanPriority = new JLabel("VLAN Priority:");
        vlanPriorityField = new JTextField(20);
        vlanPriorityField.setMaximumSize(new Dimension(Integer.MAX_VALUE, vlanPriorityField.getPreferredSize().height));
        vlanPriority.setVisible(false);
        vlanPriorityField.setVisible(false);
        stpPanel.add(vlanPriority);
        stpPanel.add(vlanPriorityField);

        JLabel vlanRootPrimary = new JLabel("VLAN Root Primary:");
        vlanRootPrimaryField = new JTextField(20);
        vlanRootPrimaryField.setMaximumSize(new Dimension(Integer.MAX_VALUE, vlanRootPrimaryField.getPreferredSize().height));
        vlanRootPrimary.setVisible(false);
        vlanRootPrimaryField.setVisible(false);
        stpPanel.add(vlanRootPrimary);
        stpPanel.add(vlanRootPrimaryField);

        JLabel vlanRootSecondary = new JLabel("VLAN Root Secondary:");
        vlanRootSecondaryField = new JTextField(20);
        vlanRootSecondaryField.setMaximumSize(new Dimension(Integer.MAX_VALUE, vlanRootSecondaryField.getPreferredSize().height));
        vlanRootSecondary.setVisible(false);
        vlanRootSecondaryField.setVisible(false);
        stpPanel.add(vlanRootSecondary);
        stpPanel.add(vlanRootSecondaryField);

        stpCheckBox.addActionListener(e -> {
            boolean selected = stpCheckBox.isSelected();
            stpMode.setVisible(selected);
            stpModeOptions.setVisible(selected);
            vlanForSTP.setVisible(selected);
            vlanPriority.setVisible(selected);
            vlanRootPrimary.setVisible(selected);
            vlanRootSecondary.setVisible(selected);
            vlanForPriorityField.setVisible(selected);
            vlanPriorityField.setVisible(selected);
            vlanRootPrimaryField.setVisible(selected);
            vlanRootSecondaryField.setVisible(selected);
            revalidate();
            repaint();
        });

        stpModeOptions.addActionListener(e -> {
            overview.append("STP Mode: " + stpModeOptions.getSelectedItem() + "\n");
        });

        vlanForPriorityField.addActionListener(e -> {
            overview.append("VLAN for Priority: " + vlanForPriorityField.getText() + "\n");
            vlanForPriorityField.setText("");
        });

        vlanPriorityField.addActionListener(e -> {
            overview.append("VLAN Priority: " + vlanPriorityField.getText() + "\n");
            vlanPriorityField.setText("");
        });

        vlanRootPrimaryField.addActionListener(e -> {
            overview.append("VLAN Root Primary: " + vlanRootPrimaryField.getText() + "\n");
            vlanRootPrimaryField.setText("");
        });

        vlanRootSecondaryField.addActionListener(e -> {
            overview.append("VLAN Root Secondary: " + vlanRootSecondaryField.getText() + "\n");
            vlanRootSecondaryField.setText("");
        });
    }

    private void etherChannelConfiguration() {
        etherChannelPanel.setLayout(new BoxLayout(etherChannelPanel, BoxLayout.Y_AXIS));
        etherChannelPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        etherChannelBox = new JCheckBox("EtherChannel");
        etherChannelPanel.add(etherChannelBox);

        JLabel channelGroupNumber = new JLabel("Channel Group Number:");
        channelGroupNumberField = new JTextField(20);
        channelGroupNumberField.setMaximumSize(new Dimension(Integer.MAX_VALUE, channelGroupNumberField.getPreferredSize().height));
        channelGroupNumber.setVisible(false);
        channelGroupNumberField.setVisible(false);
        etherChannelPanel.add(channelGroupNumber);
        etherChannelPanel.add(channelGroupNumberField);

        JLabel channelGroupMode = new JLabel("Channel Group Mode:");
        channelGroupMode.setVisible(false);
        etherChannelPanel.add(channelGroupMode);

        etherChannelOptions.setVisible(false);
        etherChannelOptions.setMaximumSize(new Dimension(100,50));
        etherChannelPanel.add(etherChannelOptions);

        etherChannelBox.addActionListener(e -> {
            boolean selected = etherChannelBox.isSelected();
            channelGroupNumber.setVisible(selected);
            channelGroupMode.setVisible(selected);
            channelGroupNumberField.setVisible(selected);
            etherChannelOptions.setVisible(selected);
            revalidate();
            repaint();
        });

        channelGroupNumberField.addActionListener(e -> {
            overview.append("Channel Group Number: " + channelGroupNumberField.getText() + "\n");
            channelGroupNumberField.setText("");
        });

        etherChannelOptions.addActionListener(e -> {
            overview.append("EtherChannel Mode: " + etherChannelOptions.getSelectedItem() + "\n");
        });
    }

    private void sshConfiguration() {
        sshPanel.setLayout(new BoxLayout(sshPanel, BoxLayout.Y_AXIS));
        sshPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        sshCheckBox = new JCheckBox("SSH");
        sshPanel.add(sshCheckBox);

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

    private void aclConfiguration() {
        aclPanel.setLayout(new BoxLayout(aclPanel, BoxLayout.Y_AXIS));
        aclPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        aclCheckBox = new JCheckBox("ACL");
        aclPanel.add(aclCheckBox);

        JLabel aclNumberLabel = new JLabel("ACL Number:");
        aclNumberField = new JTextField(20);
        aclNumberField.setMaximumSize(new Dimension(Integer.MAX_VALUE, aclNumberField.getPreferredSize().height));
        aclNumberLabel.setVisible(false);
        aclNumberField.setVisible(false);
        aclPanel.add(aclNumberLabel);
        aclPanel.add(aclNumberField);

        JLabel aclNameLabel = new JLabel("ACL Name:");
        aclNameField = new JTextField(20);
        aclNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, aclNameField.getPreferredSize().height));
        aclNameLabel.setVisible(false);
        aclNameField.setVisible(false);
        aclPanel.add(aclNameLabel);
        aclPanel.add(aclNameField);

        JLabel aclRuleLabel = new JLabel("ACL Rule:");
        aclRuleField = new JTextField(20);
        aclRuleField.setMaximumSize(new Dimension(Integer.MAX_VALUE, aclRuleField.getPreferredSize().height));
        aclRuleLabel.setVisible(false);
        aclRuleField.setVisible(false);
        aclPanel.add(aclRuleLabel);
        aclPanel.add(aclRuleField);

        JLabel aclActionLabel = new JLabel("ACL Action:");
        aclActionField = new JTextField(20);
        aclActionField.setMaximumSize(new Dimension(Integer.MAX_VALUE, aclActionField.getPreferredSize().height));
        aclActionLabel.setVisible(false);
        aclActionField.setVisible(false);
        aclPanel.add(aclActionLabel);
        aclPanel.add(aclActionField);

        aclCheckBox.addActionListener(e -> {
            boolean selected = aclCheckBox.isSelected();
            aclNumberLabel.setVisible(selected);
            aclNameLabel.setVisible(selected);
            aclRuleLabel.setVisible(selected);
            aclActionLabel.setVisible(selected);
            aclNumberField.setVisible(selected);
            aclNameField.setVisible(selected);
            aclRuleField.setVisible(selected);
            aclActionField.setVisible(selected);
            revalidate();
            repaint();
        });

        aclNumberField.addActionListener(e -> {
            overview.append("ACL Number: " + aclNumberField.getText() + "\n");
            aclNumberField.setText("");
        });

        aclNameField.addActionListener(e -> {
            overview.append("ACL Name: " + aclNameField.getText() + "\n");
            aclNameField.setText("");
        });

        aclRuleField.addActionListener(e -> {
            overview.append("ACL Rule: " + aclRuleField.getText() + "\n");
            aclRuleField.setText("");
        });

        aclActionField.addActionListener(e -> {
            overview.append("ACL Action: " + aclActionField.getText() + "\n");
            aclActionField.setText("");
        });
    }
}
