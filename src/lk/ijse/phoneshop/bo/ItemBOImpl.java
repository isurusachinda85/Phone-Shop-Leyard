package lk.ijse.phoneshop.bo;

import lk.ijse.phoneshop.dao.SQLUtil;
import lk.ijse.phoneshop.dao.custom.ItemDAO;
import lk.ijse.phoneshop.dao.custom.impl.ItemDAOImpl;
import lk.ijse.phoneshop.dto.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl {
    private ItemDAO itemDAO = new ItemDAOImpl();

    public boolean saveItem(Item item) throws SQLException, ClassNotFoundException {
        return itemDAO.save(item);
    }
    public ArrayList<Item> getAllItem() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }
    public boolean deleteItem(String  code) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code);
    }
    public  Item searchItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.search(code);
    }

}
