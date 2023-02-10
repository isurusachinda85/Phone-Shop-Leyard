package lk.ijse.phoneshop.bo;

import lk.ijse.phoneshop.dao.SQLUtil;
import lk.ijse.phoneshop.dao.custom.ItemDAO;
import lk.ijse.phoneshop.dao.custom.impl.ItemDAOImpl;
import lk.ijse.phoneshop.dto.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockManageBOImpl {
    private ItemDAO itemDAO = new ItemDAOImpl();

    public ArrayList<Item> getAllItem() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }
}
