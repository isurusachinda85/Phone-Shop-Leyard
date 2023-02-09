package lk.ijse.phoneshop.dao;

import lk.ijse.phoneshop.dto.CartDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl {
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
        return SQLUtil.execute(sql, cartDetail.getOrderId(), cartDetail.getCode(), cartDetail.getItemName(),cartDetail.getQty(), cartDetail.getUnitPrice());
    }
}
