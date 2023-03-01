package lk.ijse.phoneshop.dto;

import java.util.ArrayList;

public class PlaceOrderDTO {
    private String customerId;
    private String orderId;
    double amount;
    String paymentType;
    String billNo;

    private ArrayList<OrderDetailDTO>orderDetail = new ArrayList<>();

    public PlaceOrderDTO(String customerId, String orderId, double amount, String paymentType, String billNo) {
        this.customerId = customerId;
        this.orderId = orderId;
        this.amount = amount;
        this.paymentType = paymentType;
        this.billNo = billNo;
    }

    public PlaceOrderDTO(String customerId, String orderId, ArrayList<OrderDetailDTO> orderDetail) {
        this.customerId = customerId;
        this.orderId = orderId;
        this.orderDetail = orderDetail;
    }

    public PlaceOrderDTO() {
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

    public ArrayList<OrderDetailDTO> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(ArrayList<OrderDetailDTO> orderDetail) {
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
