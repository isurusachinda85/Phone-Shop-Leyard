package lk.ijse.phoneshop.bo.custom.impl;

import lk.ijse.phoneshop.bo.custom.RepairBO;
import lk.ijse.phoneshop.dao.custom.CustomerDAO;
import lk.ijse.phoneshop.dao.custom.ItemDAO;
import lk.ijse.phoneshop.dao.custom.RepairDAO;
import lk.ijse.phoneshop.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.phoneshop.dao.custom.impl.ItemDAOImpl;
import lk.ijse.phoneshop.dao.custom.impl.RepairDAOImpl;
import lk.ijse.phoneshop.dto.Customer;
import lk.ijse.phoneshop.dto.Item;
import lk.ijse.phoneshop.dto.Repair;

import java.sql.SQLException;
import java.util.ArrayList;

public class RepairBOImpl implements RepairBO {
    private RepairDAO repairDAO = new RepairDAOImpl();
    private ItemDAO itemDAO = new ItemDAOImpl();
    private CustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
    public boolean saveRepair(Repair repair) throws SQLException, ClassNotFoundException {
        return repairDAO.save(repair);
    }
    @Override
    public ArrayList<Repair> getAllRepair() throws SQLException, ClassNotFoundException {
        return repairDAO.getAll();
    }
    @Override
    public boolean deleteRepair(String  rid) throws SQLException, ClassNotFoundException {
        return repairDAO.delete(rid);
    }
    @Override
    public ArrayList<Item> getAllItem() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }
    @Override
    public ArrayList<Customer> getAllCustomer() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }
    @Override
    public Customer searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.search(id);
    }
    @Override
    public  Item searchItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.search(code);
    }
    @Override
    public Repair searchRepair(String id) throws SQLException, ClassNotFoundException {
        return repairDAO.search(id);
    }
    @Override
    public boolean updateRepair(Repair repair) throws SQLException, ClassNotFoundException {
        return repairDAO.update(repair);
    }
    @Override
    public  String getNextRepairId() throws SQLException, ClassNotFoundException {
        return repairDAO.getNextId();
    }
}
