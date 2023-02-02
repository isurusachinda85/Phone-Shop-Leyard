package lk.ijse.phoneshop.to;

public class CartDetail {
    private String orderId;
    private String code;
    private int qty;
    private String itemName;
    private double unitPrice;
    private String category;

    public CartDetail() {
    }

    public CartDetail(String orderId, String code, int qty, String itemName, double unitPrice, String category) {
        this.orderId = orderId;
        this.code = code;
        this.qty = qty;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.category = category;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
