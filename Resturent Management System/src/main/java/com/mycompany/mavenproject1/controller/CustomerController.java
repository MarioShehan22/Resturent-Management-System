package com.mycompany.mavenproject1.controller;

import com.mycompany.mavenproject1.view.CustomerInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerController {
    private final CustomerInterface view;

    public CustomerController(CustomerInterface view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.getBtnRegister().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegister();
            }
        });

        view.getBtnClear().addActionListener(e -> view.clearForm());
    }

    private void handleRegister() {
        String name = view.getTxtName().getText().trim();
        String email = view.getTxtEmail().getText().trim();
        String phone = view.getTxtPhone().getText().trim();
        String dob = view.getTxtDOB().getText().trim();
        String password = new String(view.getTxtPassword().getPassword());
        String confirmPassword = new String(view.getTxtConfirmPassword().getPassword());

        // Basic placeholder check: ignore if text equals placeholders
        if (name.isEmpty() || name.equals("Enter your full name") ||
            email.isEmpty() || email.equals("Enter your email address") ||
            phone.isEmpty() || phone.equals("Enter your phone number") ||
            dob.isEmpty() || dob.equals("YYYY-MM-DD") ||
            password.isEmpty() || confirmPassword.isEmpty()) {

            JOptionPane.showMessageDialog(view, "Please fill in all required fields.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(view, "Passwords do not match.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // You can add more validation here (email format, phone number format, date format, etc.)

        // Simulate successful registration
        JOptionPane.showMessageDialog(view, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        // Clear form after registration
        view.clearForm();
    }
}
