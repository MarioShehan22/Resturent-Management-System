package com.mycompany.mavenproject1;

import com.mycompany.mavenproject1.Model.User;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegistrationForm extends JPanel {
    private JTextField txtName = new JTextField(20);
    private final JTextField txtEmail = new JTextField(20);
    private JTextField txtPhone = new JTextField(20);
    private JFormattedTextField txtDOB = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));
    private JPasswordField txtPassword = new JPasswordField(20);
    private JButton btnRegister = new JButton("Register");

    public RegistrationForm() {
        setLayout(new GridLayout(6, 2, 5, 5));
        
        txtDOB.setColumns(20);
        
        add(new JLabel("Full Name:"));
        add(txtName);
        add(new JLabel("Email:"));
        add(txtEmail);
        add(new JLabel("Phone:"));
        add(txtPhone);
        add(new JLabel("Date of Birth (yyyy-MM-dd):"));
        add(txtDOB);
        add(new JLabel("Password:"));
        add(txtPassword);
        add(new JLabel());
        add(btnRegister);

        btnRegister.addActionListener(e -> registerUser());
    }

    private void registerUser() {
        // Basic validation example (you can expand this)
        if (txtName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your full name.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            txtName.requestFocus();
            return;
        }
        if (txtEmail.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your email.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            txtEmail.requestFocus();
            return;
        }
        if (!txtEmail.getText().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            txtEmail.requestFocus();
            return;
        }
        if (txtPhone.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your phone number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            txtPhone.requestFocus();
            return;
        }
        if (txtDOB.getValue() == null) {
            JOptionPane.showMessageDialog(this, "Please enter your date of birth.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            txtDOB.requestFocus();
            return;
        }
        if (txtPassword.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "Please enter a password.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            txtPassword.requestFocus();
            return;
        }

        try {
            String sql = "INSERT INTO users (name, email, phone, dob, password) VALUES (?, ?, ?, ?, ?)";

            // Cast DOB to java.sql.Date for JDBC
            java.util.Date utilDate = (java.util.Date) txtDOB.getValue();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            boolean inserted = CrudUtil.execute(sql,
                    txtName.getText().trim(),
                    txtEmail.getText().trim(),
                    txtPhone.getText().trim(),
                    sqlDate,
                    new String(txtPassword.getPassword())
            );

            if (inserted) {
                JOptionPane.showMessageDialog(this, "Registration Successful!");
                // Optionally clear fields after successful registration
                txtName.setText("");
                txtEmail.setText("");
                txtPhone.setText("");
                txtDOB.setValue(null);
                txtPassword.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}