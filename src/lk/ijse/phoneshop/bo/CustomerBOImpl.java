package lk.ijse.phoneshop.bo;

import lk.ijse.phoneshop.dao.SQLUtil;
import lk.ijse.phoneshop.dao.custom.CustomerDAO;
import lk.ijse.phoneshop.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.phoneshop.dto.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl {
    private CustomerDAO customerDAO = new CustomerDAOImpl();

    public boolean saveCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        return customerDAO.save(customer);
    }
    public ArrayList<Customer> getAllCustomer() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }
    public boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        return customerDAO.update(customer);
    }
    public Customer searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.search(id);
    }
    public String getNextCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.getNextId();
    }

}
