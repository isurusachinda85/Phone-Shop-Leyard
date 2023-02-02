package lk.ijse.phoneshop.to;

import java.time.LocalDate;
import java.time.LocalTime;

public class Order {
    private String orderId;
    private LocalDate orderDate;
    private LocalTime orderTime;
    private String customerId;



    public Order() {
    }

    public Order(String orderId, LocalDate orderDate, LocalTime orderTime, String customerId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.customerId = customerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalTime orderTime) {
        this.orderTime = orderTime;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
