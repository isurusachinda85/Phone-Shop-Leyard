package lk.ijse.phoneshop.bo.custom.impl;

import lk.ijse.phoneshop.bo.custom.ItemBO;
import lk.ijse.phoneshop.dao.DAOFactory;
import lk.ijse.phoneshop.dao.custom.ItemDAO;
import lk.ijse.phoneshop.dao.custom.impl.ItemDAOImpl;
import lk.ijse.phoneshop.dto.EmployeeDTO;
import lk.ijse.phoneshop.dto.ItemDTO;
import lk.ijse.phoneshop.entity.Employee;
import lk.ijse.phoneshop.entity.Item;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOType.ITEM);

    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(dto.getItemCode(), dto.getBrand(), dto.getModalNo(), dto.getName(), dto.getPrice(), dto.getWarranty(), dto.getQty(), dto.getCategory()));
    }

    @Override
    public ArrayList<ItemDTO> getAllAccessories() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> allAccessories = new ArrayList<>();
        ArrayList<Item> all = itemDAO.loadAccessories();
        for (Item item : all) {
            allAccessories.add(new ItemDTO(item.getItemCode(), item.getBrand(), item.getModalNo(), item.getItemName(), item.getPrice(), item.getWarranty(), item.getQty(), item.getCategory()));
        }
        return allAccessories;
    }

    @Override
    public ArrayList<ItemDTO> getAllParts() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> allParts = new ArrayList<>();
        ArrayList<Item> all = itemDAO.loadParts();
        for (Item item : all) {
            allParts.add(new ItemDTO(item.getItemCode(), item.getBrand(), item.getModalNo(), item.getItemName(), item.getPrice(), item.getWarranty(), item.getQty(), item.getCategory()));
        }
        return allParts;
    }

    @Override
    public ArrayList<ItemDTO> getAllPhone() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> allPhone = new ArrayList<>();
        ArrayList<Item> all = itemDAO.loadPhone();
        for (Item item : all) {
            allPhone.add(new ItemDTO(item.getItemCode(), item.getBrand(), item.getModalNo(), item.getItemName(), item.getPrice(), item.getWarranty(), item.getQty(), item.getCategory()));
        }
        return allPhone;
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code);
    }

    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.search(code);
        return new ItemDTO(item.getItemCode(), item.getBrand(), item.getModalNo(), item.getItemName(), item.getPrice(), item.getWarranty(), item.getQty(), item.getCategory());

    }
}
