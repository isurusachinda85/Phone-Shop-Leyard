package lk.ijse.phoneshop.bo.custom.impl;

import lk.ijse.phoneshop.bo.custom.RepairBO;
import lk.ijse.phoneshop.dao.DAOFactory;
import lk.ijse.phoneshop.dao.custom.CustomerDAO;
import lk.ijse.phoneshop.dao.custom.ItemDAO;
import lk.ijse.phoneshop.dao.custom.RepairDAO;
import lk.ijse.phoneshop.dto.CustomerDTO;
import lk.ijse.phoneshop.dto.ItemDTO;
import lk.ijse.phoneshop.dto.RepairDTO;
import lk.ijse.phoneshop.entity.Repair;

import java.sql.SQLException;
import java.util.ArrayList;

public class RepairBOImpl implements RepairBO {
    private RepairDAO repairDAO = (RepairDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOType.REPAIR);
    private ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOType.ITEM);
    private CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOType.CUSTOMER);

    @Override
    public boolean saveRepair(RepairDTO repairDTO) throws SQLException, ClassNotFoundException {
        return repairDAO.save(repairDTO);

    }
    @Override
    public ArrayList<RepairDTO> getAllRepair() throws SQLException, ClassNotFoundException {

    }
    @Override
    public boolean deleteRepair(String  rid) throws SQLException, ClassNotFoundException {
        return repairDAO.delete(rid);
    }
    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {

    }
    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {

    }
    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {

    }
    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {

    }
    @Override
    public RepairDTO searchRepair(String id) throws SQLException, ClassNotFoundException {

    }
    @Override
    public boolean updateRepair(RepairDTO repairDTO) throws SQLException, ClassNotFoundException {

    }
    @Override
    public  String getNextRepairId() throws SQLException, ClassNotFoundException {
        return repairDAO.getNextId();
    }
}
