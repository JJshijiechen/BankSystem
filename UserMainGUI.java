import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class UserMainGUI extends JFrame {
    private JLabel balanceLabel;
    private JButton transferButton, statementButton, depositButton, logoutButton;

    public UserMainGUI() {
        setTitle("Menu");
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

        balanceLabel = new JLabel("Balance: $" + Database.getBalance());
        balanceLabel.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        balanceLabel.setBounds(39, 28, 320, 25);
        panel.add(balanceLabel);

        transferButton = new JButton("Transfer Money");
        transferButton.setBounds(29, 93, 150, 25);
        panel.add(transferButton);

        statementButton = new JButton("Generate Statement");
        statementButton.setBounds(209, 93, 150, 25);
        panel.add(statementButton);

        depositButton = new JButton("Deposit Check");
        depositButton.setBounds(29, 160, 150, 25);
        panel.add(depositButton);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(209, 160, 150, 25);
        panel.add(logoutButton);

        // Adding action listeners for buttons
        transferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TransferMoneyGUI(UserMainGUI.this); // Pass this frame to the transfer money frame
            }
        });

        statementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GenerateStatementGUI();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DepositCheckGUI(UserMainGUI.this); // Pass this frame to the deposit check frame
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginGUI();
                dispose();
            }
        });
    }

    public void refreshBalance() {
        balanceLabel.setText("Balance: $" + Database.getBalance());
    }
}
