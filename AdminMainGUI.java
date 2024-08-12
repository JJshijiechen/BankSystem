import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class AdminMainGUI extends JFrame {
    private JList<String> userList;
    private JButton deleteButton, logoutButton;
    private DefaultListModel<String> listModel;

    public AdminMainGUI() {
        setTitle("Admin Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Users:");
        userLabel.setBounds(57, 30, 80, 25);
        panel.add(userLabel);

        listModel = new DefaultListModel<>();
        for (String email : Database.getUsers().keySet()) {
            listModel.addElement(email);
        }

        userList = new JList<>(listModel);
        userList.setBounds(156, 24, 165, 100);
        panel.add(userList);

        deleteButton = new JButton("Delete User");
        deleteButton.setBounds(36, 161, 120, 25);
        panel.add(deleteButton);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(235, 161, 100, 25);
        panel.add(logoutButton);

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedUser = userList.getSelectedValue();
                if (selectedUser != null) {
                    Database.deleteUser(selectedUser);
                    listModel.removeElement(selectedUser);
                    JOptionPane.showMessageDialog(null, "User deleted.");
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a user to delete.");
                }
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AdminLoginGUI();
                dispose();
            }
        });
    }
}
