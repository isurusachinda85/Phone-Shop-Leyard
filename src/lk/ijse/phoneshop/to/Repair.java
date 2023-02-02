package lk.ijse.phoneshop.to;

import java.time.LocalDate;
import java.util.Date;

public class Repair {
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
    private String date;

    public Repair() {
    }

    public Repair(String repairNo, String customerId, String customerName, int phoneNo, String itemCode, String deviceName, String deviceProblem, double price, double amount, double due, String state, String date) {
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
        this.date = date;
    }

    public Repair(String repairNo, String customerName, int phoneNo, String deviceName, String deviceProblem, double price, double amount, double due, String state) {
        this.repairNo = repairNo;
        this.customerName = customerName;
        this.phoneNo = phoneNo;
        this.deviceName = deviceName;
        this.deviceProblem = deviceProblem;
        this.price = price;
        this.amount = amount;
        this.due = due;
        this.state = state;
    }

    public Repair(String repairNo, double amount, double due, String state) {
        this.repairNo = repairNo;
        this.amount = amount;
        this.due = due;
        this.state = state;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Repair{" +
                "repairNo='" + repairNo + '\'' +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", phoneNo=" + phoneNo +
                ", itemCode='" + itemCode + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceProblem='" + deviceProblem + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", due=" + due +
                ", state='" + state + '\'' +
                ", date=" + date +
                '}';
    }
}
