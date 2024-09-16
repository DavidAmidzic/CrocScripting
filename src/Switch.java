import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Switch extends JFrame {
    private JCheckBox basicConfigCheckBox;
    private JCheckBox servicePasswordEncryptionCheckBox;
    private JCheckBox noIPDomainLookupCheckBox;
    private JCheckBox ipConfigurationCheckBox;
    private JCheckBox vlanCheckBox;
    private JCheckBox sshCheckBox;
    private JCheckBox stpCheckBox;
    private JCheckBox etherChannelBox;
    private JCheckBox portSecurityCheckBox;
    private JCheckBox stickyMacCheckBox;
    private JTextField hostnameOfSwitchField;
    private JTextField bannerField;
    private JTextField enablePasswordField;
    private JTextField interfaceField;
    private JTextField ipAddressField;
    private String interfaceFieldSave;
    private JTextField vlanNumberField;
    private JTextField vlanNameField;
    private JTextField vlanIPField;
    private JTextField maxMacField, violationActionField, staticMacField;;
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
    private JTextArea overview;
    private JPanel basicConfigPanel;
    private JPanel vlanPanel;
    private JPanel stpPanel;
    private JPanel etherChannelPanel;
    private JPanel sshPanel;
    private JPanel portSecurityPanel;

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

        // Port Security Panel
        portSecurityPanel = new JPanel();
        portSecurityConfiguration();


        // Add panels to the configuration panel
        configPanel.add(basicConfigPanel);
        configPanel.add(vlanPanel);
        configPanel.add(stpPanel);
        configPanel.add(etherChannelPanel);
        configPanel.add(sshPanel);
        configPanel.add(portSecurityPanel);

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
                String basicConfigCommands = generateConfig();
                openCommandWindow(basicConfigCommands);
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
            overview.append("Hostname: " + hostnameOfSwitchField.getText() + "\n");
        });

        bannerField.addActionListener(e -> {
            overview.append("Banner: " + bannerField.getText() + "\n");
        });

        enablePasswordField.addActionListener(e -> {
            overview.append("Enable Password: " + enablePasswordField.getText() + "\n");
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
        });
        ipAddressField.addActionListener(e -> {
            overview.append("IP Address of Interface "+ interfaceFieldSave + ": " + ipAddressField.getText() + "\n");
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

        JLabel ipLabel = new JLabel("IP and Subnetmask: ");
        vlanIPField = new JTextField(20);
        vlanIPField.setMaximumSize(new Dimension(Integer.MAX_VALUE, vlanIPField.getPreferredSize().height));
        ipLabel.setVisible(false);
        vlanIPField.setVisible(false);
        vlanPanel.add(ipLabel);
        vlanPanel.add(vlanIPField);

        vlanCheckBox.addActionListener(e -> {
            boolean selected = vlanCheckBox.isSelected();
            numberOfVLAN.setVisible(selected);
            nameOfVLAN.setVisible(selected);
            ipLabel.setVisible(selected);
            vlanNumberField.setVisible(selected);
            vlanNameField.setVisible(selected);
            vlanIPField.setVisible(selected);
            revalidate();
            repaint();
        });

        // Action listeners to update the overview area
        vlanNumberField.addActionListener(e -> {
            overview.append("Number of VLAN: " + vlanNumberField.getText() + "\n");
        });

        vlanNameField.addActionListener(e -> {
            overview.append("Name of VLAN: " + vlanNameField.getText() + "\n");
        });

        vlanIPField.addActionListener(e -> {
            overview.append("IP: " + vlanIPField.getText() + "\n");
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
        });

        vlanPriorityField.addActionListener(e -> {
            overview.append("VLAN Priority: " + vlanPriorityField.getText() + "\n");
        });

        vlanRootPrimaryField.addActionListener(e -> {
            overview.append("VLAN Root Primary: " + vlanRootPrimaryField.getText() + "\n");
        });

        vlanRootSecondaryField.addActionListener(e -> {
            overview.append("VLAN Root Secondary: " + vlanRootSecondaryField.getText() + "\n");
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
        });

        sshKeyLengthField.addActionListener(e -> {
            overview.append("Key Length: " + sshKeyLengthField.getText() + "\n");
        });

        sshUsernameField.addActionListener(e -> {
            overview.append("Username: " + sshUsernameField.getText() + "\n");
        });

        sshPasswordField.addActionListener(e -> {
            overview.append("Password: " + sshPasswordField.getText() + "\n");
        });

        sshVtyLineField.addActionListener(e -> {
            overview.append("VTY Line: " + sshVtyLineField.getText() + "\n");
        });
    }

    private void portSecurityConfiguration() {
        portSecurityPanel.setLayout(new BoxLayout(portSecurityPanel, BoxLayout.Y_AXIS));
        portSecurityPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        portSecurityCheckBox = new JCheckBox("Port Security");
        portSecurityPanel.add(portSecurityCheckBox);

        JLabel maxMacLabel = new JLabel("Max MAC Addresses:");
        maxMacField = new JTextField(20);
        maxMacField.setMaximumSize(new Dimension(Integer.MAX_VALUE, maxMacField.getPreferredSize().height));
        maxMacLabel.setVisible(false);
        maxMacField.setVisible(false);
        portSecurityPanel.add(maxMacLabel);
        portSecurityPanel.add(maxMacField);

        JLabel violationActionLabel = new JLabel("Violation Action:");
        violationActionField = new JTextField(20);
        violationActionField.setMaximumSize(new Dimension(Integer.MAX_VALUE, violationActionField.getPreferredSize().height));
        violationActionLabel.setVisible(false);
        violationActionField.setVisible(false);
        portSecurityPanel.add(violationActionLabel);
        portSecurityPanel.add(violationActionField);

        JLabel staticMacLabel = new JLabel("Static MAC Address:");
        staticMacField = new JTextField(20);
        staticMacField.setMaximumSize(new Dimension(Integer.MAX_VALUE, staticMacField.getPreferredSize().height));
        staticMacLabel.setVisible(false);
        staticMacField.setVisible(false);
        portSecurityPanel.add(staticMacLabel);
        portSecurityPanel.add(staticMacField);

        stickyMacCheckBox = new JCheckBox("Enable Sticky MAC");
        stickyMacCheckBox.setVisible(false);
        portSecurityPanel.add(stickyMacCheckBox);

        portSecurityCheckBox.addActionListener(e -> {
            boolean selected = portSecurityCheckBox.isSelected();
            maxMacLabel.setVisible(selected);
            maxMacField.setVisible(selected);
            violationActionLabel.setVisible(selected);
            violationActionField.setVisible(selected);
            staticMacLabel.setVisible(selected);
            staticMacField.setVisible(selected);
            stickyMacCheckBox.setVisible(selected);
            revalidate();
            repaint();
        });

        maxMacField.addActionListener(e -> {
            overview.append("Max MAC Addresses: " + maxMacField.getText() + "\n");
        });

        violationActionField.addActionListener(e -> {
            overview.append("Violation Action: " + violationActionField.getText() + "\n");
        });

        staticMacField.addActionListener(e -> {
            overview.append("Static MAC Address: " + staticMacField.getText() + "\n");
        });

        stickyMacCheckBox.addActionListener(e -> {
            overview.append("Sticky MAC: " + (stickyMacCheckBox.isSelected() ? "Enabled" : "Disabled") + "\n");
        });
    }



    public String generateConfig() {
        StringBuilder commands = new StringBuilder();

        //Basic Configuration
        String hostname = hostnameOfSwitchField.getText();
        if (!hostname.isEmpty() && overview.getText().contains("Hostname: " + hostname)) {
            commands.append("hostname ").append(hostname).append("\n");
        }

        String banner = bannerField.getText();
        if (!banner.isEmpty() && overview.getText().contains("Banner: " + banner)) {
            commands.append("banner motd #").append(banner).append("#\n");
        }

        if (noIPDomainLookupCheckBox.isSelected()) {
            commands.append("no ip domain-lookup\n");
        }

        String enablePassword = enablePasswordField.getText();
        if (!enablePassword.isEmpty() && overview.getText().contains("Enable Password: " + enablePassword)) {
            commands.append("enable secret ").append(enablePassword).append("\n");
        }

        if (servicePasswordEncryptionCheckBox.isSelected()) {
            commands.append("service password-encryption\n");
        }

        if (ipConfigurationCheckBox.isSelected()) {
            String interfaceText = interfaceField.getText();
            if (!interfaceText.isEmpty() && overview.getText().contains("Interface: " + interfaceText)) {
                commands.append("interface ").append(interfaceText).append("\n");
            }

            String ipAddress = ipAddressField.getText();
            if (!ipAddress.isEmpty() && overview.getText().contains("IP Address of Interface "+ interfaceFieldSave + ": " + ipAddress)) {
                commands.append("ip address ").append(ipAddress).append("\n");
                commands.append("no shutdown").append("\n");
                commands.append("exit").append("\n");
            }
        }

        // VLAN
        String numberOfVlan = vlanNumberField.getText();
        if (!numberOfVlan.isEmpty() && overview.getText().contains("Number of VLAN: " + numberOfVlan)) {
            commands.append("vlan ").append(numberOfVlan).append("\n");
        }
        String nameOfVlan = vlanNameField.getText();
        if (!nameOfVlan.isEmpty() && overview.getText().contains("Name of VLAN: " + nameOfVlan)) {
            commands.append("name ").append(nameOfVlan).append("\n");
        }
        String ipOfVlan = vlanIPField.getText();
        if (!ipOfVlan.isEmpty() && overview.getText().contains("IP: " + ipOfVlan)) {
            commands.append("interface vlan ").append(numberOfVlan).append("\n");
            commands.append("ip address ").append(ipOfVlan).append("\n");
            commands.append("no shutdown ").append("\n");
            commands.append("exit\n");

        }

        // STP
        String stpOptions = stpModeOptions.getSelectedItem().toString();
        if (!stpOptions.isEmpty() && overview.getText().contains("STP Mode: " + stpOptions)) {
            commands.append("spanning-tree mode ").append(stpOptions).append("\n");
        }
        String vlanForSTPString = vlanForPriorityField.getText();
        if (!vlanForSTPString.isEmpty() && overview.getText().contains("VLAN for Priority: " + vlanForSTPString)) {
            commands.append("spanning-tree vlan ").append(vlanForSTPString).append(" priority ").append(vlanPriorityField.getText()).append("\n");
        }
        String vlanRootPrimaryString = vlanRootPrimaryField.getText();
        if (!vlanRootPrimaryString.isEmpty() && overview.getText().contains("VLAN Root Primary: " + vlanRootPrimaryString)) {
            commands.append("spanning-tree vlan ").append(vlanRootPrimaryString).append(" root primary").append("\n");
        }
        String vlanRootSecondaryString = vlanRootSecondaryField.getText();
        if (!vlanRootSecondaryString.isEmpty() && overview.getText().contains("VLAN Root Secondary: " + vlanRootSecondaryString)) {
            commands.append("spanning-tree vlan ").append(vlanForSTPString).append(" root secondary").append("\n");
        }

        // EtherChannel
        String channelGroupNumberString = channelGroupNumberField.getText();
        String channelGroupModeString = etherChannelOptions.getSelectedItem().toString();
        if (!channelGroupNumberString.isEmpty() && overview.getText().contains("Channel Group Number: " + channelGroupNumberString) && !channelGroupModeString.isEmpty() && overview.getText().contains("EtherChannel Mode: " + channelGroupModeString)) {
            commands.append("channel-group ").append(channelGroupNumberString).append(" mode ").append(channelGroupModeString).append("\n");
        }

        // SSH
        if (sshCheckBox.isSelected()) {
            String domainName = sshDomainNameField.getText();
            if (!domainName.isEmpty() && overview.getText().contains("Domain Name: " + domainName)) {
                commands.append("\nip domain-name ").append(domainName).append("\n");
            }

            String keyLength = sshKeyLengthField.getText();
            if (!keyLength.isEmpty() && overview.getText().contains("Key Length: " + keyLength)) {
                commands.append("crypto key generate rsa general-keys modulus ").append(keyLength).append("\n");
            }

            String username = sshUsernameField.getText();
            String passwordSSH = sshPasswordField.getText();
            if (!username.isEmpty() && !passwordSSH.isEmpty() &&
                    overview.getText().contains("Username: " + username) &&
                    overview.getText().contains("Password: " + passwordSSH)) {
                commands.append("username ").append(username).append(" secret ").append(passwordSSH).append("\n");
            }

            String vtyLine = sshVtyLineField.getText();
            if (!vtyLine.isEmpty() && overview.getText().contains("VTY Line: " + vtyLine)) {
                commands.append("line vty ").append(vtyLine).append("\n");
                commands.append("login local\n");
                commands.append("transport input ssh\n");
                commands.append("ip ssh version 2\n");
                commands.append("exit\n");
            }
        }

        // Port Security Configuration
        if (portSecurityCheckBox.isSelected()) {
            String maxMac = maxMacField.getText();
            String violationAction = violationActionField.getText();
            String staticMac = staticMacField.getText();
            boolean stickyMac = stickyMacCheckBox.isSelected();

            if (!maxMac.isEmpty() && overview.getText().contains("Max MAC Addresses: " + maxMac)) {
                commands.append("switchport port-security maximum ").append(maxMac).append("\n");
            }

            if (!violationAction.isEmpty() && overview.getText().contains("Violation Action: " + violationAction)) {
                commands.append("switchport port-security violation ").append(violationAction).append("\n");
            }

            if (!staticMac.isEmpty() && overview.getText().contains("Static MAC Address: " + staticMac)) {
                commands.append("switchport port-security mac-address ").append(staticMac).append("\n");
            }

            if (stickyMac && overview.getText().contains("Sticky MAC: Enabled")) {
                commands.append("switchport port-security mac-address sticky\n");
            }

            commands.append("switchport port-security\n");
        }
        return commands.toString();

    }
    private void openCommandWindow(String commands) {
        JFrame commandFrame = new JFrame("Generated Commands");
        commandFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        commandFrame.setSize(500, 400);

        JTextArea commandArea = new JTextArea(commands);
        commandArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(commandArea);

        JButton copyButton = new JButton("Copy");
        copyButton.addActionListener(e -> {
            StringSelection stringSelection = new StringSelection(commands);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        });

        //Save-As Button
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");
            int userSelection = fileChooser.showSaveDialog(commandFrame);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                try (FileWriter fileWriter = new FileWriter(fileToSave)) {
                    fileWriter.write(commands);
                    JOptionPane.showMessageDialog(commandFrame, "File has been saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(commandFrame, "An error occurred while saving the file.", "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(copyButton);
        buttonPanel.add(saveButton);

        commandFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        commandFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        commandFrame.setVisible(true);
    }
}
