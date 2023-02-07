package lk.ijse.phoneshop.model;

import lk.ijse.phoneshop.dto.CartDetail;
import lk.ijse.phoneshop.dto.Item;
import lk.ijse.phoneshop.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemM {
    public static boolean itemAdd(Item item) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO item VALUES (?,?,?,?,?,?,?,?)";
        return SQLUtil.execute(sql,item.getItemCode(),item.getBrand(),item.getModalNo(),item.getName(),item.getPrice(),
                item.getWarranty(),item.getQty(),item.getCategory());
    }
    public static ResultSet loadAllPhone() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * from item where itemCode like 'P%'";
        return SQLUtil.execute(sql);
    }
    public static ResultSet loadAccessories() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * from item where itemCode like 'A%'";
        return SQLUtil.execute(sql);
    }
    public static ResultSet loadParts() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * from item where itemCode like 'R%'";
        return SQLUtil.execute(sql);
    }

    public static ResultSet loadItemCode() throws SQLException, ClassNotFoundException {
        String sql = "SELECT itemCode from item";
        return SQLUtil.execute(sql);
    }
    public static Item searchItem(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM item WHERE itemCode  = ?";
        ResultSet resultSet = SQLUtil.execute(sql, id);
        while (resultSet.next()){
            return new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    resultSet.getString(6),
                    resultSet.getInt(7),
                    resultSet.getString(8));
        }
        return null;
    }
    public static boolean updateQty(ArrayList<CartDetail> cartDetails) throws SQLException, ClassNotFoundException {
        for (CartDetail cartDetail : cartDetails) {
            if (!updateQty(new Item(cartDetail.getCode(),cartDetail.getItemName(),cartDetail.getUnitPrice(),cartDetail.getCategory()))) {
                return false;
            }
        }
        return true;
    }
    public static boolean updateQty(Item item) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Item SET qty = qty - ? WHERE itemCode = ?";
        return SQLUtil.execute(sql,item.getQty(),item.getItemCode());
    }
}
