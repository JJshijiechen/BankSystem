import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateStatementGUI extends JFrame {
    private JComboBox<String> monthComboBox;
    private JButton generateButton, cancelButton;

    public GenerateStatementGUI() {
        setTitle("Generate Statement");
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

        JLabel monthLabel = new JLabel("Select Month:");
        monthLabel.setBounds(10, 20, 100, 25);
        panel.add(monthLabel);

        String[] months = {"August", "September", "October", "November", "December", "January", "February", "March", "April", "May", "June", "July"};
        monthComboBox = new JComboBox<>(months);
        monthComboBox.setBounds(120, 20, 150, 25);
        panel.add(monthComboBox);

        generateButton = new JButton("Generate");
        generateButton.setBounds(10, 60, 100, 25);
        panel.add(generateButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(170, 60, 100, 25);
        panel.add(cancelButton);

        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedMonth = (String) monthComboBox.getSelectedItem();
                String statement = Database.generateStatement(selectedMonth);
                JOptionPane.showMessageDialog(null, "Statement for " + selectedMonth + ":\n" + statement);
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}

