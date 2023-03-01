package lk.ijse.phoneshop.model;

import lk.ijse.phoneshop.dto.CartDetail;
import lk.ijse.phoneshop.dto.ItemDTO;
import lk.ijse.phoneshop.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemM {
    public static boolean itemAdd(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO item VALUES (?,?,?,?,?,?,?,?)";
        return SQLUtil.execute(sql, itemDTO.getItemCode(), itemDTO.getBrand(), itemDTO.getModalNo(), itemDTO.getName(), itemDTO.getPrice(),
                itemDTO.getWarranty(), itemDTO.getQty(), itemDTO.getCategory());
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
    public static ItemDTO searchItem(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM item WHERE itemCode  = ?";
        ResultSet resultSet = SQLUtil.execute(sql, id);
        while (resultSet.next()){
            return new ItemDTO(
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
            if (!updateQty(new ItemDTO(cartDetail.getCode(),cartDetail.getItemName(),cartDetail.getUnitPrice(),cartDetail.getCategory()))) {
                return false;
            }
        }
        return true;
    }
    public static boolean updateQty(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Item SET qty = qty - ? WHERE itemCode = ?";
        return SQLUtil.execute(sql, itemDTO.getQty(), itemDTO.getItemCode());
    }
}
