package lk.ijse.phoneshop.model;

import lk.ijse.phoneshop.dto.OrderDTO;
import lk.ijse.phoneshop.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderM {
    public static boolean saveOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO orders VALUES(?, ?, ?,?)";
        return SQLUtil.execute(sql, orderDTO.getOrderId(), orderDTO.getOrderDate(), orderDTO.getOrderTime(), orderDTO.getCustomerId());
    }
    public static String getNextOrderId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1";
        ResultSet resultSet = SQLUtil.execute(sql);
        if (resultSet.next()){
            return getNextOrderId(resultSet.getString(1));
        }
        return getNextOrderId(resultSet.getString(null));
    }
    public static String getNextOrderId(String orderId){
        if (orderId!=null){
            String[]split = orderId.split("D0");
            int id = Integer.parseInt(split[1]);
            id+=1;
            return "D0"+id;
        }
        return "O001";
    }
}
