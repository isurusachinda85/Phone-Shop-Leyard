package lk.ijse.phoneshop.bo;

import lk.ijse.phoneshop.dto.Customer;
import lk.ijse.phoneshop.dto.Item;
import lk.ijse.phoneshop.dto.Repair;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RepairBO {
    boolean saveRepair(Repair repair) throws SQLException, ClassNotFoundException ;

    ArrayList<Repair> getAllRepair() throws SQLException, ClassNotFoundException ;

    boolean deleteRepair(String rid) throws SQLException, ClassNotFoundException ;

    ArrayList<Item> getAllItem() throws SQLException, ClassNotFoundException ;

    ArrayList<Customer> getAllCustomer() throws SQLException, ClassNotFoundException ;

    Customer searchCustomer(String id) throws SQLException, ClassNotFoundException ;

    Item searchItem(String code) throws SQLException, ClassNotFoundException ;

    Repair searchRepair(String id) throws SQLException, ClassNotFoundException ;

    boolean updateRepair(Repair repair) throws SQLException, ClassNotFoundException ;

    String getNextRepairId() throws SQLException, ClassNotFoundException ;

}
