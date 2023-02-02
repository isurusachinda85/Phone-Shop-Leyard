package lk.ijse.phoneshop.model;

import lk.ijse.phoneshop.db.DBConnection;
import lk.ijse.phoneshop.to.Customer;
import lk.ijse.phoneshop.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerM {
    public static boolean saveCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "INSERT Into customer values (?,?,?,?,?)";
        return CrudUtil.execute(sql,customer.getId(),customer.getName(),customer.getAddress(),customer.getPhoneNo(),customer.getEmail());
    }
    public static ResultSet getAllCustomer() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * From Customer";
        return CrudUtil.execute(sql);
    }
    public static String getNextCustomerId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT cusId FROM customer ORDER BY cusId DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()){
            return getNextCustomerId(resultSet.getString(1));
        }
        return getNextCustomerId(resultSet.getString(null));
    }
    public static String getNextCustomerId(String cusId){
        if (cusId!=null){
            String[]split = cusId.split("C0");
            int id = Integer.parseInt(split[1]);
            id+=1;
            return "C00"+id;

        }
            return "C001";

    }
    public static ResultSet loadCustomerId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT cusId from customer order by cusId asc";
        return CrudUtil.execute(sql);
    }
    public static Customer searchCustomer(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM customer WHERE cusId = ?";
        ResultSet resultSet = CrudUtil.execute(sql, id);
        while (resultSet.next()){
            return new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5));
        }
        return null;
    }
    public static boolean deleteCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "Delete From Customer where cusId=?";
        return CrudUtil.execute(sql,customer.getId());
    }
    public static boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "Update Customer set name=?,address=?,phoneNo=?,email=? where cusId=?";
        return CrudUtil.execute(sql,customer.getName(),customer.getAddress(),customer.getPhoneNo(),customer.getEmail(),customer.getId());
    }
}
