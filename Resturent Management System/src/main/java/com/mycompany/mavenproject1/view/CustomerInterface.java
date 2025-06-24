package com.mycompany.mavenproject1.view;

import java.awt.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CustomerInterface {
    private final JTextField txtName = new JTextField(20);
    private final JTextField txtEmail = new JTextField(20);
    private final JTextField txtPhone = new JTextField(20);
    private final JFormattedTextField txtDOB = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));
    private final JPasswordField txtPassword = new JPasswordField(20);
    private final JPasswordField txtConfirmPassword = new JPasswordField(20);
    private final JButton btnRegister = new JButton("Create Account");
    private final JButton btnClear = new JButton("Clear");
    private final JCheckBox chkShowPassword = new JCheckBox("Show Password");

    public CustomerInterface() {
        initializeComponents();
        setupLayout();
        setupEventHandlers();
    }

    private void initializeComponents() {
        // Set modern look and feel
        setBackground(new Color(245, 245, 245));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // Style text fields
        styleTextField(txtName, "Enter your full name");
        styleTextField(txtEmail, "Enter your email address");
        styleTextField(txtPhone, "Enter your phone number");
        styleTextField(txtDOB, "YYYY-MM-DD");

        txtDOB.setColumns(20);

        // Style password fields
        stylePasswordField(txtPassword);
        stylePasswordField(txtConfirmPassword);

        // Style buttons
        styleButton(btnRegister, new Color(34, 139, 34), Color.WHITE);
        styleButton(btnClear, new Color(220, 220, 220), Color.BLACK);

        // Style checkbox
        chkShowPassword.setBackground(getBackground());
        chkShowPassword.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(getBackground());
        JLabel titleLabel = new JLabel("Create New Account", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(51, 51, 51));
        titlePanel.add(titleLabel);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                new EmptyBorder(25, 25, 25, 25)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        // Add form fields
        addFormField(formPanel, gbc, "Full Name *", txtName, 0);
        addFormField(formPanel, gbc, "Email Address *", txtEmail, 1);
        addFormField(formPanel, gbc, "Phone Number *", txtPhone, 2);
        addFormField(formPanel, gbc, "Date of Birth *", txtDOB, 3);
        addFormField(formPanel, gbc, "Password *", txtPassword, 4);
        addFormField(formPanel, gbc, "Confirm Password *", txtConfirmPassword, 5);

        // Checkbox
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 2;
        formPanel.add(chkShowPassword, gbc);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(btnRegister);
        buttonPanel.add(btnClear);

        gbc.gridx = 0; gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(buttonPanel, gbc);

        // Add panels to main layout
        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);

        // Add some spacing at bottom
        JPanel spacerPanel = new JPanel();
        spacerPanel.setBackground(getBackground());
        spacerPanel.setPreferredSize(new Dimension(0, 20));
        add(spacerPanel, BorderLayout.SOUTH);
    }

    private void addFormField(JPanel panel, GridBagConstraints gbc, String labelText, JComponent field, int row) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setForeground(new Color(51, 51, 51));

        gbc.gridx = 0; gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(label, gbc);

        gbc.gridx = 1; gbc.gridy = row;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(field, gbc);
        gbc.weightx = 0;
    }

    private void styleTextField(JTextField field, String placeholder) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)
        ));
        field.setPreferredSize(new Dimension(250, 30));

        // Add placeholder effect
        field.setForeground(Color.GRAY);
        field.setText(placeholder);

        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (field.getText().isEmpty()) {
                    field.setForeground(Color.GRAY);
                    field.setText(placeholder);
                }
            }
        });
    }

    private void stylePasswordField(JPasswordField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)
        ));
        field.setPreferredSize(new Dimension(250, 30));
    }

    private void styleButton(JButton button, Color bgColor, Color textColor) {
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(120, 32));

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
    }

    private void setupEventHandlers() {
        btnRegister.addActionListener(e -> registerUser());
        btnClear.addActionListener(e -> clearFields());
        chkShowPassword.addActionListener(e -> togglePasswordVisibility());
    }

    private void togglePasswordVisibility() {
        if (chkShowPassword.isSelected()) {
            txtPassword.setEchoChar((char) 0);
            txtConfirmPassword.setEchoChar((char) 0);
        } else {
            txtPassword.setEchoChar('•');
            txtConfirmPassword.setEchoChar('•');
        }
    }

    private void clearFields() {
        txtName.setText("Enter your full name");
        txtName.setForeground(Color.GRAY);
        txtEmail.setText("Enter your email address");
        txtEmail.setForeground(Color.GRAY);
        txtPhone.setText("Enter your phone number");
        txtPhone.setForeground(Color.GRAY);
        txtDOB.setValue(null);
        txtPassword.setText("");
        txtConfirmPassword.setText("");
        chkShowPassword.setSelected(false);
        togglePasswordVisibility();
    }

    private String getFieldText(JTextField field, String placeholder) {
        String text = field.getText().trim();
        return text.equals(placeholder) ? "" : text;
    }

}
