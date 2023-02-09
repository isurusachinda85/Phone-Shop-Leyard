package lk.ijse.phoneshop.dao;

import lk.ijse.phoneshop.dto.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements CrudDAO<Order,String> {
    @Override
    public boolean save(Order order) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO orders VALUES(?, ?, ?,?)";
        return SQLUtil.execute(sql, order.getOrderId(), order.getOrderDate(),order.getOrderTime(), order.getCustomerId());
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Order dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1";
        ResultSet resultSet = SQLUtil.execute(sql);
        if (resultSet.next()){
            return getNextId(resultSet.getString(1));
        }
        return getNextId(resultSet.getString(null));
    }

    @Override
    public String getNextId(String orderId) {
        if (orderId!=null){
            String[]split = orderId.split("D0");
            int id = Integer.parseInt(split[1]);
            id+=1;
            return "D0"+id;
        }
        return "O001";
    }
    /*public static boolean saveOrder(Order order) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO orders VALUES(?, ?, ?,?)";
        return SQLUtil.execute(sql, order.getOrderId(), order.getOrderDate(),order.getOrderTime(), order.getCustomerId());
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
    }*/
}
