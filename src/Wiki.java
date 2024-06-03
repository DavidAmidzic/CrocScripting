import javax.swing.*;
import java.awt.*;


public class Wiki extends JFrame {

    private JPanel mainPanel;

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

        // Add categories and commands to the main panel
        addCategory("Basic Configuration", new String[]{
                "hostname <name> - Sets the hostname for the router.",
                "banner motd #<message># - Sets the Message of the Day banner.",
                "no ip domain-lookup - Disables DNS lookup to prevent the router from attempting to translate unknown commands as hostnames.",
                "enable secret <password> - Sets the encrypted password for privileged (enable) mode access.",
                "service password-encryption - Encrypts all passwords in the configuration file.",
                "interface <type/number> - Enters the interface configuration mode.",
                "ip address <address> <subnet mask> - Assigns an IP address to an interface."
        });

        addCategory("Access Configuration", new String[]{
                "line <line> <number> - Defines the access-line (vty 0 15, console 0)",
                "password <password> - sets a password for that access line",
                "login - Enables a login-systme."
        });

        addCategory("DHCP Configuration", new String[]{
                "ip dhcp excluded-address <start-ip> <end-ip> - Excludes IP addresses from being assigned by DHCP.",
                "ip dhcp pool <name> - Creates a DHCP pool with the specified name.",
                "network <network-address> <subnet-mask> - Defines the network and subnet mask for the DHCP pool.",
                "default-router <ip-address> - Specifies the default gateway for the DHCP clients.",
                "dns-server <ip-address> - Specifies the DNS server for the DHCP clients."
        });

        addCategory("OSPF Configuration", new String[]{
                "router ospf <process-id> - Enables OSPF routing process.",
                "router-id <router-id> - Locally significant id.",
                "network <network> <wildcard-mask> area <area-id> - Specifies the network to be advertised in OSPF.",
                "passive-interface <interface> - Prevents OSPF updates from being sent through an interface."
        });

        addCategory("RIP Configuration", new String[]{
                "router rip - Enables RIP routing process.",
                "version 2 - Sets RIP version to 2.",
                "network <network> - Advertises the specified network in RIP.",
                "passive-interface <interface> - Prevents OSPF updates from being sent through an interface."
        });

        addCategory("HSRP Configuration", new String[]{
                "standby <group> ip <virtual-ip> - Configures the virtual IP address for the HSRP group.",
                "standby <group> priority <priority> - Sets the priority for the router in the HSRP group.",
                "standby <group> preempt - Enables preemption for the HSRP group.",
                "standby <group> authentication <string> - Sets the authentication string for the HSRP group."
        });

        addCategory("SSH Configuration", new String[]{
                "ip domain-name <domain> - Sets the domain name for the router.",
                "crypto key generate rsa - Generates the RSA keys for SSH.",
                "ip ssh version 2 - Enables SSH version 2.",
                "line vty 0 4 - Enters the line configuration mode for VTY lines.",
                "transport input ssh - Restricts VTY access to SSH."
        });

        addCategory("NAT Configuration", new String[]{
                "ip nat inside source static <private-ip> <public-ip> - Configures static NAT.",
                "ip nat pool <name> <start-ip> <end-ip> netmask <netmask> - Defines a pool of public IP addresses for dynamic NAT.",
                "ip nat inside source list <access-list> pool <name> - Maps an access list to the NAT pool.",
                "access-list <number> permit <source> <wildcard> - Creates an access list to permit the source addresses."
        });

        // Add scroll pane to the frame
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void addCategory(String categoryName, String[] commands) {
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
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        categoryPanel.add(categoryCheckBox);
        categoryPanel.add(commandsPanel);

        mainPanel.add(categoryPanel);
    }

}
