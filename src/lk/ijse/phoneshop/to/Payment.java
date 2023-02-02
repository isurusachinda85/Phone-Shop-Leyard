package lk.ijse.phoneshop.to;

import java.time.LocalDate;
import java.time.LocalTime;

public class Payment {
    private double amount;
    private String paymentType;
    private LocalDate paymentDate;
    private LocalTime paymentTime;
    private String billNo;
    private String customerId;

    public Payment(double amount, String paymentType, LocalDate paymentDate, LocalTime paymentTime, String billNo, String customerId) {
        this.amount = amount;
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
        this.paymentTime = paymentTime;
        this.billNo = billNo;
        this.customerId = customerId;
    }

    public Payment() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(LocalTime paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "amount=" + amount +
                ", paymentType='" + paymentType + '\'' +
                ", paymentDate=" + paymentDate +
                ", paymentTime=" + paymentTime +
                ", billNo='" + billNo + '\'' +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}
