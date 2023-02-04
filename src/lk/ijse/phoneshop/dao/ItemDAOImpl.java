package lk.ijse.phoneshop.dao;

import lk.ijse.phoneshop.to.Item;
import lk.ijse.phoneshop.util.CrudUtil;

import java.sql.SQLException;

public class ItemDAOImpl {
    public static boolean itemAdd(Item item) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO item VALUES (?,?,?,?,?,?,?,?)";
        return CrudUtil.execute(sql,item.getItemCode(),item.getBrand(),item.getModalNo(),item.getName(),item.getPrice(),
                item.getWarranty(),item.getQty(),item.getCategory());
    }
}
