package lk.ijse.phoneshop.bo.custom;

import lk.ijse.phoneshop.dto.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO {
    boolean saveCustomer(Customer customer) throws SQLException, ClassNotFoundException ;

    ArrayList<Customer> getAllCustomer() throws SQLException, ClassNotFoundException ;

    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException ;

    boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException ;

    Customer searchCustomer(String id) throws SQLException, ClassNotFoundException ;

    String getNextCustomerId() throws SQLException, ClassNotFoundException ;
}
