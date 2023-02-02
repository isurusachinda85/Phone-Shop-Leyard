package lk.ijse.phoneshop.model;

import lk.ijse.phoneshop.db.DBConnection;
import lk.ijse.phoneshop.to.Order;
import lk.ijse.phoneshop.to.Payment;
import lk.ijse.phoneshop.to.PlaceOrder;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class PlaceOrderM {
    public static boolean placeOrder(PlaceOrder placeOrder) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean saveOrder = OrderM.saveOrder(new Order(placeOrder.getOrderId(), LocalDate.now(), LocalTime.now(), placeOrder.getCustomerId()));
            if (saveOrder){
                boolean updateQty = ItemM.updateQty(placeOrder.getOrderDetail());
                if (updateQty){
                    boolean saveOrderDetails = OrderDetailM.saveOrderDetails(placeOrder.getOrderDetail());
                    if (saveOrderDetails){
                        DBConnection.getInstance().getConnection().commit();
                        return true;
                    }
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        } finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }
}
