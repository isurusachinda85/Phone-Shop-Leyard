package lk.ijse.phoneshop.model;

import lk.ijse.phoneshop.to.Payment;
import lk.ijse.phoneshop.dao.SQLUtil;

import java.sql.SQLException;

public class PaymentM {

    public static boolean savePayment(Payment payment) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO payment(amount,paymentType,paymentDate,paymentTime,billNo,cusId)values(?,?,?,?,?,?)";
        return SQLUtil.execute(sql,payment.getAmount(),payment.getPaymentDate(),payment.getPaymentTime(),payment.getBillNo(),payment.getCustomerId());
    }
}
