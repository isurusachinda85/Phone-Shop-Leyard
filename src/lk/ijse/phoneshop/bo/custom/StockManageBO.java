package lk.ijse.phoneshop.bo.custom;

import lk.ijse.phoneshop.bo.SuperBO;
import lk.ijse.phoneshop.dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockManageBO extends SuperBO {
    ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException ;
}
