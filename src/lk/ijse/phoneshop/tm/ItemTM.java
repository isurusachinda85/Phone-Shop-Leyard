package lk.ijse.phoneshop.tm;

import javafx.scene.control.Button;

public class ItemTM {
    private String itemCode;
    private String brand;
    private String modalNo;
    private String name;
    private double price;
    private String warranty;
    private int qty;
    private String category;
    private Button delete;

    public ItemTM(String itemCode, String brand, String modalNo, String name, double price, String warranty, int qty, String category, Button delete) {
        this.itemCode = itemCode;
        this.brand = brand;
        this.modalNo = modalNo;
        this.name = name;
        this.price = price;
        this.warranty = warranty;
        this.qty = qty;
        this.category = category;
        this.delete = delete;
    }

    public ItemTM() {
    }

    public ItemTM(String itemCode, String brand, String modalNo, String name, double price, String warranty, int qty, String category) {
        this.itemCode = itemCode;
        this.brand = brand;
        this.modalNo = modalNo;
        this.name = name;
        this.price = price;
        this.warranty = warranty;
        this.qty = qty;
        this.category = category;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModalNo() {
        return modalNo;
    }

    public void setModalNo(String modalNo) {
        this.modalNo = modalNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
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

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }
}
