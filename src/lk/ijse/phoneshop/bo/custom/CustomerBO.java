package lk.ijse.phoneshop.bo.custom;

import lk.ijse.phoneshop.bo.SuperBO;
import lk.ijse.phoneshop.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException ;

    ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException ;

    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException ;

    boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException ;

    CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException ;

    String getNextCustomerId() throws SQLException, ClassNotFoundException ;
}
