package lk.ijse.phoneshop.bo.custom.impl;

import lk.ijse.phoneshop.bo.custom.StockManageBO;
import lk.ijse.phoneshop.dao.custom.ItemDAO;
import lk.ijse.phoneshop.dao.custom.impl.ItemDAOImpl;
import lk.ijse.phoneshop.dto.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class StockManageBOImpl implements StockManageBO {
    private ItemDAO itemDAO = new ItemDAOImpl();

    @Override
    public ArrayList<Item> getAllItem() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }
}
