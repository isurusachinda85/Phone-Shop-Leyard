package lk.ijse.phoneshop.bo;

import lk.ijse.phoneshop.db.DBConnection;
import lk.ijse.phoneshop.dto.Customer;
import lk.ijse.phoneshop.dto.Item;
import lk.ijse.phoneshop.dto.Order;
import lk.ijse.phoneshop.dto.PlaceOrder;
import lk.ijse.phoneshop.model.OrderDetailM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public interface PlaceOrderBO {
    boolean placeOrder(PlaceOrder placeOrder) throws SQLException, ClassNotFoundException;

    Customer searchCustomer(String id) throws SQLException, ClassNotFoundException ;

    Item searchItem(String code) throws SQLException, ClassNotFoundException;

    String getNextOrderId() throws SQLException, ClassNotFoundException;

    ArrayList<Customer> loadCustomerId() throws SQLException, ClassNotFoundException;

    ArrayList<Item> loadItemCode() throws SQLException, ClassNotFoundException;
}
