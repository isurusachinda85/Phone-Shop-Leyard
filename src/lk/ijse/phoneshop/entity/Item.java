package lk.ijse.phoneshop.entity;

public class Item {
    private String itemCode;
    private String brand;
    private String modalNo;
    private String itemName;
    private double price;
    private String warranty;
    private int qty;
    private String category;

    public Item(String itemCode, String brand, String modalNo, String itemName, double price, String warranty, int qty, String category) {
        this.itemCode = itemCode;
        this.brand = brand;
        this.modalNo = modalNo;
        this.itemName = itemName;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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
}
