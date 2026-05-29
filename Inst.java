import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Inst extends JFrame implements ActionListener {
    
    private JFrame frame;
    private JButton startButton, backButton;
    
    Inst() {
        frame = new JFrame("Online Examination - Instructions");
        frame.getContentPane().setBackground(new Color(25, 25, 112)); // Match login page
        frame.setBounds(100, 100, 1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        // Title
        JLabel heading = new JLabel("📋 EXAMINATION INSTRUCTIONS");
        heading.setFont(new Font("Times New Roman", Font.BOLD, 32));
        heading.setBounds(250, 20, 550, 50);
        heading.setForeground(new Color(255, 215, 0)); // Gold
        frame.getContentPane().add(heading);
        
        // Main Panel
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255, 240));
        panel.setBounds(30, 90, 920, 520);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createLineBorder(new Color(255, 215, 0), 2));
        frame.getContentPane().add(panel);
        
        // Sub heading
        JLabel subHeading = new JLabel("⚠️ Please read the instructions carefully before starting the examination.");
        subHeading.setForeground(Color.RED);
        subHeading.setFont(new Font("Tahoma", Font.BOLD, 16));
        subHeading.setBounds(30, 20, 700, 30);
        panel.add(subHeading);
        
        // Rules with better formatting
        String rulesText = "<html>" +
            "<h2 style='color:#191970;'>📌 Important Instructions:</h2>" +
            "<br>🔹 <b>Total Questions:</b> 10 Questions" +
            "<br><br>🔹 <b>Marks:</b> Each question carries 1 mark" +
            "<br><br>🔹 <b>No Negative Marking</b> - Attempt all questions" +
            "<br><br>🔹 <b>Timer:</b> You have 10 minutes to complete the exam" +
            "<br><br>🔹 <b>Mark for Review:</b> Use 'MARK FOR REVIEW' button to flag questions" +
            "<br><br>🔹 <b>Navigation:</b> Use 'NEXT' button to move between questions" +
            "<br><br>🔹 <b>Submission:</b> Click 'SUBMIT' after completing all questions" +
            "<br><br>🔹 <b>Result:</b> Score will be displayed immediately after submission" +
            "<br><br><br><h3 style='color:#006400;'>🎯 GOOD LUCK! 🎯</h3>" +
            "</html>";
        
        JLabel rules = new JLabel(rulesText);
        rules.setFont(new Font("Tahoma", Font.PLAIN, 15));
        rules.setBounds(30, 60, 850, 380);
        panel.add(rules);
        
        // Back Button
        backButton = new JButton("◀ BACK");
        backButton.setBackground(new Color(100, 100, 100));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        backButton.setBounds(200, 460, 150, 40);
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(this);
        panel.add(backButton);
        
        // Start Button
        startButton = new JButton("START EXAM ▶");
        startButton.setBackground(new Color(0, 150, 0));
        startButton.setForeground(Color.WHITE);
        startButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        startButton.setBounds(550, 460, 180, 40);
        startButton.setFocusPainted(false);
        startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        startButton.addActionListener(this);
        panel.add(startButton);
        
        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            frame.setVisible(false);
            new QuestionPaper();
        } else {
            frame.setVisible(false);
            new Login();
        }
    }
}