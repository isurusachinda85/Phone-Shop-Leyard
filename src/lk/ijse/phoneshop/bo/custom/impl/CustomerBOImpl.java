package lk.ijse.phoneshop.bo.custom.impl;

import lk.ijse.phoneshop.bo.custom.CustomerBO;
import lk.ijse.phoneshop.dao.DAOFactory;
import lk.ijse.phoneshop.dao.custom.CustomerDAO;
import lk.ijse.phoneshop.dto.AttendanceDTO;
import lk.ijse.phoneshop.dto.CustomerDTO;
import lk.ijse.phoneshop.entity.Attendance;
import lk.ijse.phoneshop.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    private CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOType.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(dto.getId(), dto.getName(),dto.getAddress(),dto.getPhoneNo(),dto.getEmail()));
    }
    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomer = new ArrayList<>();
        ArrayList<Customer> all = customerDAO.getAll();
        for (Customer customer : all) {
            allCustomer.add(new CustomerDTO(customer.getCusId(),customer.getName(),customer.getAddress(),customer.getPhoneNo(),customer.getEmail()));
        }
        return allCustomer;
    }
    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }
    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getId(),dto.getName(),dto.getAddress(),dto.getPhoneNo(),dto.getEmail()));
    }
    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.search(id);
        return new CustomerDTO(customer.getCusId(),customer.getName(),customer.getAddress(),customer.getPhoneNo(),customer.getEmail());
    }
    @Override
    public String getNextCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.getNextId();
    }

}
