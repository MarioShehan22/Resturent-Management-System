package com.mycompany.mavenproject1.controller;

import com.mycompany.mavenproject1.model.Product;
import com.mycompany.mavenproject1.view.AdminProductCreationForm;

import javax.swing.*;
import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;

public class AdminProductCreationController {
    private final AdminProductCreationForm view;

    public AdminProductCreationController(AdminProductCreationForm view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.getBtnAddProduct().addActionListener(e -> addProduct());
    }

    private void addProduct() {
        String productName = view.getProductName();
        String category = view.getCategory();
        String description = view.getDescription();
        String priceStr = view.getPrice();
        File imageFile = view.getSelectedImageFile();
        String stockStr = view.getStockQuantity();
        boolean statusActive = view.isStatusActive();

        // Validation

        // Product Name: max 100 chars, alphanumeric + space only
        if (productName.isEmpty() || productName.length() > 100 || !productName.matches("[a-zA-Z0-9 ]+")) {
            showError("Product Name must be alphanumeric, spaces allowed, max 100 characters.");
            return;
        }

        // Description max 500 chars
        if (description.isEmpty() || description.length() > 500) {
            showError("Description is required and must be at most 500 characters.");
            return;
        }

        // Price: decimal 0.01 to 9999.99
        BigDecimal price;
        try {
            price = new BigDecimal(priceStr);
            if (price.compareTo(new BigDecimal("0.01")) < 0 || price.compareTo(new BigDecimal("9999.99")) > 0) {
                showError("Price must be between 0.01 and 9999.99.");
                return;
            }
        } catch (NumberFormatException ex) {
            showError("Invalid price format.");
            return;
        }

        // Image file validation
        if (imageFile == null) {
            showError("Please select an image file.");
            return;
        }

        String filename = imageFile.getName().toLowerCase();
        if (!(filename.endsWith(".png") || filename.endsWith(".jpg") || filename.endsWith(".jpeg"))) {
            showError("Image must be a PNG or JPG file.");
            return;
        }

        try {
            long fileSize = Files.size(imageFile.toPath());
            if (fileSize > 5 * 1024 * 1024) { // 5MB
                showError("Image file size must be less than 5MB.");
                return;
            }
        } catch (Exception ex) {
            showError("Error reading image file.");
            return;
        }

        // Stock Quantity: integer max 5 million (5,000,000)
        int stockQty;
        try {
            stockQty = Integer.parseInt(stockStr);
            if (stockQty < 0 || stockQty > 5_000_000) {
                showError("Stock Quantity must be between 0 and 5,000,000.");
                return;
            }
        } catch (NumberFormatException ex) {
            showError("Invalid stock quantity.");
            return;
        }

        // If all validation passed, create Product object
        Product product = new Product();
        product.setProductName(productName);
        product.setCategory(category);
        product.setDescription(description);
        product.setPrice(price);
        product.setImagePath(imageFile.getAbsolutePath());
        product.setStockQuantity(stockQty);
        product.setActive(statusActive);

        // TODO: Save product to database or data store here

        JOptionPane.showMessageDialog(view, "Product Added Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        view.clearForm();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(view, message, "Validation Error", JOptionPane.ERROR_MESSAGE);
    }
}
