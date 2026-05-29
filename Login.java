import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {
    
    private JFrame frame;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JButton loginButton;
    
    Login() {
        frame = new JFrame("Online Examination System - Login");
        frame.getContentPane().setBackground(new Color(25, 25, 112));
        frame.setBounds(100, 100, 900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        // Title
        JLabel titleLabel = new JLabel("Online Examination System");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 36));
        titleLabel.setBounds(220, 40, 500, 60);
        titleLabel.setForeground(new Color(255, 215, 0));
        frame.getContentPane().add(titleLabel);
        
        // Subtitle
        JLabel subTitle = new JLabel("Java Programming Quiz");
        subTitle.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        subTitle.setBounds(350, 100, 250, 30);
        subTitle.setForeground(Color.WHITE);
        frame.getContentPane().add(subTitle);
        
        // Login Panel
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255, 230));
        panel.setBounds(200, 160, 500, 320);
        panel.setLayout(null);
        frame.getContentPane().add(panel);
        
        panel.setBorder(BorderFactory.createLineBorder(new Color(0, 100, 200), 2));
        
        // Login Label
        JLabel loginLabel = new JLabel("LOGIN TO EXAM");
        loginLabel.setFont(new Font("Arial", Font.BOLD, 24));
        loginLabel.setForeground(new Color(25, 25, 112));
        loginLabel.setBounds(160, 15, 250, 40);
        panel.add(loginLabel);
        
        // Username
        JLabel usernameLabel = new JLabel("Username :");
        usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        usernameLabel.setBounds(50, 75, 120, 35);
        usernameLabel.setForeground(new Color(25, 25, 112));
        panel.add(usernameLabel);
        
        usernameField = new JTextField();
        usernameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        usernameField.setBounds(180, 75, 250, 35);
        usernameField.setBackground(new Color(240, 248, 255));
        panel.add(usernameField);
        
        // Password
        JLabel passwordLabel = new JLabel("Password :");
        passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        passwordLabel.setBounds(50, 135, 120, 35);
        passwordLabel.setForeground(new Color(25, 25, 112));
        panel.add(passwordLabel);
        
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        passwordField.setBounds(180, 135, 250, 35);
        passwordField.setBackground(new Color(240, 248, 255));
        panel.add(passwordField);
        
        // Login Button
        loginButton = new JButton("LOGIN");
        loginButton.setBackground(new Color(0, 150, 0));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        loginButton.setBounds(160, 210, 180, 45);
        loginButton.setFocusPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(this);
        panel.add(loginButton);
        
        // Info label - Updated to show only shruti
        JLabel infoLabel = new JLabel("Demo Credentials: shruti / 11111");
        infoLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        infoLabel.setBounds(150, 280, 250, 20);
        infoLabel.setForeground(Color.GRAY);
        panel.add(infoLabel);
        
        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        
        // Only shruti/11111 is valid now
        if(username.equals("shruti") && password.equals("11111")) {
            frame.setVisible(false);
            new Inst();
        } else {
            JOptionPane.showMessageDialog(frame, 
                "Invalid username or password!\n\nOnly valid credential:\nshruti / 11111", 
                "Login Failed", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        new Login();
    }
}
