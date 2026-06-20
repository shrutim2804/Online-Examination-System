import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Result1 {

    private JFrame frame;
    private int score;

    // ---- Theme colors (matches Login & QuestionPaper) ----
    private final Color GRADIENT_TOP    = new Color(20, 24, 60);
    private final Color GRADIENT_BOTTOM = new Color(76, 29, 110);
    private final Color ACCENT          = new Color(124, 92, 255);
    private final Color GREEN           = new Color(46, 196, 121);
    private final Color ORANGE          = new Color(255, 159, 67);
    private final Color RED             = new Color(235, 77, 92);
    private final Color RED_HOVER       = new Color(212, 60, 75);
    private final Color CARD_BG         = new Color(255, 255, 255, 248);
    private final Color TEXT_DARK       = new Color(35, 35, 60);
    private final Color TEXT_MUTED      = new Color(120, 120, 140);

    Result1(int score) {
        this.score = score;

        frame = new JFrame("Online Examination - Result");
        frame.setBounds(300, 150, 650, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GradientPanel background = new GradientPanel();
        background.setLayout(null);
        frame.setContentPane(background);

        // Title
        JLabel titleLabel = new JLabel("Examination Result", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(0, 45, 650, 45);
        background.add(titleLabel);

        JLabel subTitle = new JLabel("Here's how you did", SwingConstants.CENTER);
        subTitle.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        subTitle.setForeground(new Color(220, 215, 255));
        subTitle.setBounds(0, 90, 650, 22);
        background.add(subTitle);

        int percentage = (score * 100) / 10;
        Color ringColor;
        String message;
        Color msgColor;

        if (score >= 8) {
            ringColor = GREEN;
            message = "🏆 Excellent! You have mastered Java!";
            msgColor = GREEN;
        } else if (score >= 6) {
            ringColor = ACCENT;
            message = "👍 Good job! Keep practicing!";
            msgColor = ACCENT;
        } else if (score >= 4) {
            ringColor = ORANGE;
            message = "📚 Fair attempt! Need more practice.";
            msgColor = ORANGE;
        } else {
            ringColor = RED;
            message = "💪 Need improvement! Study harder!";
            msgColor = RED;
        }

        // ---- Result Card ----
        RoundedPanel resultPanel = new RoundedPanel(28, CARD_BG);
        resultPanel.setLayout(null);
        resultPanel.setBounds(75, 140, 500, 400);
        background.add(resultPanel);

        // Score ring (custom painted progress circle)
        ScoreRing ring = new ScoreRing(percentage, ringColor);
        ring.setBounds(175, 35, 150, 150);
        resultPanel.add(ring);

        // Score label
        JLabel scoreLabel = new JLabel("Score: " + score + " / 10", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        scoreLabel.setForeground(TEXT_DARK);
        scoreLabel.setBounds(0, 195, 500, 36);
        resultPanel.add(scoreLabel);

        JLabel percentLabel = new JLabel(percentage + "% correct", SwingConstants.CENTER);
        percentLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        percentLabel.setForeground(TEXT_MUTED);
        percentLabel.setBounds(0, 230, 500, 22);
        resultPanel.add(percentLabel);

        // Divider
        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(230, 230, 238));
        separator.setBounds(60, 270, 380, 1);
        resultPanel.add(separator);

        // Message badge
        RoundedPanel msgBadge = new RoundedPanel(14, withAlpha(msgColor, 28));
        msgBadge.setLayout(new BorderLayout());
        msgBadge.setBounds(40, 290, 420, 48);
        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        messageLabel.setForeground(msgColor);
        msgBadge.add(messageLabel, BorderLayout.CENTER);
        resultPanel.add(msgBadge);

        // Exit Button
        RoundedButton closeButton = new RoundedButton("✖  EXIT", 14, RED, RED_HOVER);
        closeButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        closeButton.setForeground(Color.WHITE);
        closeButton.setBounds(170, 355, 160, 46);
        closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeButton.addActionListener(e -> System.exit(0));
        resultPanel.add(closeButton);

        frame.setVisible(true);
    }

    private Color withAlpha(Color c, int alpha) {
        return new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha);
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
            g2.fillOval(-100, -100, 300, 300);
            g2.fillOval(getWidth() - 200, getHeight() - 200, 350, 350);
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

            g2.setColor(new Color(0, 0, 0, 55));
            g2.fill(new RoundRectangle2D.Double(5, 7, getWidth() - 5, getHeight() - 5, radius, radius));

            g2.setColor(bg);
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 7, getHeight() - 7, radius, radius));
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

    // ---------- Circular score ring with centered percentage text ----------
    private class ScoreRing extends JComponent {
        private final int percent;
        private final Color color;

        ScoreRing(int percent, Color color) {
            this.percent = percent;
            this.color = color;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int stroke = 12;
            int size = Math.min(getWidth(), getHeight()) - stroke;
            int x = (getWidth() - size) / 2;
            int y = (getHeight() - size) / 2;

            // track
            g2.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.setColor(new Color(235, 235, 242));
            g2.drawOval(x, y, size, size);

            // progress arc
            g2.setColor(color);
            int angle = (int) (360 * (percent / 100.0));
            g2.drawArc(x, y, size, size, 90, -angle);

            // percentage text
            g2.setFont(new Font("Segoe UI", Font.BOLD, 26));
            FontMetrics fm = g2.getFontMetrics();
            String text = percent + "%";
            int tx = (getWidth() - fm.stringWidth(text)) / 2;
            int ty = (getHeight() + fm.getAscent()) / 2 - 4;
            g2.setColor(TEXT_DARK);
            g2.drawString(text, tx, ty);

            g2.dispose();
        }
    }
}
