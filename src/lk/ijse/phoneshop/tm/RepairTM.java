package lk.ijse.phoneshop.tm;

import javafx.scene.control.Button;

import java.util.Date;

public class RepairTM {
    private String repairNo;
    private String customerId;
    private String customerName;
    private int phoneNo;
    private String itemCode;
    private String deviceName;
    private String deviceProblem;
    private double price;
    private double amount;
    private double due;
    private String state;
    private Button delete;

    public RepairTM() {
    }

    public RepairTM(String repairNo, String customerId, String customerName, int phoneNo, String itemCode, String deviceName, String deviceProblem, double price, double amount, double due, String state, Button delete) {
        this.repairNo = repairNo;
        this.customerId = customerId;
        this.customerName = customerName;
        this.phoneNo = phoneNo;
        this.itemCode = itemCode;
        this.deviceName = deviceName;
        this.deviceProblem = deviceProblem;
        this.price = price;
        this.amount = amount;
        this.due = due;
        this.state = state;
        this.delete = delete;
    }

    public RepairTM(String repairNo, String customerName, int phoneNo, String deviceName, String deviceProblem, double price, double amount, double due, String state, Button delete) {
        this.repairNo = repairNo;
        this.customerName = customerName;
        this.phoneNo = phoneNo;
        this.deviceName = deviceName;
        this.deviceProblem = deviceProblem;
        this.price = price;
        this.amount = amount;
        this.due = due;
        this.state = state;
        this.delete = delete;
    }

    public String getRepairNo() {
        return repairNo;
    }

    public void setRepairNo(String repairNo) {
        this.repairNo = repairNo;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceProblem() {
        return deviceProblem;
    }

    public void setDeviceProblem(String deviceProblem) {
        this.deviceProblem = deviceProblem;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getDue() {
        return due;
    }

    public void setDue(double due) {
        this.due = due;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }



    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }


}
