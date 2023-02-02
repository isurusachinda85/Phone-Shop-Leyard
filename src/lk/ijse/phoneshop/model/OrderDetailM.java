package lk.ijse.phoneshop.model;

import lk.ijse.phoneshop.to.CartDetail;
import lk.ijse.phoneshop.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailM {
    public static boolean saveOrderDetails(ArrayList<CartDetail> cartDetails) throws SQLException, ClassNotFoundException {
        for (CartDetail cartDetail : cartDetails) {
            if (!saveOrderDetail(cartDetail)) {
                return false;
            }
        }
        return true;
    }
    public static boolean saveOrderDetail(CartDetail cartDetail) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO OrderDetail VALUES(?, ?, ?, ?,?)";
        return CrudUtil.execute(sql, cartDetail.getOrderId(), cartDetail.getCode(), cartDetail.getItemName(),cartDetail.getQty(), cartDetail.getUnitPrice());
    }
}
