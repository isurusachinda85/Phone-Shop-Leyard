package lk.ijse.phoneshop.entity;

public class Repair {
    private String repId;
    private String customerName;
    private int phoneNo;
    private String deviceName;
    private String problem;
    private double repairPrice;
    private double amount;
    private double due;
    private String state;
    private String cusId;
    private String date;

    public Repair(String repId, String customerName, int phoneNo, String deviceName, String problem, double repairPrice, double amount, double due, String state, String cusId, String date) {
        this.repId = repId;
        this.customerName = customerName;
        this.phoneNo = phoneNo;
        this.deviceName = deviceName;
        this.problem = problem;
        this.repairPrice = repairPrice;
        this.amount = amount;
        this.due = due;
        this.state = state;
        this.cusId = cusId;
        this.date = date;
    }

    public String getRepId() {
        return repId;
    }

    public void setRepId(String repId) {
        this.repId = repId;
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

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public double getRepairPrice() {
        return repairPrice;
    }

    public void setRepairPrice(double repairPrice) {
        this.repairPrice = repairPrice;
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

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
