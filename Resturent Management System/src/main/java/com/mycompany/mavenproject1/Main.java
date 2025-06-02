package com.mycompany.mavenproject1;
import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Restaurant Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 650);
            
            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Registration", new RegistrationForm());
            tabbedPane.addTab("Login", new LoginForm());
            
            frame.add(tabbedPane);
            frame.setVisible(true);
        });
    }
}