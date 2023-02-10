package lk.ijse.phoneshop.bo.custom.impl;

import lk.ijse.phoneshop.bo.custom.ItemBO;
import lk.ijse.phoneshop.dao.custom.ItemDAO;
import lk.ijse.phoneshop.dao.custom.impl.ItemDAOImpl;
import lk.ijse.phoneshop.dto.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    private ItemDAO itemDAO = new ItemDAOImpl();

    @Override
    public boolean saveItem(Item item) throws SQLException, ClassNotFoundException {
        return itemDAO.save(item);
    }
    @Override
    public ArrayList<Item> getAllItem() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }
    @Override
    public boolean deleteItem(String  code) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code);
    }
    @Override
    public  Item searchItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.search(code);
    }

}
