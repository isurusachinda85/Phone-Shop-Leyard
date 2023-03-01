package lk.ijse.phoneshop.bo.custom;

import lk.ijse.phoneshop.bo.SuperBO;
import lk.ijse.phoneshop.dto.CustomerDTO;
import lk.ijse.phoneshop.dto.ItemDTO;
import lk.ijse.phoneshop.dto.PlaceOrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PlaceOrderBO extends SuperBO {
    boolean placeOrder(PlaceOrderDTO placeOrderDTO) throws SQLException, ClassNotFoundException;

    CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException ;

    ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException;

    String getNextOrderId() throws SQLException, ClassNotFoundException;

    ArrayList<CustomerDTO> loadCustomerId() throws SQLException, ClassNotFoundException;

    ArrayList<ItemDTO> loadItemCode() throws SQLException, ClassNotFoundException;
}
