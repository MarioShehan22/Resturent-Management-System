package com.mycompany.mavenproject1.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AdminProductCreationForm extends JPanel {
    private final JTextField txtProductName = new JTextField(20);
    private final JComboBox<String> cmbCategory = new JComboBox<>(new String[]{"Appetizers", "Main Course", "Beverage", "Desserts"});
    private final JTextArea txtDescription = new JTextArea(5, 20);
    private final JTextField txtPrice = new JTextField(20);
    private final JTextField txtImagePath = new JTextField(20);
    private final JButton btnBrowseImage = new JButton("Browse...");
    private final JTextField txtStockQuantity = new JTextField(20);
    private final JComboBox<String> cmbStatus = new JComboBox<>(new String[]{"Active", "Inactive"});
    private final JButton btnAddProduct = new JButton("Add Product");

    private File selectedImageFile;

    public AdminProductCreationForm() {
        initializeComponents();
        setupLayout();
        setupEvents();
    }

    private void initializeComponents() {
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setBackground(new Color(245, 245, 245));

        txtDescription.setLineWrap(true);
        txtDescription.setWrapStyleWord(true);

        txtImagePath.setEditable(false);
    }

    private void setupLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        // Product Name
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Product Name *"), gbc);
        gbc.gridx = 1;
        add(txtProductName, gbc);

        // Category
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Category *"), gbc);
        gbc.gridx = 1;
        add(cmbCategory, gbc);

        // Description
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Description *"), gbc);
        gbc.gridx = 1;
        add(new JScrollPane(txtDescription), gbc);

        // Price
        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Price *"), gbc);
        gbc.gridx = 1;
        add(txtPrice, gbc);

        // Image
        gbc.gridx = 0; gbc.gridy = 4;
        add(new JLabel("Image *"), gbc);
        gbc.gridx = 1;
        JPanel imagePanel = new JPanel(new BorderLayout(5,0));
        imagePanel.add(txtImagePath, BorderLayout.CENTER);
        imagePanel.add(btnBrowseImage, BorderLayout.EAST);
        add(imagePanel, gbc);

        // Stock Quantity
        gbc.gridx = 0; gbc.gridy = 5;
        add(new JLabel("Stock Quantity *"), gbc);
        gbc.gridx = 1;
        add(txtStockQuantity, gbc);

        // Status
        gbc.gridx = 0; gbc.gridy = 6;
        add(new JLabel("Status *"), gbc);
        gbc.gridx = 1;
        add(cmbStatus, gbc);

        // Add button
        gbc.gridx = 0; gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnAddProduct, gbc);
    }

    private void setupEvents() {
        btnBrowseImage.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png"));
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedImageFile = fileChooser.getSelectedFile();
                txtImagePath.setText(selectedImageFile.getAbsolutePath());
            }
        });
    }

    // Getters for Controller

    public String getProductName() { return txtProductName.getText().trim(); }
    public String getCategory() { return (String) cmbCategory.getSelectedItem(); }
    public String getDescription() { return txtDescription.getText().trim(); }
    public String getPrice() { return txtPrice.getText().trim(); }
    public File getSelectedImageFile() { return selectedImageFile; }
    public String getStockQuantity() { return txtStockQuantity.getText().trim(); }
    public boolean isStatusActive() { return cmbStatus.getSelectedItem().equals("Active"); }
    public JButton getBtnAddProduct() { return btnAddProduct; }

    public void clearForm() {
        txtProductName.setText("");
        cmbCategory.setSelectedIndex(0);
        txtDescription.setText("");
        txtPrice.setText("");
        txtImagePath.setText("");
        selectedImageFile = null;
        txtStockQuantity.setText("");
        cmbStatus.setSelectedIndex(0);
    }
}
