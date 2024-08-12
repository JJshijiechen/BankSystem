import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

class LoginSelectionGUI extends JFrame {
    private JButton userLoginButton, adminLoginButton;

    public LoginSelectionGUI() {
        setTitle("Select Login Type");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        userLoginButton = new JButton("User Login");
        userLoginButton.setBounds(50, 50, 100, 50);
        panel.add(userLoginButton);

        adminLoginButton = new JButton("Admin Login");
        adminLoginButton.setBounds(160, 50, 100, 50);
        panel.add(adminLoginButton);

        userLoginButton.addActionListener(e -> {
            new LoginGUI();
            dispose();
        });

        adminLoginButton.addActionListener(e -> {
            new AdminLoginGUI();
            dispose();
        });
    }
}

