package BankSystem;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferMoneyGUI extends JFrame {
    private JTextField recipientEmailField;
    private JTextField amountField;
    private JButton transferButton, cancelButton;
    private UserMainGUI dashboardFrame;

    public TransferMoneyGUI(UserMainGUI dashboardFrame) {
        this.dashboardFrame = dashboardFrame; // Store reference to DashboardFrame

        setTitle("Transfer Money");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel recipientEmailLabel = new JLabel("Recipient Email:");
        recipientEmailLabel.setBounds(10, 20, 120, 25);
        panel.add(recipientEmailLabel);

        recipientEmailField = new JTextField(20);
        recipientEmailField.setBounds(140, 20, 130, 25);
        panel.add(recipientEmailField);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(10, 60, 80, 25);
        panel.add(amountLabel);

        amountField = new JTextField(20);
        amountField.setBounds(140, 60, 130, 25);
        panel.add(amountField);

        transferButton = new JButton("Transfer");
        transferButton.setBounds(10, 100, 100, 25);
        panel.add(transferButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(170, 100, 100, 25);
        panel.add(cancelButton);

        transferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String recipientEmail = recipientEmailField.getText();
                double amount = Double.parseDouble(amountField.getText());

                if (Database.transferMoney(recipientEmail, amount)) {
                    JOptionPane.showMessageDialog(null, "Transfer Successful");
                    dashboardFrame.refreshBalance(); // Refresh balance in the dashboard
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Transfer Failed");
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
