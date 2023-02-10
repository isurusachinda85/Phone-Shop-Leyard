package lk.ijse.phoneshop.entity;

public class OrderDetail {
    private String orderId;
    private String itemCode;
    private String itemName;
    private int qty;
    private double unitPrice;

    public OrderDetail(String orderId, String itemCode, String itemName, int qty, double unitPrice) {
        this.orderId = orderId;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
