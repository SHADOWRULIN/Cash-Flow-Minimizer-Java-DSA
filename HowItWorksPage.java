import javax.swing.*;
import java.awt.*;

public class HowItWorksPage extends JFrame {

    public HowItWorksPage() {
        setTitle("How It Works - Cash Flow Minimizer");
        setSize(500, 400);
        setLocationRelativeTo(null);

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setText(
            "🔍 What is Cash Flow Minimizer?\n" +
            "It’s a tool that helps groups of people settle debts with as few transactions as possible.\n\n" +
            "📊 Why Minimize Transactions?\n" +
            "- Fewer transactions mean less confusion and less money transfer overhead.\n\n" +
            "🧠 How Does It Work?\n" +
            "- Each person is assigned a balance (who owes or is owed).\n" +
            "- We use a Greedy Algorithm to pair the biggest debtor with the biggest creditor.\n" +
            "- This continues until everyone is settled.\n\n" +
            "✅ Result: All debts are paid with the minimum number of transactions!"
        );

        JScrollPane scrollPane = new JScrollPane(infoArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JButton backButton = new JButton("Back");
        
        backButton.addActionListener(e -> {
            new HomePage().setVisible(true);
            dispose(); 
        });

        setLayout(new BorderLayout()); 
        add(scrollPane, BorderLayout.CENTER); 
        add(backButton, BorderLayout.SOUTH);
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new HowItWorksPage().setVisible(true));
    }
}
