package lk.ijse.phoneshop.bo;

import lk.ijse.phoneshop.dao.SQLUtil;
import lk.ijse.phoneshop.dao.custom.CustomerDAO;
import lk.ijse.phoneshop.dao.custom.ItemDAO;
import lk.ijse.phoneshop.dao.custom.RepairDAO;
import lk.ijse.phoneshop.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.phoneshop.dao.custom.impl.ItemDAOImpl;
import lk.ijse.phoneshop.dao.custom.impl.RepairDAOImpl;
import lk.ijse.phoneshop.dto.Customer;
import lk.ijse.phoneshop.dto.Item;
import lk.ijse.phoneshop.dto.Repair;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepairBOImpl {
    private RepairDAO repairDAO = new RepairDAOImpl();
    private ItemDAO itemDAO = new ItemDAOImpl();
    private CustomerDAO customerDAO = new CustomerDAOImpl();

    public boolean saveRepair(Repair repair) throws SQLException, ClassNotFoundException {
        return repairDAO.save(repair);
    }
    public ArrayList<Repair> getAllRepair() throws SQLException, ClassNotFoundException {
        return repairDAO.getAll();
    }
    public boolean deleteRepair(String  rid) throws SQLException, ClassNotFoundException {
        return repairDAO.delete(rid);
    }
    public ArrayList<Item> getAllItem() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }
    public ArrayList<Customer> getAllCustomer() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }
    public Customer searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.search(id);
    }
    public  Item searchItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.search(code);
    }
    public Repair searchRepair(String id) throws SQLException, ClassNotFoundException {
        return repairDAO.search(id);
    }
    public boolean updateRepair(Repair repair) throws SQLException, ClassNotFoundException {
        return repairDAO.update(repair);
    }
    public  String getNextRepairId() throws SQLException, ClassNotFoundException {
        return repairDAO.getNextId();
    }
}
