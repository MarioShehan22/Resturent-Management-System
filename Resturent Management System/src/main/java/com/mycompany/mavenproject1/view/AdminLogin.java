package com.mycompany.mavenproject1.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.mycompany.mavenproject1.Db.CrudUtil;
import com.mycompany.mavenproject1.Model.User;
public class AdminLogin extends JPanel{
    private final JTextField txtEmail = new JTextField(20);
    private final JPasswordField txtPassword = new JPasswordField(20);
    private final JButton btnLogin = new JButton("Sign In");
    private final JButton btnForgotPassword = new JButton("Forgot Password?");
    private JCheckBox chkRememberMe = new JCheckBox("Remember me");
    private final JCheckBox chkShowPassword = new JCheckBox("Show Password");

    public AdminLogin() {
        initializeComponents();
        setupLayout();
        setupEventHandlers();
    }

    private void initializeComponents() {
        // Set modern look and feel
        setBackground(new Color(245, 245, 245));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // Style text fields
        styleTextField(txtEmail, "Enter your email address");
        stylePasswordField(txtPassword);

        // Style buttons
        styleButton(btnLogin, new Color(25, 118, 210), Color.WHITE);
        styleLinkButton(btnForgotPassword);

        // Style checkboxes
        chkRememberMe.setBackground(Color.WHITE);
        chkRememberMe.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        chkShowPassword.setBackground(Color.WHITE);
        chkShowPassword.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(getBackground());
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Welcome Back Customer", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(new Color(51, 51, 51));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Sign in to your account", JLabel.CENTER);
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(new Color(102, 102, 102));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        titlePanel.add(Box.createVerticalStrut(10));
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(subtitleLabel);
        titlePanel.add(Box.createVerticalStrut(20));

        // Login Card Panel
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BorderLayout());
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                new EmptyBorder(40, 40, 40, 40)
        ));

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 0, 8, 0);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Email field
        JLabel emailLabel = new JLabel("Email Address");
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        emailLabel.setForeground(new Color(51, 51, 51));
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(emailLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(txtEmail, gbc);

        // Password field
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordLabel.setForeground(new Color(51, 51, 51));
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.insets = new Insets(20, 0, 8, 0);
        formPanel.add(passwordLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 8, 0);
        formPanel.add(txtPassword, gbc);

        // Show password checkbox
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.insets = new Insets(5, 0, 15, 0);
        formPanel.add(chkShowPassword, gbc);

        // Options Panel (Remember me and Forgot password)
        JPanel optionsPanel = new JPanel(new BorderLayout());
        optionsPanel.setBackground(Color.WHITE);
        optionsPanel.add(chkRememberMe, BorderLayout.WEST);
        optionsPanel.add(btnForgotPassword, BorderLayout.EAST);

        gbc.gridx = 0; gbc.gridy = 5;
        gbc.insets = new Insets(10, 0, 20, 0);
        formPanel.add(optionsPanel, gbc);

        // Login button
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.insets = new Insets(10, 0, 0, 0);
        formPanel.add(btnLogin, gbc);

        cardPanel.add(formPanel, BorderLayout.CENTER);

        // Add panels to main layout
        add(titlePanel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);

        // Add some spacing at bottom
        JPanel spacerPanel = new JPanel();
        spacerPanel.setBackground(getBackground());
        spacerPanel.setPreferredSize(new Dimension(0, 20));
        add(spacerPanel, BorderLayout.SOUTH);
    }

    private void styleTextField(final JTextField field, final String placeholder) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        field.setPreferredSize(new Dimension(300, 45));

        // Add placeholder effect
        field.setForeground(Color.GRAY);
        field.setText(placeholder);

        field.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
                field.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(25, 118, 210), 2),
                        BorderFactory.createEmptyBorder(7, 11, 7, 11)
                ));
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (field.getText().isEmpty()) {
                    field.setForeground(Color.GRAY);
                    field.setText(placeholder);
                }
                field.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                        BorderFactory.createEmptyBorder(8, 12, 8, 12)
                ));
            }
        });
    }

    private void stylePasswordField(final JPasswordField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        field.setPreferredSize(new Dimension(300, 45));

        field.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                field.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(25, 118, 210), 2),
                        BorderFactory.createEmptyBorder(7, 11, 7, 11)
                ));
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                field.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                        BorderFactory.createEmptyBorder(8, 12, 8, 12)
                ));
            }
        });
    }

    private void styleButton(final JButton button, final Color bgColor, Color textColor) {
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(300, 35));

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
    }

    private void styleLinkButton(final JButton button) {
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setForeground(new Color(25, 118, 210));
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setForeground(new Color(25, 118, 210).darker());
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setForeground(new Color(25, 118, 210));
            }
        });
    }

    private void setupEventHandlers() {
        btnLogin.addActionListener(e -> loginUser());
        btnForgotPassword.addActionListener(e -> forgotPassword());
        chkShowPassword.addActionListener(e -> togglePasswordVisibility());

        // Add Enter key support
        txtEmail.addActionListener(e -> txtPassword.requestFocus());
        txtPassword.addActionListener(e -> loginUser());
    }

    private void togglePasswordVisibility() {
        if (chkShowPassword.isSelected()) {
            txtPassword.setEchoChar((char) 0);
        } else {
            txtPassword.setEchoChar('•');
        }
    }

    private void forgotPassword() {
        JOptionPane.showMessageDialog(this,
                "Password reset functionality will be implemented soon.\nPlease contact support for assistance.",
                "Forgot Password",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private String getFieldText(JTextField field, String placeholder) {
        String text = field.getText().trim();
        return text.equals(placeholder) ? "" : text;
    }

    private void loginUser() {
        String email = getFieldText(txtEmail, "Enter your email address");
        String password = new String(txtPassword.getPassword()).trim();

        // Validation
        if (email.isEmpty()) {
            showValidationError("Please enter your email.", txtEmail);
            return;
        }

        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            showValidationError("Please enter a valid email address.", txtEmail);
            return;
        }

        if (password.isEmpty()) {
            showValidationError("Please enter your password.", txtPassword);
            return;
        }

        // Show loading state
        btnLogin.setText("Signing In...");
        btnLogin.setEnabled(false);

        SwingUtilities.invokeLater(() -> {
            try {
                String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
                ResultSet rs = CrudUtil.execute(sql, email, password);

                if (rs.next()) {
                    User user = new User();
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    // Set other user fields if available
                    if (rs.findColumn("name") > 0) {
                        // user.setName(rs.getString("name"));
                    }

                    JOptionPane.showMessageDialog(this,
                            "Welcome back!\nLogin successful.",
                            "Login Success",
                            JOptionPane.INFORMATION_MESSAGE);

                    // Clear fields for security
                    txtPassword.setText("");
                    chkShowPassword.setSelected(false);
                    togglePasswordVisibility();

                } else {
                    showValidationError("Invalid email or password.\nPlease check your credentials and try again.", null);
                }

                rs.close();

            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Database connection error.\nPlease try again later.",
                        "Connection Error",
                        JOptionPane.ERROR_MESSAGE);
            } finally {
                // Reset button state
                btnLogin.setText("Sign In");
                btnLogin.setEnabled(true);
            }
        });
    }

    private void showValidationError(String message, JComponent component) {
        JOptionPane.showMessageDialog(this, message, "Validation Error", JOptionPane.ERROR_MESSAGE);
        if (component != null) {
            component.requestFocus();
        }
    }

    public JCheckBox getChkRememberMe() {
        return chkRememberMe;
    }

    public void setChkRememberMe(JCheckBox chkRememberMe) {
        this.chkRememberMe = chkRememberMe;
    }
}