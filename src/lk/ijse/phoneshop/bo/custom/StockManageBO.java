package lk.ijse.phoneshop.bo.custom;

import lk.ijse.phoneshop.bo.SuperBO;
import lk.ijse.phoneshop.dto.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockManageBO extends SuperBO {
    ArrayList<Item> getAllItem() throws SQLException, ClassNotFoundException ;
}
