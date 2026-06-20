import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.EmptyBorder;

public class Inst extends JFrame implements ActionListener {

    private JFrame frame;
    private JButton startButton, backButton;

    // ---- Theme colors (matches Login, QuestionPaper, Result1) ----
    private final Color GRADIENT_TOP    = new Color(20, 24, 60);
    private final Color GRADIENT_BOTTOM = new Color(76, 29, 110);
    private final Color ACCENT          = new Color(124, 92, 255);
    private final Color GREEN           = new Color(46, 196, 121);
    private final Color GREEN_HOVER     = new Color(36, 170, 105);
    private final Color GRAY            = new Color(120, 120, 140);
    private final Color GRAY_HOVER      = new Color(100, 100, 120);
    private final Color RED_TEXT        = new Color(235, 77, 92);
    private final Color CARD_BG         = new Color(255, 255, 255, 248);
    private final Color ROW_BG          = new Color(244, 245, 250);
    private final Color TEXT_DARK       = new Color(35, 35, 60);

    private final String[] rules = {
        "Total Questions: 10 Questions",
        "Marks: Each question carries 1 mark",
        "No Negative Marking — attempt all questions",
        "Timer: You have 10 minutes to complete the exam",
        "Mark for Review: Use 'MARK FOR REVIEW' to flag questions",
        "Navigation: Use 'NEXT' to move between questions",
        "Submission: Click 'SUBMIT' after completing all questions",
        "Result: Your score is shown immediately after submission"
    };

    Inst() {
        frame = new JFrame("Online Examination - Instructions");
        frame.setSize(1000, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(700, 500)); // keeps footer/buttons from being squashed

        GradientPanel background = new GradientPanel();
        background.setLayout(new BorderLayout());
        frame.setContentPane(background);

        // ===== Header =====
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(new EmptyBorder(30, 20, 15, 20));

        JLabel heading = new JLabel("Examination Instructions", SwingConstants.CENTER);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 30));
        heading.setForeground(Color.WHITE);
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(heading);

        JLabel subHeading = new JLabel("Please read carefully before starting the examination", SwingConstants.CENTER);
        subHeading.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subHeading.setForeground(new Color(220, 215, 255));
        subHeading.setAlignmentX(Component.CENTER_ALIGNMENT);
        subHeading.setBorder(new EmptyBorder(6, 0, 0, 0));
        headerPanel.add(subHeading);

        background.add(headerPanel, BorderLayout.NORTH);

        // ===== Center: Card containing scrollable rules + fixed footer =====
        JPanel centerWrap = new JPanel(new BorderLayout());
        centerWrap.setOpaque(false);
        centerWrap.setBorder(new EmptyBorder(0, 60, 30, 60));

        RoundedPanel card = new RoundedPanel(26, CARD_BG);
        card.setLayout(new BorderLayout());
        centerWrap.add(card, BorderLayout.CENTER);
        background.add(centerWrap, BorderLayout.CENTER);

        // --- Card content: vertical box (warning + rules + good luck) inside scroll pane ---
        JPanel cardContent = new JPanel();
        cardContent.setOpaque(false);
        cardContent.setLayout(new BoxLayout(cardContent, BoxLayout.Y_AXIS));
        cardContent.setBorder(new EmptyBorder(24, 30, 16, 30));

        // Warning banner
        RoundedPanel warnBanner = new RoundedPanel(14, new Color(235, 77, 92, 28));
        warnBanner.setLayout(new BorderLayout());
        warnBanner.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        warnBanner.setPreferredSize(new Dimension(100, 44));
        JLabel warnLabel = new JLabel("  ⚠️  Read every instruction below before you begin");
        warnLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        warnLabel.setForeground(RED_TEXT);
        warnBanner.add(warnLabel, BorderLayout.CENTER);
        cardContent.add(warnBanner);
        cardContent.add(Box.createRigidArea(new Dimension(0, 14)));

        // Rule rows
        for (String rule : rules) {
            RoundedPanel row = new RoundedPanel(10, ROW_BG);
            row.setLayout(new BorderLayout());
            row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
            row.setPreferredSize(new Dimension(100, 42));

            JLabel bullet = new JLabel("●");
            bullet.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            bullet.setForeground(ACCENT);
            bullet.setBorder(new EmptyBorder(0, 18, 0, 10));
            row.add(bullet, BorderLayout.WEST);

            JLabel ruleLabel = new JLabel(rule);
            ruleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            ruleLabel.setForeground(TEXT_DARK);
            row.add(ruleLabel, BorderLayout.CENTER);

            cardContent.add(row);
            cardContent.add(Box.createRigidArea(new Dimension(0, 8)));
        }

        // Good luck message
        JLabel goodLuck = new JLabel("🎯  GOOD LUCK!", SwingConstants.CENTER);
        goodLuck.setFont(new Font("Segoe UI", Font.BOLD, 18));
        goodLuck.setForeground(GREEN);
        goodLuck.setAlignmentX(Component.CENTER_ALIGNMENT);
        goodLuck.setBorder(new EmptyBorder(10, 0, 4, 0));
        cardContent.add(goodLuck);

        JScrollPane scrollPane = new JScrollPane(cardContent);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        card.add(scrollPane, BorderLayout.CENTER);

        // --- Fixed footer with buttons (always visible, never overlapped) ---
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 24, 18));
        footer.setOpaque(false);

        backButton = new RoundedButton("◀  BACK", 14, GRAY, GRAY_HOVER);
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        backButton.setForeground(Color.WHITE);
        backButton.setPreferredSize(new Dimension(160, 46));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(this);
        footer.add(backButton);

        startButton = new RoundedButton("START EXAM  ▶", 14, GREEN, GREEN_HOVER);
        startButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        startButton.setForeground(Color.WHITE);
        startButton.setPreferredSize(new Dimension(200, 46));
        startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        startButton.addActionListener(this);
        footer.add(startButton);

        card.add(footer, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
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
            g2.fillOval(-110, -110, 310, 310);
            g2.fillOval(getWidth() - 210, getHeight() - 210, 360, 360);
        }
    }

    // ---------- Rounded card/row panel with soft shadow ----------
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

            g2.setColor(new Color(0, 0, 0, 45));
            g2.fill(new RoundRectangle2D.Double(3, 5, getWidth() - 3, getHeight() - 3, radius, radius));

            g2.setColor(bg);
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 5, getHeight() - 5, radius, radius));
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
