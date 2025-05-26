package com.mycompany.mavenproject1;

import com.mycompany.mavenproject1.Model.User;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginForm extends JPanel {
    private JTextField txtEmail = new JTextField(10);
    private JPasswordField txtPassword = new JPasswordField(10);
    private JButton btnLogin = new JButton("Login");

    public LoginForm() {
        setLayout(new GridLayout(3, 2, 5, 2));

        add(new JLabel("Email:"));
        add(txtEmail);
        add(new JLabel("Password:"));
        add(txtPassword);
        add(new JLabel());
        add(btnLogin);

        btnLogin.addActionListener(e -> loginUser());
    }

    private void loginUser() {
        String email = txtEmail.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        // Validation
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your email.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            txtEmail.requestFocus();
            return;
        }

        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            txtEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your password.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            txtPassword.requestFocus();
            return;
        }

        try {
            // Query to find user by email and password
            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";

            ResultSet rs = CrudUtil.execute(sql, email, password);

            if (rs.next()) {
                // User found, populate User object if needed
                User user = new User();
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                // You can set other user fields here if you have them

                JOptionPane.showMessageDialog(this, "Login Successful! Welcome " + user.getEmail());
                // Proceed to next screen or functionality
            } else {
                JOptionPane.showMessageDialog(this, "Invalid email or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }

            rs.close();

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
