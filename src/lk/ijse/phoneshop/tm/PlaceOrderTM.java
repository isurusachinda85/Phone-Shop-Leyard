package lk.ijse.phoneshop.tm;

import javafx.scene.control.Button;

public class PlaceOrderTM {
    private String code;
    private String itemName;
    private double unitPrice;
    private int qty;
    private String category;
    private double total;
    private Button deleteButton;

    public PlaceOrderTM(String code, String itemName, double unitPrice, int qty, String category, double total, Button deleteButton) {
        this.code = code;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.category = category;
        this.total = total;
        this.deleteButton = deleteButton;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(Button deleteButton) {
        this.deleteButton = deleteButton;
    }
}
