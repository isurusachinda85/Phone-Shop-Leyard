package lk.ijse.phoneshop.dao;

import lk.ijse.phoneshop.db.DBConnection;
import lk.ijse.phoneshop.to.Customer;
import lk.ijse.phoneshop.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerDAOImpl {
    public boolean saveCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "INSERT Into customer values (?,?,?,?,?)";
        return CrudUtil.execute(sql,customer.getId(),customer.getName(),customer.getAddress(),customer.getPhoneNo(),customer.getEmail());
    }
    public ArrayList<Customer> getAllCustomer() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet resultSet = stm.executeQuery("SELECT * From Customer");
        ArrayList<Customer> allCustomer = new ArrayList<>();

        while (resultSet.next()){
            allCustomer.add(new Customer(
                    resultSet.getString("cusId"),
                    resultSet.getString("name"),
                    resultSet.getString("address"),
                    resultSet.getString("phoneNo"),
                    resultSet.getString("email")));
        }

        return allCustomer;
    }
    public boolean deleteCustomer(String  id) throws SQLException, ClassNotFoundException {
        String sql = "Delete From Customer where cusId=?";
        return CrudUtil.execute(sql,id);
    }
    public  Customer searchCustomer(String id) throws SQLException, ClassNotFoundException {
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
    public boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "Update Customer set name=?,address=?,phoneNo=?,email=? where cusId=?";
        return CrudUtil.execute(sql,customer.getName(),customer.getAddress(),customer.getPhoneNo(),customer.getEmail(),customer.getId());
    }
    public  String getNextCustomerId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT cusId FROM customer ORDER BY cusId DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()){
            return getNextCustomerId(resultSet.getString(1));
        }
        return getNextCustomerId(resultSet.getString(null));
    }
    public  String getNextCustomerId(String cusId){
        if (cusId!=null){
            String[]split = cusId.split("C0");
            int id = Integer.parseInt(split[1]);
            id+=1;
            return "C00"+id;

        }
        return "C001";
    }
    public ResultSet loadCustomerId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT cusId from customer order by cusId asc";
        return CrudUtil.execute(sql);
    }
}
