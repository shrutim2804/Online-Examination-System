import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.EmptyBorder;

public class QuestionPaper extends JFrame implements ActionListener {

    private JFrame frame;
    private JLabel questionLabel, questionNumberLabel, timerLabel, progressLabel;
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

    // ---- Theme colors (matches Login page) ----
    private final Color GRADIENT_TOP    = new Color(20, 24, 60);
    private final Color GRADIENT_BOTTOM = new Color(76, 29, 110);
    private final Color ACCENT          = new Color(124, 92, 255);
    private final Color ACCENT_HOVER    = new Color(102, 70, 230);
    private final Color GREEN           = new Color(46, 196, 121);
    private final Color GREEN_HOVER     = new Color(36, 170, 105);
    private final Color ORANGE          = new Color(255, 159, 67);
    private final Color ORANGE_HOVER    = new Color(235, 140, 50);
    private final Color RED             = new Color(235, 77, 92);
    private final Color RED_HOVER       = new Color(212, 60, 75);
    private final Color CARD_BG         = new Color(255, 255, 255, 248);
    private final Color TEXT_DARK       = new Color(35, 35, 60);
    private final Color TEXT_MUTED      = new Color(120, 120, 140);

    QuestionPaper() {
        for (int i = 0; i < 10; i++) {
            userAnswers[i] = -1;
        }

        frame = new JFrame("Online Examination - Java Quiz");
        frame.setSize(1200, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GradientPanel root = new GradientPanel();
        root.setLayout(new BorderLayout());
        frame.setContentPane(root);

        // ========== TOP PANEL WITH TIMER ==========
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setPreferredSize(new Dimension(1200, 90));
        topPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("  Java Programming Quiz");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        topPanel.add(titleLabel, BorderLayout.WEST);

        RoundedPanel timerCard = new RoundedPanel(18, new Color(0, 0, 0, 130));
        timerCard.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        timerCard.setPreferredSize(new Dimension(220, 60));

        timerLabel = new JLabel("⏰  10:00");
        timerLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        timerLabel.setForeground(new Color(255, 99, 110));
        timerCard.add(timerLabel);

        JPanel timerWrap = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 15));
        timerWrap.setOpaque(false);
        timerWrap.add(timerCard);
        topPanel.add(timerWrap, BorderLayout.EAST);

        topPanel.setBorder(new EmptyBorder(0, 20, 0, 0));
        root.add(topPanel, BorderLayout.NORTH);

        // ========== CENTER PANEL (Question Area) ==========
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 18, 18));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(new EmptyBorder(0, 20, 20, 20));

        // ---- Left Panel - Questions ----
        RoundedPanel leftPanel = new RoundedPanel(22, CARD_BG);
        leftPanel.setLayout(new BorderLayout());

        // Question text area
        JPanel questionArea = new JPanel(new BorderLayout(0, 12));
        questionArea.setOpaque(false);
        questionArea.setBorder(new EmptyBorder(28, 32, 16, 32));

        JPanel headerRow = new JPanel(new BorderLayout());
        headerRow.setOpaque(false);

        questionNumberLabel = new JLabel("Question 1 of 10");
        questionNumberLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        questionNumberLabel.setForeground(ACCENT);
        headerRow.add(questionNumberLabel, BorderLayout.WEST);

        progressLabel = new JLabel("10% complete");
        progressLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        progressLabel.setForeground(TEXT_MUTED);
        headerRow.add(progressLabel, BorderLayout.EAST);

        questionArea.add(headerRow, BorderLayout.NORTH);

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Segoe UI", Font.BOLD, 21));
        questionLabel.setForeground(TEXT_DARK);
        questionArea.add(questionLabel, BorderLayout.CENTER);

        leftPanel.add(questionArea, BorderLayout.NORTH);

        // Options Panel
        JPanel optionsPanel = new JPanel(new GridLayout(4, 1, 0, 14));
        optionsPanel.setOpaque(false);
        optionsPanel.setBorder(new EmptyBorder(10, 32, 10, 32));

        bg = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setFont(new Font("Segoe UI", Font.PLAIN, 16));
            options[i].setForeground(TEXT_DARK);
            options[i].setOpaque(false);
            options[i].setFocusPainted(false);
            options[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            optionsPanel.add(wrapOption(options[i]));
            bg.add(options[i]);
        }

        leftPanel.add(optionsPanel, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 18, 15));
        buttonPanel.setOpaque(false);

        nextButton = new RoundedButton("NEXT →", 14, GREEN, GREEN_HOVER);
        nextButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        nextButton.setForeground(Color.WHITE);
        nextButton.setPreferredSize(new Dimension(150, 44));
        nextButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        nextButton.addActionListener(this);
        buttonPanel.add(nextButton);

        markedButton = new RoundedButton("📌 MARK FOR REVIEW", 14, ORANGE, ORANGE_HOVER);
        markedButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        markedButton.setForeground(Color.WHITE);
        markedButton.setPreferredSize(new Dimension(210, 44));
        markedButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        markedButton.addActionListener(this);
        buttonPanel.add(markedButton);

        submitButton = new RoundedButton("✓ FINISH & SUBMIT", 14, RED, RED_HOVER);
        submitButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        submitButton.setForeground(Color.WHITE);
        submitButton.setPreferredSize(new Dimension(190, 44));
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitButton.addActionListener(this);
        buttonPanel.add(submitButton);

        leftPanel.add(buttonPanel, BorderLayout.SOUTH);

        // ---- Right Panel - Marked Questions ----
        RoundedPanel rightPanel = new RoundedPanel(22, CARD_BG);
        rightPanel.setLayout(new BorderLayout());

        JLabel markedLabel = new JLabel("📋  Marked Questions");
        markedLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
        markedLabel.setForeground(TEXT_DARK);
        markedLabel.setBorder(new EmptyBorder(22, 24, 12, 24));
        rightPanel.add(markedLabel, BorderLayout.NORTH);

        markedPanel = new JPanel();
        markedPanel.setLayout(new GridLayout(0, 2, 12, 12));
        markedPanel.setOpaque(false);
        markedPanel.setBorder(new EmptyBorder(0, 24, 24, 24));

        JPanel markedWrap = new JPanel(new BorderLayout());
        markedWrap.setOpaque(false);
        markedWrap.add(markedPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(markedWrap);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        centerPanel.add(leftPanel);
        centerPanel.add(rightPanel);

        root.add(centerPanel, BorderLayout.CENTER);

        loadQuestion();
        startTimer();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /** Wraps a radio button in a rounded selectable-looking row for a card feel */
    private JPanel wrapOption(JRadioButton rb) {
        JPanel row = new RoundedPanel(12, new Color(244, 245, 250));
        row.setLayout(new BorderLayout());
        rb.setBorder(new EmptyBorder(12, 16, 12, 16));
        row.add(rb, BorderLayout.CENTER);
        row.setPreferredSize(new Dimension(10, 50));
        return row;
    }

    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timeLeft > 0) {
                    timeLeft--;
                    int minutes = timeLeft / 60;
                    int seconds = timeLeft % 60;
                    timerLabel.setText("⏰  " + String.format("%02d:%02d", minutes, seconds));

                    if (timeLeft <= 120) {
                        timerLabel.setForeground(ORANGE);
                        if (timeLeft <= 60) {
                            timerLabel.setForeground(new Color(255, 99, 110));
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

        questionNumberLabel.setText("Question " + (currentQuestion + 1) + " of 10");
        progressLabel.setText(((currentQuestion + 1) * 10) + "% complete");
        questionLabel.setText(questions[currentQuestion]);

        String[] currentOptions = optionsData[currentQuestion];
        for (int i = 0; i < 4; i++) {
            options[i].setText((char) (65 + i) + ".  " + currentOptions[i]);
        }

        if (userAnswers[currentQuestion] != -1) {
            options[userAnswers[currentQuestion]].setSelected(true);
        }

        if (currentQuestion == 9) {
            nextButton.setText("✓ SUBMIT");
        } else {
            nextButton.setText("NEXT →");
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
            RoundedButton btn = new RoundedButton("Q" + (currentQuestion + 1), 10, ORANGE, ORANGE_HOVER);
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
            btn.setPreferredSize(new Dimension(70, 40));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    saveCurrentAnswer();
                    currentQuestion = Integer.parseInt(btn.getText().replace("Q", "")) - 1;
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

    // ---------- Custom gradient background panel ----------
    private class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            GradientPaint gp = new GradientPaint(0, 0, GRADIENT_TOP, getWidth(), getHeight(), GRADIENT_BOTTOM);
            g2.setPaint(gp);
            g2.fillRect(0, 0, getWidth(), getHeight());

            g2.setColor(new Color(255, 255, 255, 12));
            g2.fillOval(-120, -120, 320, 320);
            g2.fillOval(getWidth() - 220, getHeight() - 220, 380, 380);
        }
    }

    // ---------- Rounded card panel with soft shadow ----------
    private class RoundedPanel extends JPanel {
        private final int radius;
        private final Color bg;

        RoundedPanel(int radius, Color bg) {
            this.radius = radius;
            this.bg = bg;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(new Color(0, 0, 0, 50));
            g2.fill(new RoundRectangle2D.Double(4, 6, getWidth() - 4, getHeight() - 4, radius, radius));

            g2.setColor(bg);
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 6, getHeight() - 6, radius, radius));
            g2.dispose();
            super.paintComponent(g);
        }
    }

    // ---------- Rounded hover-aware button ----------
    private class RoundedButton extends JButton {
        private final int radius;
        private final Color base;
        private final Color hover;
        private boolean hovering = false;

        RoundedButton(String text, int radius, Color base, Color hover) {
            super(text);
            this.radius = radius;
            this.base = base;
            this.hover = hover;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setOpaque(false);
            setHorizontalAlignment(SwingConstants.CENTER);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) { hovering = true; repaint(); }
                @Override
                public void mouseExited(MouseEvent e) { hovering = false; repaint(); }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(hovering ? hover : base);
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), radius, radius));
            g2.dispose();
            super.paintComponent(g);
        }
    }
}
