import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

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

    JTextField hostnameField;
    JTextField bannerField;
    JCheckBox noIpDomainLookupCheckBox;
    private JCheckBox ipCheckBox;
    private JCheckBox servicePasswordEncryptionCheckBox;
    JTextField enablePasswordField;
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

        // SSH
        sshPanel = new JPanel();
        sshConfiguration();

        // NAT
        natPanel = new JPanel();
        natConfiguration();

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
                String basicConfigCommands = generateConfig();
                openCommandWindow(basicConfigCommands);
            }
        });

        // Display Area
        overview = new JTextArea();
        overview.setEditable(false);
        add(new JScrollPane(overview), BorderLayout.CENTER);

        setVisible(true);
    }

    private String generateConfig() {
        StringBuilder commands = new StringBuilder();

        //basic config
        String hostname = hostnameField.getText();
        if (!hostname.isEmpty() && overview.getText().contains("Hostname: " + hostname)) {
            commands.append("hostname ").append(hostname).append("\n");
        }

        String banner = bannerField.getText();
        if (!banner.isEmpty() && overview.getText().contains("Banner: " + banner)) {
            commands.append("banner motd #").append(banner).append("#\n");
        }

        if (noIpDomainLookupCheckBox.isSelected()) {
            commands.append("no ip domain-lookup\n");
        }

        String enablePassword = enablePasswordField.getText();
        if (!enablePassword.isEmpty() && overview.getText().contains("Enable Password: " + enablePassword)) {
            commands.append("enable secret ").append(enablePassword).append("\n");
        }

        if (servicePasswordEncryptionCheckBox.isSelected()) {
            commands.append("service password-encryption\n");
        }

        if (ipCheckBox.isSelected()) {
            String interfaceText = interfaceField.getText();
            if (!interfaceText.isEmpty() && overview.getText().contains("Interface: " + interfaceText)) {
                commands.append("interface ").append(interfaceText).append("\n");
            }

            String ipAddress = ipAddressField.getText();
            if (!ipAddress.isEmpty() && overview.getText().contains("IP Address: " + ipAddress)) {
                commands.append("ip address ").append(ipAddress).append("\n");
            }
        }

        // Access Configuration
        String line = accessLineField.getText();
        if (!line.isEmpty() && overview.getText().contains("Line: " + line)) {
            commands.append("line ").append(line).append("\n");
        }

        String password = accessPasswordField.getText();
        if (!password.isEmpty() && overview.getText().contains("Password: " + password)) {
            commands.append("password ").append(password).append("\n");
        }

        if (accessLoginCheckBox.isSelected()) {
            commands.append("login\n");
        }

        // OSPF Configuration
//        String ospfProcessId = ospfProcessIdField.getText();
//        if (!ospfProcessId.isEmpty() && overview.getText().contains("OSPF Process ID: " + ospfProcessId)) {
//            commands.append("router ospf ").append(ospfProcessId).append("\n");
//        }
//
//        String ospfNetwork = ospfNetworkField.getText();
//        String ospfWildcard = ospfWildcardField.getText();
//        String ospfArea = ospfAreaField.getText();
//        if (!ospfNetwork.isEmpty() && !ospfWildcard.isEmpty() && !ospfArea.isEmpty() &&
//                overview.getText().contains("Network: " + ospfNetwork) &&
//                overview.getText().contains("Wildcard: " + ospfWildcard) &&
//                overview.getText().contains("Area: " + ospfArea)) {
//            commands.append("network ").append(ospfNetwork).append(" ").append(ospfWildcard)
//                    .append(" area ").append(ospfArea).append("\n");
//        }
//
//        if (ospfCheckBox.isSelected()) {
//            commands.append("router ospf ").append(ospfProcessId).append("\n");
//        }
        String ospfGroup = ospfGroupField.getText();
        if (!ospfGroup.isEmpty() && overview.getText().contains("OSPF Group: " + ospfGroup)) {
            commands.append("router ospf ").append(ospfGroup).append("\n");

            String ospfRouterId = ospfRouterIdField.getText();
            if (!ospfRouterId.isEmpty() && overview.getText().contains("OSPF Router ID: " + ospfRouterId)) {
                commands.append("router-id ").append(ospfRouterId).append("\n");
            }

            String ospfNetwork = ospfNetworkField.getText();
            if (!ospfNetwork.isEmpty() && overview.getText().contains("OSPF Network: " + ospfNetwork)) {
                commands.append("network ").append(ospfNetwork).append("\n");
            }

            String ospfPassiveInterface = ospfPassiveInterfaceField.getText();
            if (!ospfPassiveInterface.isEmpty() && overview.getText().contains("OSPF Passive Interface: " + ospfPassiveInterface)) {
                commands.append("passive-interface ").append(ospfPassiveInterface).append("\n");
            }
        }


        // RIP Configuration
        if (ripCheckBox.isSelected()) {
            String ripVersion = ripVersionField.getText();
            if (!ripVersion.isEmpty() && overview.getText().contains("RIP Version: " + ripVersion)) {
                commands.append("router rip\n");
                commands.append("version ").append(ripVersion).append("\n");
            }

            String ripNetwork = ripNetworkField.getText();
            if (!ripNetwork.isEmpty() && overview.getText().contains("RIP Network: " + ripNetwork)) {
                commands.append("network ").append(ripNetwork).append("\n");
            }

            String ripPassiveInterface = ripPassiveInterfaceField.getText();
            if (!ripPassiveInterface.isEmpty() && overview.getText().contains("RIP Passive Interface: " + ripPassiveInterface)) {
                commands.append("passive-interface ").append(ripPassiveInterface).append("\n");
            }
        }

        if (sshCheckBox.isSelected()) {
            String domainName = sshDomainNameField.getText();
            if (!domainName.isEmpty() && overview.getText().contains("SSH Domain Name: " + domainName)) {
                commands.append("ip domain-name ").append(domainName).append("\n");
            }

            String keyLength = sshKeyLengthField.getText();
            if (!keyLength.isEmpty() && overview.getText().contains("SSH Key Length: " + keyLength)) {
                commands.append("crypto key generate rsa modulus ").append(keyLength).append("\n");
            }

            String username = sshUsernameField.getText();
            String passwordSSH = sshPasswordField.getText();
            if (!username.isEmpty() && !passwordSSH.isEmpty() &&
                    overview.getText().contains("SSH Username: " + username) &&
                    overview.getText().contains("SSH Password: " + passwordSSH)) {
                commands.append("username ").append(username).append(" password ").append(passwordSSH).append("\n");
            }

            String vtyLine = sshVtyLineField.getText();
            if (!vtyLine.isEmpty() && overview.getText().contains("SSH VTY Line: " + vtyLine)) {
                commands.append("line vty 0 ").append(vtyLine).append("\n");
                commands.append("transport input ssh\n");
                commands.append("login local\n");
            }
        }

        // DHCP Configuration
        if (dhcpCheckBox.isSelected()) {
            String excludedStart = excludedAddressStartField.getText();
            String excludedEnd = excludedAddressEndField.getText();
            if (!excludedStart.isEmpty() && !excludedEnd.isEmpty() &&
                    overview.getText().contains("DHCP Excluding Start Address: " + excludedStart) &&
                    overview.getText().contains("DHCP Excluding End Address: " + excludedEnd)) {
                commands.append("ip dhcp excluded-address ").append(excludedStart).append(" ").append(excludedEnd).append("\n");
            }

            String dhcpName = dhcpNameField.getText();
            if (!dhcpName.isEmpty() && overview.getText().contains("Pool Name: " + dhcpName)) {
                commands.append("ip dhcp pool ").append(dhcpName).append("\n");
            }

            String dhcpNetwork = dhcpNetworkField.getText();
            if (!dhcpNetwork.isEmpty() && overview.getText().contains("Network: " + dhcpNetwork)) {
                commands.append("network ").append(dhcpNetwork).append("\n");
            }

            String dhcpDefaultRouter = dhcpDefaultRouterField.getText();
            if (!dhcpDefaultRouter.isEmpty() && overview.getText().contains("Default Router: " + dhcpDefaultRouter)) {
                commands.append("default-router ").append(dhcpDefaultRouter).append("\n");
            }

            String dhcpDns = dhcpDnsField.getText();
            if (!dhcpDns.isEmpty() && overview.getText().contains("DNS: " + dhcpDns)) {
                commands.append("dns-server ").append(dhcpDns).append("\n");
            }

            if (dhcpHelperCheckBox.isSelected()) {
                String helperInterface = dhcpHelperInterfaceField.getText();
                String helperIp = dhcpHelperIpField.getText();
                if (!helperInterface.isEmpty() && !helperIp.isEmpty() &&
                        overview.getText().contains("DHCP Helper Interface: " + helperInterface) &&
                        overview.getText().contains("DHCP Helper IP Address: " + helperIp)) {
                    commands.append("interface ").append(helperInterface).append("\n");
                    commands.append("ip helper-address ").append(helperIp).append("\n");
                }
            }
        }

        // HSRP Configuration
        if (hsrpCheckBox.isSelected()) {
            String hsrpGroup = hsrpGroupField.getText();
            if (!hsrpGroup.isEmpty() && overview.getText().contains("HSRP Group: " + hsrpGroup)) {
                commands.append("standby ").append(hsrpGroup).append("\n");

                String hsrpInterface = hsrpInterfaceField.getText();
                if (!hsrpInterface.isEmpty() && overview.getText().contains("HSRP Interface: " + hsrpInterface)) {
                    commands.append("interface ").append(hsrpInterface).append("\n");
                }

                String hsrpVersion = hsrpVersionField.getText();
                if (!hsrpVersion.isEmpty() && overview.getText().contains("HSRP Version: " + hsrpVersion)) {
                    commands.append("standby version ").append(hsrpVersion).append("\n");
                }

                String hsrpIpPriority = hsrpIpPriorityField.getText();
                if (!hsrpIpPriority.isEmpty() && overview.getText().contains("HSRP IP Priority: " + hsrpIpPriority)) {
                    commands.append("standby ").append(hsrpGroup).append(" ip ").append(hsrpIpPriority).append("\n");
                }

                if (hsrpPreemptionCheckBox.isSelected()) {
                    commands.append("standby preempt\n");
                }
            }
        }

        // NAT Configuration
        if (natCheckBox.isSelected()) {
            String natInterface = natInterfaceField.getText();
            String direction = directionField.getText();

            if (!natInterface.isEmpty() && overview.getText().contains("NAT Interface: " + natInterface)) {
                commands.append("interface ").append(natInterface).append("\n");
            }

            if (!direction.isEmpty() && overview.getText().contains("Direction: " + direction)) {
                commands.append("ip nat inside source ").append(direction).append("\n");
            }

            if (staticNatCheckBox.isSelected()) {
                String privateAddress = natPrivateAddressField.getText();
                String publicAddress = natPublicAddressField.getText();

                if (!privateAddress.isEmpty() && overview.getText().contains("Private Address: " + privateAddress) &&
                        !publicAddress.isEmpty() && overview.getText().contains("Public Address: " + publicAddress)) {
                    commands.append("ip nat inside source static ").append(privateAddress).append(" ").append(publicAddress).append("\n");
                }
            }

            if (dynamicNatCheckBox.isSelected()) {
                String poolName = natPoolNameField.getText();
                String dynamicStartIp = dynamicStartIpField.getText();
                String dynamicEndIp = dynamicEndIpField.getText();
                String netmask = natNetmaskField.getText();
                String accessList = natAccessListField.getText();
                String permitDeny = natPermitDenyField.getText();
                String networkForAccess = natNetworkForAccessField.getText();
                String wildcardMask = natWildcardMaskField.getText();

                if (!poolName.isEmpty() && overview.getText().contains("Pool-Name: " + poolName)) {
                    commands.append("ip nat pool ").append(poolName).append(" ").append(dynamicStartIp).append(" ").append(dynamicEndIp).append(" netmask ").append(netmask).append("\n");
                }

                if (!accessList.isEmpty() && overview.getText().contains("Access List Number: " + accessList)) {
                    commands.append("access-list ").append(accessList).append(" ").append(permitDeny).append(" ").append(networkForAccess).append(" ").append(wildcardMask).append("\n");
                    commands.append("ip nat inside source list ").append(accessList).append(" pool ").append(poolName).append("\n");
                }
            }
        }


        return commands.toString();
    }

    private void openCommandWindow(String commands) {
        JFrame commandFrame = new JFrame("Generated Commands");
        commandFrame.setSize(500, 400);
        commandFrame.setLayout(new BorderLayout());

        JTextArea commandArea = new JTextArea(commands);
        commandArea.setEditable(false);
        commandFrame.add(new JScrollPane(commandArea), BorderLayout.CENTER);

        JButton copyButton = new JButton("Copy to Clipboard");
        copyButton.addActionListener(e -> {
            StringSelection stringSelection = new StringSelection(commands);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(copyButton);
        commandFrame.add(buttonPanel, BorderLayout.SOUTH);

        commandFrame.setVisible(true);
    }

    private void natConfiguration() {
        natPanel = new JPanel();
        natPanel.setLayout(new BoxLayout(natPanel, BoxLayout.Y_AXIS));
        natPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        natCheckBox = new JCheckBox("NAT");
        natPanel.add(natCheckBox);

        // Additional Checkboxes for Static and Dynamic NAT (initially hidden)
        staticNatCheckBox = new JCheckBox("Static NAT");
        staticNatCheckBox.setVisible(false);
        natPanel.add(staticNatCheckBox);

        dynamicNatCheckBox = new JCheckBox("Dynamic NAT");
        dynamicNatCheckBox.setVisible(false);
        natPanel.add(dynamicNatCheckBox);

        // Fields for Static NAT (initially hidden)
        JPanel staticNatPanel = new JPanel();
        staticNatPanel.setLayout(new BoxLayout(staticNatPanel, BoxLayout.Y_AXIS));
        JLabel privateAddressLabel = new JLabel("Private Address:");
        natPrivateAddressField = new JTextField(20);
        natPrivateAddressField.setMaximumSize(new Dimension(Integer.MAX_VALUE, natPrivateAddressField.getPreferredSize().height));
        privateAddressLabel.setVisible(false);
        natPrivateAddressField.setVisible(false);
        staticNatPanel.add(privateAddressLabel);
        staticNatPanel.add(natPrivateAddressField);

        JLabel publicAddressLabel = new JLabel("Public Address:");
        natPublicAddressField = new JTextField(20);
        natPublicAddressField.setMaximumSize(new Dimension(Integer.MAX_VALUE, natPublicAddressField.getPreferredSize().height));
        publicAddressLabel.setVisible(false);
        natPublicAddressField.setVisible(false);
        staticNatPanel.add(publicAddressLabel);
        staticNatPanel.add(natPublicAddressField);
        staticNatPanel.setVisible(false);
        natPanel.add(staticNatPanel);

        // Fields for Dynamic NAT (initially hidden)
        JPanel dynamicNatPanel = new JPanel();
        dynamicNatPanel.setLayout(new BoxLayout(dynamicNatPanel, BoxLayout.Y_AXIS));
        JLabel poolNameLabel = new JLabel("Pool Name:");
        natPoolNameField = new JTextField(20);
        natPoolNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, natPoolNameField.getPreferredSize().height));
        poolNameLabel.setVisible(false);
        natPoolNameField.setVisible(false);
        dynamicNatPanel.add(poolNameLabel);
        dynamicNatPanel.add(natPoolNameField);

        JLabel dynamicStartIpLabel = new JLabel("Dynamic Start IP:");
        dynamicStartIpField = new JTextField(20);
        dynamicStartIpField.setMaximumSize(new Dimension(Integer.MAX_VALUE, dynamicStartIpField.getPreferredSize().height));
        dynamicStartIpLabel.setVisible(false);
        dynamicStartIpField.setVisible(false);
        dynamicNatPanel.add(dynamicStartIpLabel);
        dynamicNatPanel.add(dynamicStartIpField);

        JLabel dynamicEndIpLabel = new JLabel("Dynamic End IP:");
        dynamicEndIpField = new JTextField(20);
        dynamicEndIpField.setMaximumSize(new Dimension(Integer.MAX_VALUE, dynamicEndIpField.getPreferredSize().height));
        dynamicEndIpLabel.setVisible(false);
        dynamicEndIpField.setVisible(false);
        dynamicNatPanel.add(dynamicEndIpLabel);
        dynamicNatPanel.add(dynamicEndIpField);

        JLabel netmaskLabel = new JLabel("Netmask:");
        natNetmaskField = new JTextField(20);
        natNetmaskField.setMaximumSize(new Dimension(Integer.MAX_VALUE, natNetmaskField.getPreferredSize().height));
        netmaskLabel.setVisible(false);
        natNetmaskField.setVisible(false);
        dynamicNatPanel.add(netmaskLabel);
        dynamicNatPanel.add(natNetmaskField);

        JLabel accessListLabel = new JLabel("Access List Number:");
        natAccessListField = new JTextField(20);
        natAccessListField.setMaximumSize(new Dimension(Integer.MAX_VALUE, natAccessListField.getPreferredSize().height));
        accessListLabel.setVisible(false);
        natAccessListField.setVisible(false);
        dynamicNatPanel.add(accessListLabel);
        dynamicNatPanel.add(natAccessListField);

        JLabel permitDenyLabel = new JLabel("Permit/Deny:");
        natPermitDenyField = new JTextField(20);
        natPermitDenyField.setMaximumSize(new Dimension(Integer.MAX_VALUE, natPermitDenyField.getPreferredSize().height));
        permitDenyLabel.setVisible(false);
        natPermitDenyField.setVisible(false);
        dynamicNatPanel.add(permitDenyLabel);
        dynamicNatPanel.add(natPermitDenyField);

        JLabel networkForAccessLabel = new JLabel("Network for Access List:");
        natNetworkForAccessField = new JTextField(20);
        natNetworkForAccessField.setMaximumSize(new Dimension(Integer.MAX_VALUE, natNetworkForAccessField.getPreferredSize().height));
        networkForAccessLabel.setVisible(false);
        natNetworkForAccessField.setVisible(false);
        dynamicNatPanel.add(networkForAccessLabel);
        dynamicNatPanel.add(natNetworkForAccessField);

        JLabel wildcardMaskLabel = new JLabel("Wildcard Mask for Access List:");
        natWildcardMaskField = new JTextField(20);
        natWildcardMaskField.setMaximumSize(new Dimension(Integer.MAX_VALUE, natWildcardMaskField.getPreferredSize().height));
        wildcardMaskLabel.setVisible(false);
        natWildcardMaskField.setVisible(false);
        dynamicNatPanel.add(wildcardMaskLabel);
        dynamicNatPanel.add(natWildcardMaskField);
        dynamicNatPanel.setVisible(false);
        natPanel.add(dynamicNatPanel);

        // Fields for Interface and Direction (initially hidden)
        JLabel interfaceLabel = new JLabel("Interface:");
        natInterfaceField = new JTextField(20);
        natInterfaceField.setMaximumSize(new Dimension(Integer.MAX_VALUE, natInterfaceField.getPreferredSize().height));
        interfaceLabel.setVisible(false);
        natInterfaceField.setVisible(false);
        natPanel.add(interfaceLabel);
        natPanel.add(natInterfaceField);

        JLabel directionLabel = new JLabel("Direction:");
        directionField = new JTextField(20);
        directionField.setMaximumSize(new Dimension(Integer.MAX_VALUE, directionField.getPreferredSize().height));
        directionLabel.setVisible(false);
        directionField.setVisible(false);
        natPanel.add(directionLabel);
        natPanel.add(directionField);

        // ActionListener for the main NAT Checkbox
        natCheckBox.addActionListener(e -> {
            boolean selected = natCheckBox.isSelected();
            staticNatCheckBox.setVisible(selected);
            dynamicNatCheckBox.setVisible(selected);
            interfaceLabel.setVisible(selected);
            natInterfaceField.setVisible(selected);
            directionLabel.setVisible(selected);
            directionField.setVisible(selected);
            revalidate();
            repaint();
        });

        // ActionListener for Static NAT Checkbox
        staticNatCheckBox.addActionListener(e -> {
            boolean selected = staticNatCheckBox.isSelected();
            privateAddressLabel.setVisible(selected);
            natPrivateAddressField.setVisible(selected);
            publicAddressLabel.setVisible(selected);
            natPublicAddressField.setVisible(selected);
            staticNatPanel.setVisible(selected);
            revalidate();
            repaint();
        });

        // ActionListener for Dynamic NAT Checkbox
        dynamicNatCheckBox.addActionListener(e -> {
            boolean selected = dynamicNatCheckBox.isSelected();
            poolNameLabel.setVisible(selected);
            natPoolNameField.setVisible(selected);
            dynamicStartIpLabel.setVisible(selected);
            dynamicStartIpField.setVisible(selected);
            dynamicEndIpLabel.setVisible(selected);
            dynamicEndIpField.setVisible(selected);
            netmaskLabel.setVisible(selected);
            natNetmaskField.setVisible(selected);
            accessListLabel.setVisible(selected);
            natAccessListField.setVisible(selected);
            permitDenyLabel.setVisible(selected);
            natPermitDenyField.setVisible(selected);
            networkForAccessLabel.setVisible(selected);
            natNetworkForAccessField.setVisible(selected);
            wildcardMaskLabel.setVisible(selected);
            natWildcardMaskField.setVisible(selected);
            dynamicNatPanel.setVisible(selected);
            revalidate();
            repaint();
        });

        // Action listeners to update the overview area for Static NAT
        natPrivateAddressField.addActionListener(e -> {
            overview.append("NAT Private Address: " + natPrivateAddressField.getText() + "\n");
        });

        natPublicAddressField.addActionListener(e -> {
            overview.append("NAT Public Address: " + natPublicAddressField.getText() + "\n");
        });

        // Action listeners to update the overview area for Dynamic NAT
        natPoolNameField.addActionListener(e -> {
            overview.append("NAT Pool Name: " + natPoolNameField.getText() + "\n");
        });

        dynamicStartIpField.addActionListener(e -> {
            overview.append("Dynamic Start IP: " + dynamicStartIpField.getText() + "\n");
        });

        dynamicEndIpField.addActionListener(e -> {
            overview.append("Dynamic End IP: " + dynamicEndIpField.getText() + "\n");
        });

        natNetmaskField.addActionListener(e -> {
            overview.append("Netmask: " + natNetmaskField.getText() + "\n");
        });

        natAccessListField.addActionListener(e -> {
            overview.append("Access List Number: " + natAccessListField.getText() + "\n");
        });

        natPermitDenyField.addActionListener(e -> {
            overview.append("Permit/Deny: " + natPermitDenyField.getText() + "\n");
        });

        natNetworkForAccessField.addActionListener(e -> {
            overview.append("Network for Access List: " + natNetworkForAccessField.getText() + "\n");
        });

        natWildcardMaskField.addActionListener(e -> {
            overview.append("Wildcard Mask for Access List: " + natWildcardMaskField.getText() + "\n");
        });

        natInterfaceField.addActionListener(e -> {
            overview.append("NAT Interface: " + natInterfaceField.getText() + "\n");
        });

        directionField.addActionListener(e -> {
            overview.append("NAT Direction: " + directionField.getText() + "\n");
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

        JLabel passwordLabel = new JLabel("Password:");
        sshPasswordField = new JTextField(20);
        sshPasswordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, sshPasswordField.getPreferredSize().height));
        passwordLabel.setVisible(false);
        sshPasswordField.setVisible(false);
        sshPanel.add(passwordLabel);
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
            passwordLabel.setVisible(selected);
            sshPasswordField.setVisible(selected);
            vtyLineLabel.setVisible(selected);
            sshVtyLineField.setVisible(selected);
            revalidate();
            repaint();
        });

        // Action listeners to update the overview area
        sshDomainNameField.addActionListener(e -> {
            overview.append("SSH Domain Name: " + sshDomainNameField.getText() + "\n");
        });

        sshKeyLengthField.addActionListener(e -> {
            overview.append("SSH Key Length: " + sshKeyLengthField.getText() + "\n");
        });

        sshUsernameField.addActionListener(e -> {
            overview.append("SSH Username: " + sshUsernameField.getText() + "\n");
        });

        sshPasswordField.addActionListener(e -> {
            overview.append("SSH Password: " + sshPasswordField.getText() + "\n");
        });

        sshVtyLineField.addActionListener(e -> {
            overview.append("SSH VTY Line: " + sshVtyLineField.getText() + "\n");
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

        JLabel ipAddressLabel = new JLabel("IP Address + Subnet Mask:");
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
        });

        bannerField.addActionListener(e -> {
            overview.append("Banner: " + bannerField.getText() + "\n");
        });

        enablePasswordField.addActionListener(e -> {
            overview.append("Enable Password: " + enablePasswordField.getText() + "\n");
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
        });

        ipAddressField.addActionListener(e -> {
            overview.append("IP Address + Subnet Mask: " + ipAddressField.getText() + "\n");
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
        });

        accessPasswordField.addActionListener(e -> {
            overview.append("Password: " + accessPasswordField.getText() + "\n");
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
        });

        hsrpInterfaceField.addActionListener(e -> {
            overview.append("HSRP Interface: " + hsrpInterfaceField.getText() + "\n");
        });

        hsrpVersionField.addActionListener(e -> {
            overview.append("HSRP Version: " + hsrpVersionField.getText() + "\n");
        });

        hsrpIpPriorityField.addActionListener(e -> {
            overview.append("HSRP IP Priority: " + hsrpIpPriorityField.getText() + "\n");
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
        });

        excludedAddressEndField.addActionListener(e -> {
            overview.append("DHCP Excluding End Address: " + excludedAddressEndField.getText() + "\n");
        });

        dhcpNameField.addActionListener(e -> {
            overview.append("Pool Name: " + dhcpNameField.getText() + "\n");
        });

        dhcpNetworkField.addActionListener(e -> {
            overview.append("Network: " + dhcpNetworkField.getText() + "\n");
        });

        dhcpDefaultRouterField.addActionListener(e -> {
            overview.append("Default Router: " + dhcpDefaultRouterField.getText() + "\n");
        });

        dhcpDnsField.addActionListener(e -> {
            overview.append("DNS: " + dhcpDnsField.getText() + "\n");
        });

        dhcpHelperInterfaceField.addActionListener(e -> {
            overview.append("DHCP Helper Interface: " + dhcpHelperInterfaceField.getText() + "\n");
        });

        dhcpHelperIpField.addActionListener(e -> {
            overview.append("DHCP Helper IP Address: " + dhcpHelperIpField.getText() + "\n");
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
        });

        ospfRouterIdField.addActionListener(e -> {
            overview.append("OSPF Router ID: " + ospfRouterIdField.getText() + "\n");
        });

        ospfNetworkField.addActionListener(e -> {
            overview.append("OSPF Network: " + ospfNetworkField.getText() + "\n");
        });

        ospfPassiveInterfaceField.addActionListener(e -> {
            overview.append("OSPF Passive Interface: " + ospfPassiveInterfaceField.getText() + "\n");
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
        });

        ripNetworkField.addActionListener(e -> {
            overview.append("RIP Network: " + ripNetworkField.getText() + "\n");
        });

        ripPassiveInterfaceField.addActionListener(e -> {
            overview.append("RIP Passive Interface: " + ripPassiveInterfaceField.getText() + "\n");
        });
    }

}
