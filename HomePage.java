import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame {

    public HomePage() {
        setTitle("Cash Flow Minimizer - Home");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ==== Title ====
        JLabel titleLabel = new JLabel("Cash Flow Minimizer", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // ==== Buttons ====
        JButton enterButton = new JButton("➕ Enter Transactions");
        JButton howItWorksButton = new JButton("📘 How It Works");
        JButton exitButton = new JButton("❌ Exit");

        enterButton.setPreferredSize(new Dimension(200, 40));
        exitButton.setPreferredSize(new Dimension(200, 40));

        // ==== Button Actions ====
        howItWorksButton.addActionListener(e -> {
            dispose();
            new HowItWorksPage().setVisible(true);
        });

        enterButton.addActionListener(e -> {
            dispose(); // close this window
            new CashFlowMinimizerGUI().setVisible(true); // open transaction page
        });

        exitButton.addActionListener(e -> System.exit(0));

        // ==== Layout ====
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        buttonPanel.add(enterButton);
        buttonPanel.add(howItWorksButton);
        buttonPanel.add(exitButton);

        JPanel container = new JPanel(new BorderLayout());
        container.add(titleLabel, BorderLayout.NORTH);
        container.add(buttonPanel, BorderLayout.CENTER);
        container.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        add(container);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomePage().setVisible(true));
    }
}
