import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Wiki extends JFrame {

    private JPanel mainPanel;
    private JCheckBox routerCheckBox;
    private JCheckBox switchCheckBox;

    public Wiki() {
        setTitle("Wiki - Configuration Commands");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Scroll pane for main panel
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Add Router checkbox
        routerCheckBox = new JCheckBox("Router");
        routerCheckBox.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Panel for router options
        JPanel routerPanel = new JPanel();
        routerPanel.setLayout(new BoxLayout(routerPanel, BoxLayout.Y_AXIS));
        routerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        routerPanel.setVisible(false);

        // Add categories and commands to the router panel
        addCategory(routerPanel, "Basic Configuration", new String[]{
                "hostname <name> - Sets the hostname for the router.",
                "banner motd #<message># - Sets the Message of the Day banner.",
                "no ip domain-lookup - Disables DNS lookup to prevent the router from attempting to translate unknown commands as hostnames.",
                "enable secret <password> - Sets the encrypted password for privileged (enable) mode access.",
                "service password-encryption - Encrypts all passwords in the configuration file.",
                "interface <type/number> - Enters the interface configuration mode.",
                "ip address <address> <subnet mask> - Assigns an IP address to an interface."
        });

        addCategory(routerPanel, "DHCP Configuration", new String[]{
                "ip dhcp excluded-address <start-ip> <end-ip> - Excludes IP addresses from being assigned by DHCP.",
                "ip dhcp pool <name> - Creates a DHCP pool with the specified name.",
                "network <network-address> <subnet-mask> - Defines the network and subnet mask for the DHCP pool.",
                "default-router <ip-address> - Specifies the default gateway for the DHCP clients.",
                "dns-server <ip-address> - Specifies the DNS server for the DHCP clients.",
                "lease <days> <hours> <minutes> - Sets the lease duration for the DHCP addresses."
        });

        addCategory(routerPanel, "Access Control Lists (ACL)", new String[]{
                "access-list <number> permit <source> <wildcard> - Allows traffic from the specified source.",
                "access-list <number> deny <source> <wildcard> - Blocks traffic from the specified source.",
                "ip access-group <number> in - Applies the access list to incoming traffic on an interface.",
                "ip access-group <number> out - Applies the access list to outgoing traffic on an interface."
        });

        addCategory(routerPanel, "OSPF Configuration", new String[]{
                "router ospf <process-id> - Enables OSPF routing process.",
                "network <network> <wildcard-mask> area <area-id> - Specifies the network to be advertised in OSPF.",
                "passive-interface <interface> - Prevents OSPF updates from being sent through an interface.",
                "default-information originate - Advertises a default route to OSPF neighbors."
        });

        addCategory(routerPanel, "RIP Configuration", new String[]{
                "router rip - Enables RIP routing process.",
                "version 2 - Sets RIP version to 2.",
                "network <network> - Advertises the specified network in RIP.",
                "no auto-summary - Disables automatic summarization of networks in RIP."
        });

        addCategory(routerPanel, "HSRP Configuration", new String[]{
                "standby <group> ip <virtual-ip> - Configures the virtual IP address for the HSRP group.",
                "standby <group> priority <priority> - Sets the priority for the router in the HSRP group.",
                "standby <group> preempt - Enables preemption for the HSRP group.",
                "standby <group> authentication <string> - Sets the authentication string for the HSRP group."
        });

        addCategory(routerPanel, "SSH Configuration", new String[]{
                "ip domain-name <domain> - Sets the domain name for the router.",
                "crypto key generate rsa - Generates the RSA keys for SSH.",
                "ip ssh version 2 - Enables SSH version 2.",
                "line vty 0 4 - Enters the line configuration mode for VTY lines.",
                "transport input ssh - Restricts VTY access to SSH."
        });

        addCategory(routerPanel, "NAT Configuration", new String[]{
                "ip nat inside source static <private-ip> <public-ip> - Configures static NAT.",
                "ip nat pool <name> <start-ip> <end-ip> netmask <netmask> - Defines a pool of public IP addresses for dynamic NAT.",
                "ip nat inside source list <access-list> pool <name> - Maps an access list to the NAT pool.",
                "access-list <number> permit <source> <wildcard> - Creates an access list to permit the source addresses."
        });

        // Add Router checkbox and router panel to the main panel
        mainPanel.add(routerCheckBox);
        mainPanel.add(routerPanel);

        // Add Switch checkbox
        switchCheckBox = new JCheckBox("Switch");
        switchCheckBox.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Panel for switch options
        JPanel switchPanel = new JPanel();
        switchPanel.setLayout(new BoxLayout(switchPanel, BoxLayout.Y_AXIS));
        switchPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        switchPanel.setVisible(false);

        // Add categories and commands to the switch panel
        addCategory(switchPanel, "Basic Configuration", new String[]{
                "hostname <name> - Sets the hostname for the switch.",
                "banner motd #<message># - Sets the Message of the Day banner.",
                "no ip domain-lookup - Disables DNS lookup to prevent the switch from attempting to translate unknown commands as hostnames.",
                "enable secret <password> - Sets the encrypted password for privileged (enable) mode access.",
                "service password-encryption - Encrypts all passwords in the configuration file.",
                "interface <type/number> - Enters the interface configuration mode.",
                "ip address <address> <subnet mask> - Assigns an IP address to an interface."
        });

        addCategory(switchPanel, "VLAN Configuration", new String[]{
                "vlan <number> - Creates a VLAN with the specified number.",
                "name <name> - Assigns a name to the VLAN.",
                "interface vlan <number> - Enters the VLAN interface configuration mode.",
                "ip address <address> <subnet mask> - Assigns an IP address to a VLAN interface."
        });

        addCategory(switchPanel, "STP Configuration", new String[]{
                "spanning-tree mode <pvst|rapid-pvst|mst> - Sets the spanning tree protocol mode.",
                "spanning-tree vlan <vlan-id> priority <priority> - Sets the STP priority for a VLAN.",
                "spanning-tree vlan <vlan-id> root primary - Sets the switch as the primary root for a VLAN.",
                "spanning-tree vlan <vlan-id> root secondary - Sets the switch as the secondary root for a VLAN."
        });

        addCategory(switchPanel, "EtherChannel Configuration", new String[]{
                "interface <type> <number> - Enters the interface configuration mode.",
                "channel-group <number> mode <active|passive|desirable|auto> - Configures the channel group number and mode."
        });

        addCategory(switchPanel, "SSH Configuration", new String[]{
                "ip domain-name <domain> - Sets the domain name for the switch.",
                "crypto key generate rsa - Generates the RSA keys for SSH.",
                "ip ssh version 2 - Enables SSH version 2.",
                "line vty 0 4 - Enters the line configuration mode for VTY lines.",
                "transport input ssh - Restricts VTY access to SSH."
        });

        addCategory(switchPanel, "ACL Configuration", new String[]{
                "access-list <number> permit <source> <wildcard> - Allows traffic from the specified source.",
                "access-list <number> deny <source> <wildcard> - Blocks traffic from the specified source.",
                "ip access-group <number> in - Applies the access list to incoming traffic on an interface.",
                "ip access-group <number> out - Applies the access list to outgoing traffic on an interface."
        });

        // Add Switch checkbox and switch panel to the main panel
        mainPanel.add(switchCheckBox);
        mainPanel.add(switchPanel);

        // Add scroll pane to the frame
        add(scrollPane, BorderLayout.CENTER);

        routerCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                routerPanel.setVisible(routerCheckBox.isSelected());
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        switchCheckBox.addActionListener(e -> {
            switchPanel.setVisible(switchCheckBox.isSelected());
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        setVisible(true);
    }

    private void addCategory(JPanel parentPanel, String categoryName, String[] commands) {
        JPanel categoryPanel = new JPanel();
        categoryPanel.setLayout(new BoxLayout(categoryPanel, BoxLayout.Y_AXIS));
        categoryPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JCheckBox categoryCheckBox = new JCheckBox(categoryName);
        categoryCheckBox.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel commandsPanel = new JPanel();
        commandsPanel.setLayout(new BoxLayout(commandsPanel, BoxLayout.Y_AXIS));
        commandsPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        commandsPanel.setVisible(false);

        for (String command : commands) {
            JLabel commandLabel = new JLabel(command);
            commandLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            commandLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Adding space between lines
            commandsPanel.add(commandLabel);
        }

        categoryCheckBox.addActionListener(e -> {
            commandsPanel.setVisible(categoryCheckBox.isSelected());
            parentPanel.revalidate();
            parentPanel.repaint();
        });

        categoryPanel.add(categoryCheckBox);
        categoryPanel.add(commandsPanel);

        parentPanel.add(categoryPanel);
    }
}
