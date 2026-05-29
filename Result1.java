import java.awt.*;
import javax.swing.*;

public class Result1 {
    
    private JFrame frame;
    private int score;
    
    Result1(int score) {
        this.score = score;
        
        frame = new JFrame("Online Examination - Result");
        frame.getContentPane().setBackground(new Color(25, 25, 112)); // Match theme
        frame.setBounds(300, 200, 650, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        // Title
        JLabel titleLabel = new JLabel("📊 EXAMINATION RESULT");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 34));
        titleLabel.setForeground(new Color(255, 215, 0)); // Gold
        titleLabel.setBounds(140, 30, 400, 50);
        frame.add(titleLabel);
        
        // Result Panel
        JPanel resultPanel = new JPanel();
        resultPanel.setBackground(new Color(255, 255, 255, 240));
        resultPanel.setBounds(50, 100, 530, 350);
        resultPanel.setLayout(null);
        resultPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 215, 0), 3));
        frame.add(resultPanel);
        
        // Score Icon
        JLabel scoreIcon = new JLabel("🎯");
        scoreIcon.setFont(new Font("Segoe UI", Font.PLAIN, 50));
        scoreIcon.setBounds(230, 20, 80, 60);
        resultPanel.add(scoreIcon);
        
        // Score Label
        JLabel scoreLabel = new JLabel("Your Score: " + score + " / 10");
        scoreLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
        scoreLabel.setForeground(new Color(25, 25, 112));
        scoreLabel.setBounds(140, 80, 350, 50);
        resultPanel.add(scoreLabel);
        
        // Percentage
        int percentage = (score * 100) / 10;
        JLabel percentageLabel = new JLabel("Percentage: " + percentage + "%");
        percentageLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        percentageLabel.setForeground(new Color(0, 100, 0));
        percentageLabel.setBounds(160, 140, 300, 40);
        resultPanel.add(percentageLabel);
        
        // Result message with emoji
        String message;
        Color msgColor;
        if (score >= 8) {
            message = "🏆 Excellent! You have mastered Java! 🏆";
            msgColor = new Color(0, 100, 0);
        } else if (score >= 6) {
            message = "👍 Good job! Keep practicing! 👍";
            msgColor = new Color(0, 0, 139);
        } else if (score >= 4) {
            message = "📚 Fair attempt! Need more practice. 📚";
            msgColor = new Color(255, 140, 0);
        } else {
            message = "💪 Need improvement! Study harder! 💪";
            msgColor = Color.RED;
        }
        
        JLabel messageLabel = new JLabel(message);
        messageLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        messageLabel.setForeground(msgColor);
        messageLabel.setBounds(80, 200, 450, 40);
        resultPanel.add(messageLabel);
        
        // Separator
        JSeparator separator = new JSeparator();
        separator.setBounds(50, 260, 430, 10);
        resultPanel.add(separator);
        
        // Exit Button
        JButton closeButton = new JButton("✖ EXIT");
        closeButton.setBackground(new Color(200, 0, 0));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        closeButton.setBounds(190, 280, 150, 45);
        closeButton.setFocusPainted(false);
        closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeButton.addActionListener(e -> System.exit(0));
        resultPanel.add(closeButton);
        
        frame.setVisible(true);
    }
}