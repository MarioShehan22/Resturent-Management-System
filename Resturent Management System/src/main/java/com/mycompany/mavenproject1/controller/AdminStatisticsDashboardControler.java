package com.mycompany.mavenproject1.controller;

import java.util.List;

import com.mycompany.mavenproject1.view.AdminStatisticsDashboardInterface;

public class AdminStatisticsDashboardControler {
    private final AdminStatisticsDashboardInterface dashboard;

    public AdminStatisticsDashboardControler() {
        dashboard = new AdminStatisticsDashboardInterface();

        // Example data update inside constructor
        dashboard.setTotalProducts(123);
        dashboard.setTodaysRevenue(4567.89);
        dashboard.setTotalOrders(789);
        dashboard.setPopularProducts(List.of(
            "Product A - 100 orders",
            "Product B - 95 orders",
            "Product C - 80 orders",
            "Product D - 75 orders",
            "Product E - 70 orders"
        ));
    }

    public AdminStatisticsDashboardInterface getDashboard() {
        return dashboard;
    }
}

