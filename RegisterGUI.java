package BankSystem;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterGUI extends JFrame {
    private JTextField nameField, emailField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public RegisterGUI() {
        setTitle("Register");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }
    
    private void inputInfo(String name, String email, String password) {
        Database.registerUser(new User(name, email, password));
        JOptionPane.showMessageDialog(null, "Registration Successful");
        new LoginGUI();
        dispose();
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 20, 80, 25);
        panel.add(nameLabel);

        nameField = new JTextField(20);
        nameField.setBounds(100, 20, 165, 25);
        panel.add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 50, 80, 25);
        panel.add(emailLabel);

        emailField = new JTextField(20);
        emailField.setBounds(100, 50, 165, 25);
        panel.add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 80, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 80, 165, 25);
        panel.add(passwordField);

        registerButton = new JButton("Register");
        registerButton.setBounds(10, 120, 120, 25);
        panel.add(registerButton);
        
        // Action listener with refactored code
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                // Call the inputInfo method
                inputInfo(name, email, password);
            }
        });       
    }
}
