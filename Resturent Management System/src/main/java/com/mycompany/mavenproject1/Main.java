package com.mycompany.mavenproject1;

import com.mycompany.mavenproject1.controller.AdminProductCreationController;
import com.mycompany.mavenproject1.view.AdminProductCreationForm;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.mycompany.mavenproject1.view.AdminStatisticsDashboardInterface;
import com.mycompany.mavenproject1.view.CustomerDashboard;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Login Form");
            AdminProductCreationForm form = new AdminProductCreationForm();
            new AdminProductCreationController(form);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(form);
//            frame.setContentPane(new AdminProductCreationForm());
            frame.pack();
            frame.setLocationRelativeTo(null); // center the window
            frame.setVisible(true);
        });
    }
}
