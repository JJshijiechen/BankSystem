import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.SystemColor;

public class AdminLoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, backButton;

    public AdminLoginGUI() {
        setTitle("Admin Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        placeComponents(panel);
        
        JLabel Notice = new JLabel("Default Account:");
        Notice.setBounds(10, 138, 119, 16);
        panel.add(Notice);
        
        JTextArea AdminInfo = new JTextArea();
        AdminInfo.setBackground(SystemColor.window);
        AdminInfo.setText("Username: admin \nPassword: admin123");
        AdminInfo.setBounds(132, 127, 133, 39);
        panel.add(AdminInfo);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(100, 20, 165, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 165, 25);
        panel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(10, 90, 80, 25);
        panel.add(loginButton);

        backButton = new JButton("Back");
        backButton.setBounds(164, 87, 100, 25);
        panel.add(backButton);
        
        
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (Database.adminLogin_clk(username, password)) {
                    new AdminMainGUI();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginSelectionGUI();
                dispose();
            }
        });
    }
}
