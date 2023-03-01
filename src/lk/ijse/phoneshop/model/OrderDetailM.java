package lk.ijse.phoneshop.model;

import lk.ijse.phoneshop.dto.OrderDetailDTO;
import lk.ijse.phoneshop.dao.SQLUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailM {
    public static boolean saveOrderDetails(ArrayList<OrderDetailDTO> orderDetailDTOS) throws SQLException, ClassNotFoundException {
        for (OrderDetailDTO orderDetailDTO : orderDetailDTOS) {
            if (!saveOrderDetail(orderDetailDTO)) {
                return false;
            }
        }
        return true;
    }
    public static boolean saveOrderDetail(OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO OrderDetail VALUES(?, ?, ?, ?,?)";
        return SQLUtil.execute(sql, orderDetailDTO.getOrderId(), orderDetailDTO.getCode(), orderDetailDTO.getItemName(), orderDetailDTO.getQty(), orderDetailDTO.getUnitPrice());
    }
}
