package lk.ijse.phoneshop.bo;

import lk.ijse.phoneshop.dto.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO {
    boolean saveItem(Item item) throws SQLException, ClassNotFoundException ;

    ArrayList<Item> getAllItem() throws SQLException, ClassNotFoundException;

    boolean deleteItem(String code) throws SQLException, ClassNotFoundException ;

    Item searchItem(String code) throws SQLException, ClassNotFoundException ;
}
