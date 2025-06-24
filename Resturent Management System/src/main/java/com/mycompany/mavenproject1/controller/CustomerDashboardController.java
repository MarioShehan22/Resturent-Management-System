package com.mycompany.mavenproject1.controller;

import com.mycompany.mavenproject1.view.CustomerDashboard;
import com.mycompany.mavenproject1.view.CustomerDashboard.Product;
import com.mycompany.mavenproject1.view.CustomerDashboard.ProductItemPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDashboardController {
    private final CustomerDashboard view;

    public CustomerDashboardController(CustomerDashboard view) {
        this.view = view;
        initController();
    }

    private void initController() {
        // Load products (dummy data for now)
        loadProducts();

        // Button event handlers
        view.getBtnPlaceOrder().addActionListener(e -> placeOrder());
        view.getBtnViewOrders().addActionListener(e -> viewOrders());
        view.getBtnEditProfile().addActionListener(e -> editProfile());
        view.getBtnLogout().addActionListener(e -> logout());
    }

    private void loadProducts() {
        // Sample products - replace with real data source
        List<Product> products = new ArrayList<>();
        products.add(new Product("Product 1", "This is the first product", 19.99, new ImageIcon("images/product1.png")));
        products.add(new Product("Product 2", "Second product description goes here", 29.99, new ImageIcon("images/product2.png")));
        products.add(new Product("Product 3", "Another cool product", 39.99, new ImageIcon("images/product3.png")));
        // Add more as needed...

        view.setProducts(products);
    }

    private void placeOrder() {
        List<ProductItemPanel> selected = view.getSelectedProducts();
        if (selected.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please select at least one product to order.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Here you would handle the order placement logic (e.g., saving to DB)

        // For now, just show confirmation popup
        JOptionPane.showMessageDialog(view, "Order placed successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

        // Optionally, clear selections or update UI as needed
    }

    private void viewOrders() {
        // Open orders window or display orders list
        JOptionPane.showMessageDialog(view, "Showing current and past orders (feature to implement)", "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void editProfile() {
        // Open profile editing dialog or panel
        JOptionPane.showMessageDialog(view, "Edit profile feature to implement", "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(view, "Are you sure you want to logout?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Perform logout logic here (e.g., close window, return to login)
            JOptionPane.showMessageDialog(view, "Logged out successfully", "Logout", JOptionPane.INFORMATION_MESSAGE);
            // System.exit(0); // Or navigate to login screen
        }
    }
}
