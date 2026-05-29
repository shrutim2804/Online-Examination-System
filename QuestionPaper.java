import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class QuestionPaper extends JFrame implements ActionListener {
    
    private JFrame frame;
    private JLabel questionLabel, questionNumberLabel, timerLabel;
    private JRadioButton[] options = new JRadioButton[4];
    private JButton nextButton, markedButton, submitButton;
    private ButtonGroup bg;
    
    private int score = 0;
    private int currentQuestion = 0;
    private int[] userAnswers = new int[10];
    private boolean[] isMarked = new boolean[10];
    
    private JPanel markedPanel;
    
    private String[] questions = {
        "Q1. Which one among these is NOT a primitive datatype?",
        "Q2. Which class is available to all classes automatically?",
        "Q3. Which package is directly available without importing it?",
        "Q4. String class is defined in which package?",
        "Q5. Which one among these is NOT a keyword in Java?",
        "Q6. Which one among these is NOT a class?",
        "Q7. Which one is NOT a function of Object class?",
        "Q8. Which function is NOT present in Applet class?",
        "Q9. Which one is NOT a valid Swing component?",
        "Q10. What is the default value of int variable in Java?"
    };
    
    private String[][] optionsData = {
        {"int", "Float", "boolean", "char"},
        {"Swing", "Applet", "Object", "ActionEvent"},
        {"swing", "applet", "net", "lang"},
        {"lang", "Swing", "Applet", "awt"},
        {"class", "int", "get", "if"},
        {"Swing", "ActionPerformed", "ActionEvent", "Button"},
        {"toString()", "finalize()", "equals()", "getDocumentBase()"},
        {"init()", "main()", "start()", "destroy()"},
        {"JButton", "JList", "JButtonGroup", "JTextArea"},
        {"0", "null", "undefined", "none"}
    };
    
    private int[] correctAnswers = {1, 2, 3, 0, 2, 1, 3, 1, 2, 0};
    
    private Timer timer;
    private int timeLeft = 600;
    
    QuestionPaper() {
        for (int i = 0; i < 10; i++) {
            userAnswers[i] = -1;
        }
        
        frame = new JFrame("Online Examination - Java Quiz");
        frame.setSize(1200, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(25, 25, 112));
        
        // ========== TOP PANEL WITH TIMER ==========
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(25, 25, 112));
        topPanel.setPreferredSize(new Dimension(1200, 80));
        
        // Timer Panel (will be visible for sure)
        JPanel timerPanel = new JPanel();
        timerPanel.setBackground(Color.BLACK);
        timerPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        
        timerLabel = new JLabel("⏰ TIME LEFT: 10:00");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        timerLabel.setForeground(Color.RED);
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timerPanel.add(timerLabel);
        
        topPanel.add(timerPanel);
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
        
        // ========== CENTER PANEL (Question Area) ==========
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1, 2, 10, 10));
        centerPanel.setBackground(new Color(25, 25, 112));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Left Panel - Questions
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 215, 0), 2));
        
        // Question text area
        JPanel questionArea = new JPanel();
        questionArea.setLayout(new BorderLayout());
        questionArea.setBackground(Color.WHITE);
        questionArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        questionNumberLabel = new JLabel("📌 Question 1 of 10");
        questionNumberLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        questionNumberLabel.setForeground(new Color(25, 25, 112));
        questionArea.add(questionNumberLabel, BorderLayout.NORTH);
        
        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        questionLabel.setForeground(new Color(0, 0, 139));
        questionArea.add(questionLabel, BorderLayout.CENTER);
        
        leftPanel.add(questionArea, BorderLayout.NORTH);
        
        // Options Panel
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1, 10, 20));
        optionsPanel.setBackground(Color.WHITE);
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        bg = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setFont(new Font("Tahoma", Font.PLAIN, 18));
            options[i].setBackground(Color.WHITE);
            optionsPanel.add(options[i]);
            bg.add(options[i]);
        }
        
        leftPanel.add(optionsPanel, BorderLayout.CENTER);
        
        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 15));
        buttonPanel.setBackground(Color.WHITE);
        
        nextButton = new JButton("NEXT →");
        nextButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        nextButton.setBackground(new Color(0, 150, 0));
        nextButton.setForeground(Color.WHITE);
        nextButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        nextButton.addActionListener(this);
        buttonPanel.add(nextButton);
        
        markedButton = new JButton("📌 MARK FOR REVIEW");
        markedButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        markedButton.setBackground(new Color(255, 140, 0));
        markedButton.setForeground(Color.WHITE);
        markedButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        markedButton.addActionListener(this);
        buttonPanel.add(markedButton);
        
        submitButton = new JButton("✓ FINISH & SUBMIT");
        submitButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        submitButton.setBackground(new Color(200, 0, 0));
        submitButton.setForeground(Color.WHITE);
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitButton.addActionListener(this);
        buttonPanel.add(submitButton);
        
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Right Panel - Marked Questions
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(255, 255, 255, 230));
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 215, 0), 2));
        
        JLabel markedLabel = new JLabel("  📋 Marked Questions  ");
        markedLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        markedLabel.setForeground(new Color(25, 25, 112));
        markedLabel.setBackground(new Color(255, 228, 196));
        markedLabel.setOpaque(true);
        rightPanel.add(markedLabel, BorderLayout.NORTH);
        
        markedPanel = new JPanel();
        markedPanel.setLayout(new GridLayout(5, 2, 15, 15));
        markedPanel.setBackground(new Color(255, 228, 196));
        markedPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JScrollPane scrollPane = new JScrollPane(markedPanel);
        scrollPane.setBorder(null);
        rightPanel.add(scrollPane, BorderLayout.CENTER);
        
        centerPanel.add(leftPanel);
        centerPanel.add(rightPanel);
        
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        
        loadQuestion();
        startTimer();
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timeLeft > 0) {
                    timeLeft--;
                    int minutes = timeLeft / 60;
                    int seconds = timeLeft % 60;
                    timerLabel.setText("⏰ TIME LEFT: " + String.format("%02d:%02d", minutes, seconds));
                    
                    if (timeLeft <= 120) {
                        timerLabel.setForeground(Color.ORANGE);
                        if (timeLeft <= 60) {
                            timerLabel.setForeground(Color.RED);
                        }
                    }
                    
                    if (timeLeft == 0) {
                        timer.stop();
                        JOptionPane.showMessageDialog(frame, "⏰ Time's Up! Submitting your exam.");
                        submitExam();
                    }
                }
            }
        });
        timer.start();
    }
    
    private void loadQuestion() {
        bg.clearSelection();
        
        for (int i = 0; i < 4; i++) {
            options[i].setSelected(false);
        }
        
        questionNumberLabel.setText("📌 Question " + (currentQuestion + 1) + " of 10");
        questionLabel.setText(questions[currentQuestion]);
        
        String[] currentOptions = optionsData[currentQuestion];
        for (int i = 0; i < 4; i++) {
            options[i].setText((char)(65 + i) + ". " + currentOptions[i]);
        }
        
        if (userAnswers[currentQuestion] != -1) {
            options[userAnswers[currentQuestion]].setSelected(true);
        }
        
        if (currentQuestion == 9) {
            nextButton.setText("✓ SUBMIT");
            nextButton.setBackground(new Color(200, 0, 0));
        } else {
            nextButton.setText("NEXT →");
            nextButton.setBackground(new Color(0, 150, 0));
        }
    }
    
    private void saveCurrentAnswer() {
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected()) {
                userAnswers[currentQuestion] = i;
                return;
            }
        }
    }
    
    private void calculateScore() {
        score = 0;
        for (int i = 0; i < 10; i++) {
            if (userAnswers[i] == correctAnswers[i]) {
                score++;
            }
        }
    }
    
    private void submitExam() {
        if (timer != null) {
            timer.stop();
        }
        saveCurrentAnswer();
        calculateScore();
        frame.setVisible(false);
        new Result1(score);
    }
    
    private void addMarkedButton() {
        if (!isMarked[currentQuestion]) {
            isMarked[currentQuestion] = true;
            JButton btn = new JButton("Go to Q" + (currentQuestion + 1));
            btn.setBackground(new Color(255, 140, 0));
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("Tahoma", Font.BOLD, 12));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    saveCurrentAnswer();
                    currentQuestion = Integer.parseInt(btn.getText().replace("Go to Q", "")) - 1;
                    loadQuestion();
                }
            });
            markedPanel.add(btn);
            markedPanel.revalidate();
            markedPanel.repaint();
            JOptionPane.showMessageDialog(frame, "✓ Question " + (currentQuestion + 1) + " marked for review!");
        } else {
            JOptionPane.showMessageDialog(frame, "ℹ️ This question is already marked!");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            saveCurrentAnswer();
            if (currentQuestion < 9) {
                currentQuestion++;
                loadQuestion();
            } else {
                int confirm = JOptionPane.showConfirmDialog(frame, 
                    "Are you sure you want to submit the exam?", 
                    "Confirm Submission", 
                    JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    submitExam();
                }
            }
        } else if (e.getSource() == markedButton) {
            addMarkedButton();
        } else if (e.getSource() == submitButton) {
            int confirm = JOptionPane.showConfirmDialog(frame, 
                "Are you sure you want to finish and submit the exam?", 
                "Confirm Submission", 
                JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                submitExam();
            }
        }
    }
}