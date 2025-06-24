package com.mycompany.mavenproject1.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class CustomerDashboard extends JPanel {
    // Components for product list
    private final DefaultListModel<ProductItemPanel> productListModel = new DefaultListModel<>();
    private final JList<ProductItemPanel> listProducts = new JList<>(productListModel);
    
    private final JButton btnPlaceOrder = new JButton("Place Order");
    private final JButton btnViewOrders = new JButton("View My Orders");
    private final JButton btnEditProfile = new JButton("Edit Profile");
    private final JButton btnLogout = new JButton("Logout");

    public CustomerDashboard() {
        initializeComponents();
        setupLayout();
    }

    private void initializeComponents() {
        setBackground(new Color(245, 245, 245));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        listProducts.setCellRenderer(new ProductListCellRenderer());
        listProducts.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listProducts.setVisibleRowCount(8);
    }

    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Customer Dashboard");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        add(title, BorderLayout.NORTH);

        // Scroll pane for product list
        JScrollPane scrollPane = new JScrollPane(listProducts);
        scrollPane.setPreferredSize(new Dimension(600, 300));
        add(scrollPane, BorderLayout.CENTER);

        // Bottom panel with buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setBackground(getBackground());
        bottomPanel.add(btnPlaceOrder);
        bottomPanel.add(btnViewOrders);
        bottomPanel.add(btnEditProfile);
        bottomPanel.add(btnLogout);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Method to add products to list
    public void setProducts(List<Product> products) {
        productListModel.clear();
        for (Product p : products) {
            productListModel.addElement(new ProductItemPanel(p));
        }
    }

    // Get selected product panels
    public List<ProductItemPanel> getSelectedProducts() {
        return listProducts.getSelectedValuesList();
    }

    // Getters for buttons
    public JButton getBtnPlaceOrder() {
        return btnPlaceOrder;
    }

    public JButton getBtnViewOrders() {
        return btnViewOrders;
    }

    public JButton getBtnEditProfile() {
        return btnEditProfile;
    }

    public JButton getBtnLogout() {
        return btnLogout;
    }

    // --------------- Inner classes ----------------

    // Represents product with basic info
    public static class Product {
        public final String name;
        public final String description;
        public final double price;
        public final ImageIcon image;

        public Product(String name, String description, double price, ImageIcon image) {
            this.name = name;
            this.description = description;
            this.price = price;
            this.image = image;
        }
    }

    // Panel to show product info with checkbox for selection and quantity spinner
    public static class ProductItemPanel extends JPanel {
        private final JCheckBox chkSelect = new JCheckBox();
        private final JLabel lblImage = new JLabel();
        private final JLabel lblName = new JLabel();
        private final JLabel lblDescription = new JLabel();
        private final JLabel lblPrice = new JLabel();
        private final JSpinner spinnerQuantity = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));

        private final Product product;

        public ProductItemPanel(Product product) {
            this.product = product;
            setupPanel();
        }

        private void setupPanel() {
            setLayout(new BorderLayout(10, 10));
            setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            setPreferredSize(new Dimension(550, 100));
            setBackground(Color.WHITE);

            chkSelect.setBackground(Color.WHITE);

            lblImage.setIcon(product.image);
            lblName.setText(product.name);
            lblName.setFont(new Font("Segoe UI", Font.BOLD, 14));
            lblDescription.setText("<html><body style='width:250px'>" + product.description + "</body></html>");
            lblPrice.setText(String.format("$%.2f", product.price));
            lblPrice.setForeground(new Color(34, 139, 34));
            lblPrice.setFont(new Font("Segoe UI", Font.BOLD, 14));

            JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            leftPanel.setBackground(Color.WHITE);
            leftPanel.add(chkSelect);
            leftPanel.add(lblImage);

            JPanel centerPanel = new JPanel(new GridLayout(3, 1));
            centerPanel.setBackground(Color.WHITE);
            centerPanel.add(lblName);
            centerPanel.add(lblDescription);
            centerPanel.add(lblPrice);

            JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            rightPanel.setBackground(Color.WHITE);
            rightPanel.add(new JLabel("Qty:"));
            rightPanel.add(spinnerQuantity);

            add(leftPanel, BorderLayout.WEST);
            add(centerPanel, BorderLayout.CENTER);
            add(rightPanel, BorderLayout.EAST);
        }

        public boolean isSelected() {
            return chkSelect.isSelected();
        }

        public int getQuantity() {
            return (Integer) spinnerQuantity.getValue();
        }

        public Product getProduct() {
            return product;
        }
    }

    // Renderer to show ProductItemPanel in JList properly
    private static class ProductListCellRenderer implements ListCellRenderer<ProductItemPanel> {
        @Override
        public Component getListCellRendererComponent(JList<? extends ProductItemPanel> list, ProductItemPanel value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            value.setBackground(isSelected ? new Color(200, 230, 201) : Color.WHITE);
            return value;
        }
    }
}
