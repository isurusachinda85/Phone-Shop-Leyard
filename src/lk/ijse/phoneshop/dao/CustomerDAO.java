package lk.ijse.phoneshop.dao;

import lk.ijse.phoneshop.to.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO {
    boolean saveCustomer(Customer customer) throws SQLException, ClassNotFoundException;
    ArrayList<Customer> getAllCustomer() throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    Customer searchCustomer(String id) throws SQLException, ClassNotFoundException;
    boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException;
    String getNextCustomerId() throws SQLException, ClassNotFoundException;
    String getNextCustomerId(String cusId);
    ResultSet loadCustomerId() throws SQLException, ClassNotFoundException;
}
