package lk.ijse.phoneshop.bo.custom.impl;

import lk.ijse.phoneshop.bo.custom.RepairBO;
import lk.ijse.phoneshop.dao.DAOFactory;
import lk.ijse.phoneshop.dao.custom.CustomerDAO;
import lk.ijse.phoneshop.dao.custom.ItemDAO;
import lk.ijse.phoneshop.dao.custom.RepairDAO;
import lk.ijse.phoneshop.dto.CustomerDTO;
import lk.ijse.phoneshop.dto.ItemDTO;
import lk.ijse.phoneshop.dto.RepairDTO;
import lk.ijse.phoneshop.entity.Customer;
import lk.ijse.phoneshop.entity.Item;
import lk.ijse.phoneshop.entity.Repair;

import java.sql.SQLException;
import java.util.ArrayList;

public class RepairBOImpl implements RepairBO {
    private RepairDAO repairDAO = (RepairDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOType.REPAIR);
    private ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOType.ITEM);
    private CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOType.CUSTOMER);

    @Override
    public boolean saveRepair(RepairDTO dto) throws SQLException, ClassNotFoundException {
        return repairDAO.save(new Repair(dto.getRepairNo(), dto.getCustomerName(), dto.getPhoneNo(), dto.getDeviceName(), dto.getDeviceProblem(), dto.getPrice(), dto.getAmount(), dto.getDue(), dto.getState(), dto.getDate(), dto.getCustomerId()));

    }

    @Override
    public ArrayList<RepairDTO> getAllRepair() throws SQLException, ClassNotFoundException {
        ArrayList<RepairDTO> allRepair = new ArrayList<>();
        ArrayList<Repair> all = repairDAO.getAll();
        for (Repair repair : all) {
            allRepair.add(new RepairDTO(repair.getRepId(), repair.getCustomerName(), repair.getPhoneNo(), repair.getDeviceName(), repair.getProblem(), repair.getRepairPrice(), repair.getAmount(), repair.getDue(), repair.getState()));
        }
        return allRepair;
    }

    @Override
    public boolean deleteRepair(String rid) throws SQLException, ClassNotFoundException {
        return repairDAO.delete(rid);
    }

    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> allItem = new ArrayList<>();
        ArrayList<Item> all = itemDAO.getAll();
        for (Item item : all) {
            allItem.add(new ItemDTO(item.getItemCode(), item.getBrand(), item.getModalNo(), item.getItemName(), item.getPrice(), item.getWarranty(), item.getQty(), item.getCategory()));
        }
        return allItem;
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomer = new ArrayList<>();
        ArrayList<Customer> all = customerDAO.getAll();
        for (Customer customer : all) {
            allCustomer.add(new CustomerDTO(customer.getCusId(), customer.getName(), customer.getAddress(), customer.getPhoneNo(), customer.getEmail()));
        }
        return allCustomer;
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.search(id);
        return new CustomerDTO(customer.getCusId(),customer.getName(),customer.getAddress(),customer.getPhoneNo(),customer.getEmail());
    }

    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        Item search = itemDAO.search(code);
        return new ItemDTO(search.getItemCode());
    }

    @Override
    public RepairDTO searchRepair(String id) throws SQLException, ClassNotFoundException {
        Repair search = repairDAO.search(id);
        return new RepairDTO(search.getRepId());
    }

    @Override
    public boolean updateRepair(RepairDTO dto) throws SQLException, ClassNotFoundException {
        return repairDAO.update(new Repair(dto.getRepairNo(), dto.getCustomerName(), dto.getPhoneNo(), dto.getDeviceName(), dto.getDeviceProblem(), dto.getPrice(), dto.getAmount(), dto.getDue(), dto.getState(), dto.getDate(), dto.getCustomerId()));
    }

    @Override
    public String getNextRepairId() throws SQLException, ClassNotFoundException {
        return repairDAO.getNextId();
    }
}
