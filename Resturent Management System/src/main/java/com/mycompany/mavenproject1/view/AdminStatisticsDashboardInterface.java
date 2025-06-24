package com.mycompany.mavenproject1.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AdminStatisticsDashboardInterface extends JPanel {
    private final JLabel lblTotalProductsValue = new JLabel("0");
    private final JLabel lblTodaysRevenueValue = new JLabel("$0.00");
    private final JLabel lblTotalOrdersValue = new JLabel("0");
    private final DefaultListModel<String> popularProductsModel = new DefaultListModel<>();
    private final JList<String> listPopularProducts = new JList<>(popularProductsModel);

    public AdminStatisticsDashboardInterface() {
        initializeComponents();
        setupLayout();
    }

    private void initializeComponents() {
        setBackground(new Color(245, 245, 245));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // Style metric labels
        styleMetricValueLabel(lblTotalProductsValue);
        styleMetricValueLabel(lblTodaysRevenueValue);
        styleMetricValueLabel(lblTotalOrdersValue);

        // Style popular products list
        listPopularProducts.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        listPopularProducts.setVisibleRowCount(5);
        listPopularProducts.setFixedCellHeight(30);
        listPopularProducts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        // Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(getBackground());
        JLabel titleLabel = new JLabel("Admin Statistics Dashboard", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(51, 51, 51));
        titlePanel.add(titleLabel);

        // Metrics panel
        JPanel metricsPanel = new JPanel(new GridBagLayout());
        metricsPanel.setBackground(Color.WHITE);
        metricsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                new EmptyBorder(25, 25, 25, 25)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.anchor = GridBagConstraints.WEST;

        // Add metrics rows
        addMetricRow(metricsPanel, gbc, 0, "Total Products:", lblTotalProductsValue);
        addMetricRow(metricsPanel, gbc, 1, "Today's Revenue:", lblTodaysRevenueValue);
        addMetricRow(metricsPanel, gbc, 2, "Total Orders:", lblTotalOrdersValue);

        // Popular products label
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        JLabel popularLabel = new JLabel("Popular Products (Top 5):");
        popularLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        popularLabel.setForeground(new Color(51, 51, 51));
        metricsPanel.add(popularLabel, gbc);

        // Popular products list
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        metricsPanel.add(new JScrollPane(listPopularProducts), gbc);

        add(titlePanel, BorderLayout.NORTH);
        add(metricsPanel, BorderLayout.CENTER);

        JPanel spacerPanel = new JPanel();
        spacerPanel.setBackground(getBackground());
        spacerPanel.setPreferredSize(new Dimension(0, 20));
        add(spacerPanel, BorderLayout.SOUTH);
    }

    private void addMetricRow(JPanel panel, GridBagConstraints gbc, int row, String labelText, JLabel valueLabel) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        label.setForeground(new Color(51, 51, 51));

        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE;
        panel.add(label, gbc);

        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        panel.add(valueLabel, gbc);
        gbc.weightx = 0;
    }

    private void styleMetricValueLabel(JLabel label) {
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label.setForeground(new Color(34, 139, 34));
        label.setHorizontalAlignment(SwingConstants.RIGHT);
    }
    public void setTotalProducts(int count) {
        lblTotalProductsValue.setText(String.valueOf(count));
    }

    public void setTodaysRevenue(double revenue) {
        lblTodaysRevenueValue.setText(String.format("$%.2f", revenue));
    }

    public void setTotalOrders(int count) {
        lblTotalOrdersValue.setText(String.valueOf(count));
    }

    public void setPopularProducts(List<String> products) {
        popularProductsModel.clear();
        for (String product : products) {
            popularProductsModel.addElement(product);
        }
    }
}
