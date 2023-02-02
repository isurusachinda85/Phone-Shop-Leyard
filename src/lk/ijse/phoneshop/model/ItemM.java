package lk.ijse.phoneshop.model;

import lk.ijse.phoneshop.to.CartDetail;
import lk.ijse.phoneshop.to.Item;
import lk.ijse.phoneshop.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemM {
    public static boolean itemAdd(Item item) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO item VALUES (?,?,?,?,?,?,?,?)";
        return CrudUtil.execute(sql,item.getItemCode(),item.getBrand(),item.getModalNo(),item.getName(),item.getPrice(),
                item.getWarranty(),item.getQty(),item.getCategory());
    }
    public static ResultSet loadAllPhone() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * from item where itemCode like 'P%'";
        return CrudUtil.execute(sql);
    }
    public static ResultSet loadAccessories() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * from item where itemCode like 'A%'";
        return CrudUtil.execute(sql);
    }
    public static ResultSet loadParts() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * from item where itemCode like 'R%'";
        return CrudUtil.execute(sql);
    }
    /*public static String getNextPhoneId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT itemCode FROM item ORDER BY itemCode like 'P%'DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()){
            return getNextPhoneId(resultSet.getString(1));
        }
        return getNextPhoneId(resultSet.getString(null));
    }
    public static String getNextPhoneId(String itemCode){
        if (itemCode!=null){
            String[]split = itemCode.split("P0");
            int id = Integer.parseInt(split[1]);
            id+=1;
            return "P00"+id;

        }
        return "P001";

    }
    public static String getNextAccessoriesCode() throws SQLException, ClassNotFoundException {
        String sql = "SELECT itemCode FROM item ORDER BY itemCode like 'A%'DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()){
            return getNextAccessoriesCode(resultSet.getString(1));
        }
        return getNextAccessoriesCode(resultSet.getString(null));
    }
    public static String getNextAccessoriesCode(String itemCode){
        if (itemCode!=null){
            String[]split = itemCode.split("A0");
            int id = Integer.parseInt(split[1]);
            id+=1;
            return "A00"+id;

        }
        return "A001";

    }*/
    public static ResultSet loadItemCode() throws SQLException, ClassNotFoundException {
        String sql = "SELECT itemCode from item";
        return CrudUtil.execute(sql);
    }
    public static Item searchItem(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM item WHERE itemCode  = ?";
        ResultSet resultSet = CrudUtil.execute(sql, id);
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
        return CrudUtil.execute(sql,item.getQty(),item.getItemCode());
    }
}
