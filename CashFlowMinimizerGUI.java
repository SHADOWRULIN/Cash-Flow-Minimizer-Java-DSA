import javax.swing.*;
import java.awt.*;
import java.util.*;

public class CashFlowMinimizerGUI extends JFrame {
    private DefaultListModel<String> transactionListModel = new DefaultListModel<>();
    private java.util.List<Transaction> transactions = new ArrayList<>();

    private JTextField fromField = new JTextField(10);
    private JTextField toField = new JTextField(10);
    private JTextField amountField = new JTextField(10);
    private JTextArea outputArea = new JTextArea(10, 30);

    public CashFlowMinimizerGUI() {
        setTitle("Cash Flow Minimizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        // ==== INPUT PANEL WITH GRIDBAGLAYOUT ====
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // Padding around each component
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Row 1: Labels and Fields
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("From:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(fromField, gbc);

        gbc.gridx = 2;
        inputPanel.add(new JLabel("To:"), gbc);

        gbc.gridx = 3;
        inputPanel.add(toField, gbc);

        gbc.gridx = 4;
        inputPanel.add(new JLabel("Amount:"), gbc);

        gbc.gridx = 5;
        inputPanel.add(amountField, gbc);

        // Row 2: Buttons
        JButton addButton = new JButton("Add Transaction");
        JButton minimizeButton = new JButton("Minimize Cash Flow");

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        inputPanel.add(addButton, gbc);

        gbc.gridx = 3;
        gbc.gridwidth = 2;
        inputPanel.add(minimizeButton, gbc);

        // ==== TRANSACTION LIST + OUTPUT AREA ====
        JList<String> transactionList = new JList<>(transactionListModel);
        JScrollPane transactionScroll = new JScrollPane(transactionList);
        JScrollPane outputScroll = new JScrollPane(outputArea);
        outputArea.setEditable(false);

        // ==== LAYOUT ====
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(transactionScroll, BorderLayout.CENTER);
        add(outputScroll, BorderLayout.SOUTH);

        // ==== BUTTON ACTIONS ====

        // Add Transaction
        addButton.addActionListener(e -> {
            try {
                String from = fromField.getText().trim();
                String to = toField.getText().trim();
                int amount = Integer.parseInt(amountField.getText().trim());

                if (from.equals("") || to.equals("") || from.equals(to)) {
                    JOptionPane.showMessageDialog(this, "Invalid input!");
                    return;
                }

                transactions.add(new Transaction(from, to, amount));
                transactionListModel.addElement(from + " pays " + amount + " to " + to);

                fromField.setText("");
                toField.setText("");
                amountField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Amount must be a number!");
            }
        });

        // Minimize Cash Flow
        minimizeButton.addActionListener(e -> {
            Map<String, Integer> net = calculateNetAmounts(transactions);
            java.util.List<String> result = minimizeCashFlow(net);
            outputArea.setText("Minimized Transactions:\n");
            for (String s : result) {
                outputArea.append(s + "\n");
            }
        });
    }

    // ==== CORE LOGIC ====

    private Map<String, Integer> calculateNetAmounts(java.util.List<Transaction> transactions) {
        Map<String, Integer> netAmounts = new HashMap<>();
        for (Transaction t : transactions) {
            netAmounts.put(t.from, netAmounts.getOrDefault(t.from, 0) - t.amount);
            netAmounts.put(t.to, netAmounts.getOrDefault(t.to, 0) + t.amount);
        }
        return netAmounts;
    }

    private java.util.List<String> minimizeCashFlow(Map<String, Integer> netAmounts) {
        PriorityQueue<Person> creditors = new PriorityQueue<>((a, b) -> b.amount - a.amount);
        PriorityQueue<Person> debtors = new PriorityQueue<>(Comparator.comparingInt(a -> a.amount));
        java.util.List<String> result = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : netAmounts.entrySet()) {
            int amt = entry.getValue();
            if (amt > 0)
                creditors.add(new Person(entry.getKey(), amt));
            else if (amt < 0)
                debtors.add(new Person(entry.getKey(), amt));
        }

        while (!creditors.isEmpty() && !debtors.isEmpty()) {
            Person cr = creditors.poll();
            Person db = debtors.poll();
            int settle = Math.min(cr.amount, -db.amount);
            result.add(db.name + " pays " + settle + " to " + cr.name);

            int newCrAmt = cr.amount - settle;
            int newDbAmt = db.amount + settle;
            if (newCrAmt > 0) creditors.add(new Person(cr.name, newCrAmt));
            if (newDbAmt < 0) debtors.add(new Person(db.name, newDbAmt));
        }
        return result;
    }

    // ==== DATA CLASSES ====

    static class Transaction {
        String from, to;
        int amount;
        public Transaction(String from, String to, int amount) {
            this.from = from;
            this.to = to;
            this.amount = amount;
        }
    }

    static class Person {
        String name;
        int amount;
        public Person(String name, int amount) {
            this.name = name;
            this.amount = amount;
        }
    }

    // ==== MAIN ====
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CashFlowMinimizerGUI().setVisible(true));
    }
}
