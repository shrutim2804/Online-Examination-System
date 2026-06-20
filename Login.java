import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame implements ActionListener {

    private JFrame frame;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JButton loginButton;

    // Theme colors
    private final Color GRADIENT_TOP    = new Color(20, 24, 60);
    private final Color GRADIENT_BOTTOM = new Color(76, 29, 110);
    private final Color ACCENT          = new Color(124, 92, 255);
    private final Color ACCENT_HOVER    = new Color(102, 70, 230);
    private final Color CARD_BG         = new Color(255, 255, 255, 245);
    private final Color FIELD_BG        = new Color(244, 245, 250);
    private final Color TEXT_DARK       = new Color(35, 35, 60);
    private final Color TEXT_MUTED      = new Color(120, 120, 140);

    Login() {
        frame = new JFrame("Online Examination System - Login");
        frame.setBounds(100, 100, 950, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ---- Gradient background panel ----
        GradientPanel background = new GradientPanel();
        background.setLayout(null);
        frame.setContentPane(background);

        // Title
        JLabel titleLabel = new JLabel("Online Examination System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 38));
        titleLabel.setBounds(0, 50, 950, 55);
        titleLabel.setForeground(Color.WHITE);
        background.add(titleLabel);

        // Subtitle
        JLabel subTitle = new JLabel("Java Programming Quiz", SwingConstants.CENTER);
        subTitle.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        subTitle.setBounds(0, 105, 950, 25);
        subTitle.setForeground(new Color(220, 215, 255));
        background.add(subTitle);

        // ---- Glassy login card ----
        RoundedPanel card = new RoundedPanel(28, CARD_BG);
        card.setLayout(null);
        card.setBounds(275, 180, 400, 340);
        background.add(card);

        JLabel loginLabel = new JLabel("Welcome Back");
        loginLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        loginLabel.setForeground(TEXT_DARK);
        loginLabel.setBounds(40, 28, 300, 32);
        card.add(loginLabel);

        JLabel loginSub = new JLabel("Login to start your exam");
        loginSub.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        loginSub.setForeground(TEXT_MUTED);
        loginSub.setBounds(40, 60, 300, 20);
        card.add(loginSub);

        // Username label
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        usernameLabel.setForeground(TEXT_MUTED);
        usernameLabel.setBounds(40, 100, 200, 18);
        card.add(usernameLabel);

        usernameField = new JTextField();
        styleField(usernameField);
        usernameField.setBounds(40, 120, 320, 42);
        card.add(usernameField);

        // Password label
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        passwordLabel.setForeground(TEXT_MUTED);
        passwordLabel.setBounds(40, 172, 200, 18);
        card.add(passwordLabel);

        passwordField = new JPasswordField();
        styleField(passwordField);
        passwordField.setBounds(40, 192, 320, 42);
        card.add(passwordField);

        // Login Button
        loginButton = new RoundedButton("LOGIN", 14, ACCENT, ACCENT_HOVER);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBounds(40, 250, 320, 46);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(this);
        card.add(loginButton);

        // Info label
        JLabel infoLabel = new JLabel("Demo Credentials: shruti / 11111", SwingConstants.CENTER);
        infoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        infoLabel.setForeground(TEXT_MUTED);
        infoLabel.setBounds(40, 304, 320, 20);
        card.add(infoLabel);

        // Allow Enter key to submit from password field
        passwordField.addActionListener(this);

        frame.setVisible(true);
    }

    private void styleField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        field.setBackground(FIELD_BG);
        field.setForeground(TEXT_DARK);
        field.setCaretColor(TEXT_DARK);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(225, 225, 235), 1, true),
                new EmptyBorder(8, 14, 8, 14)));
        field.setOpaque(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.equals("shruti") && password.equals("11111")) {
            frame.setVisible(false);
            new Inst();
        } else {
            JOptionPane.showMessageDialog(frame,
                    "Invalid username or password!\n\nOnly valid credential:\nshruti / 11111",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE);
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

            // subtle decorative circles
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

            // shadow
            g2.setColor(new Color(0, 0, 0, 60));
            g2.fill(new RoundRectangle2D.Double(6, 8, getWidth() - 6, getHeight() - 6, radius, radius));

            // card
            g2.setColor(bg);
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 8, getHeight() - 8, radius, radius));
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

    public static void main(String[] args) {
        new Login();
    }
}
