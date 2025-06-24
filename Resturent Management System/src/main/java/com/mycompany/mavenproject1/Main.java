package com.mycompany.mavenproject1;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.mycompany.mavenproject1.view.CustomerLogin;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Login Form");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new CustomerLogin());
            frame.pack();
            frame.setLocationRelativeTo(null); // center the window
            frame.setVisible(true);
        });
    }
}
