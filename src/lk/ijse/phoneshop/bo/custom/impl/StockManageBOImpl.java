package lk.ijse.phoneshop.bo.custom.impl;

import lk.ijse.phoneshop.bo.custom.StockManageBO;
import lk.ijse.phoneshop.dao.DAOFactory;
import lk.ijse.phoneshop.dao.custom.ItemDAO;
import lk.ijse.phoneshop.dto.ItemDTO;
import lk.ijse.phoneshop.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class StockManageBOImpl implements StockManageBO {
    private ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOType.ITEM);

    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> allItem = new ArrayList<>();
        ArrayList<Item> all = itemDAO.getAll();
        for (Item item : all) {
            allItem.add(new ItemDTO(item.getItemCode(), item.getBrand(), item.getModalNo(), item.getItemName(), item.getPrice(), item.getWarranty(), item.getQty(), item.getCategory()));
        }
        return allItem;
    }
}
