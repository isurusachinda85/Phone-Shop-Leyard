package lk.ijse.phoneshop.bo.custom.impl;

import lk.ijse.phoneshop.bo.custom.ItemBO;
import lk.ijse.phoneshop.dao.DAOFactory;
import lk.ijse.phoneshop.dao.custom.ItemDAO;
import lk.ijse.phoneshop.dto.EmployeeDTO;
import lk.ijse.phoneshop.dto.ItemDTO;
import lk.ijse.phoneshop.entity.Employee;
import lk.ijse.phoneshop.entity.Item;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    private ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOType.ITEM);

    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(dto.getItemCode(),dto.getBrand(),dto.getModalNo(),dto.getCategory(),dto.getPrice(),dto.getBrand(),dto.getQty(),dto.getCategory()));
    }
    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> allItem = new ArrayList<>();
        ArrayList<Item> all = itemDAO.getAll();
        for (Item item: all) {
            //allItem.add(new EmployeeDTO(item.getItemCode(),item.getBrand(),item.getModalNo(),item.getBrand(),item.getCategory(),item.getPrice(),item.getQty(),item.getCategory(),item.getCategory()));
        }
        return allItem;
    }
    @Override
    public boolean deleteItem(String  code) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code);
    }
    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {

    }

}
