import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepositCheckGUI extends JFrame {
    private JTextField checkNumberField;
    private JTextField amountField;
    private JButton depositButton, cancelButton;
    private UserMainGUI dashboardFrame;

    public DepositCheckGUI(UserMainGUI dashboardFrame) {
        this.dashboardFrame = dashboardFrame; // Store reference to DashboardFrame

        setTitle("Deposit Check");
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

        JLabel checkNumberLabel = new JLabel("Check Number:");
        checkNumberLabel.setBounds(10, 20, 100, 25);
        panel.add(checkNumberLabel);

        checkNumberField = new JTextField(20);
        checkNumberField.setBounds(140, 20, 130, 25);
        panel.add(checkNumberField);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(10, 60, 80, 25);
        panel.add(amountLabel);

        amountField = new JTextField(20);
        amountField.setBounds(140, 60, 130, 25);
        panel.add(amountField);

        depositButton = new JButton("Deposit");
        depositButton.setBounds(10, 100, 100, 25);
        panel.add(depositButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(170, 100, 100, 25);
        panel.add(cancelButton);

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String checkNumber = checkNumberField.getText();
                double amount = Double.parseDouble(amountField.getText());

                if (Database.depositCheck(checkNumber, amount)) {
                    JOptionPane.showMessageDialog(null, "Check Deposited Successfully");
                    dashboardFrame.refreshBalance(); // Refresh balance in the dashboard
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Check Deposit Failed");
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
