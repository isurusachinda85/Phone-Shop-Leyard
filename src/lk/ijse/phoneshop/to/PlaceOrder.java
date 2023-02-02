package lk.ijse.phoneshop.to;

import java.util.ArrayList;

public class PlaceOrder {
    private String customerId;
    private String orderId;
    double amount;
    String paymentType;
    String billNo;

    private ArrayList<CartDetail>orderDetail = new ArrayList<>();

    public PlaceOrder(String customerId, String orderId, double amount, String paymentType, String billNo) {
        this.customerId = customerId;
        this.orderId = orderId;
        this.amount = amount;
        this.paymentType = paymentType;
        this.billNo = billNo;
    }

    public PlaceOrder(String customerId, String orderId, ArrayList<CartDetail> orderDetail) {
        this.customerId = customerId;
        this.orderId = orderId;
        this.orderDetail = orderDetail;
    }

    public PlaceOrder() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public ArrayList<CartDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(ArrayList<CartDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }

    @Override
    public String toString() {
        return "PlaceOrder{" +
                "customerId='" + customerId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderDetail=" + orderDetail +
                '}';
    }
}
