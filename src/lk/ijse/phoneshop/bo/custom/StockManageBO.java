package lk.ijse.phoneshop.bo.custom;

import lk.ijse.phoneshop.dto.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockManageBO {
    ArrayList<Item> getAllItem() throws SQLException, ClassNotFoundException ;
}
