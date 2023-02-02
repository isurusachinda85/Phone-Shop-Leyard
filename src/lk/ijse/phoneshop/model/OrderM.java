package lk.ijse.phoneshop.model;

import lk.ijse.phoneshop.to.Order;
import lk.ijse.phoneshop.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderM {
    public static boolean saveOrder(Order order) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO orders VALUES(?, ?, ?,?)";
        return CrudUtil.execute(sql, order.getOrderId(), order.getOrderDate(),order.getOrderTime(), order.getCustomerId());
    }
    public static String getNextOrderId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);
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
