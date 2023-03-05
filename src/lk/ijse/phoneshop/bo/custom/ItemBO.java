package lk.ijse.phoneshop.bo.custom;

import lk.ijse.phoneshop.bo.SuperBO;
import lk.ijse.phoneshop.dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException ;

    boolean deleteItem(String code) throws SQLException, ClassNotFoundException ;

    ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException ;

    ArrayList<ItemDTO> getAllAccessories() throws SQLException, ClassNotFoundException;

    ArrayList<ItemDTO> getAllParts() throws SQLException, ClassNotFoundException;

    ArrayList<ItemDTO> getAllPhone() throws SQLException, ClassNotFoundException;
}
