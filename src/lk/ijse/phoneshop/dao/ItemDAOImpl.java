package lk.ijse.phoneshop.dao;

import lk.ijse.phoneshop.db.DBConnection;
import lk.ijse.phoneshop.to.Customer;
import lk.ijse.phoneshop.to.Item;
import lk.ijse.phoneshop.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ItemDAOImpl {
    public boolean itemAdd(Item item) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO item VALUES (?,?,?,?,?,?,?,?)";
        return CrudUtil.execute(sql,item.getItemCode(),item.getBrand(),item.getModalNo(),item.getName(),item.getPrice(),
                item.getWarranty(),item.getQty(),item.getCategory());
    }
    public ArrayList<Item> loadAllPhone() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet resultSet = stm.executeQuery("SELECT * from item where itemCode like 'P%'");
        ArrayList<Item> allPhone = new ArrayList<>();
        while (resultSet.next()){
            allPhone.add(new Item(
                    resultSet.getString("itemCode"),
                    resultSet.getString("brand"),
                    resultSet.getString("modalNo"),
                    resultSet.getString("itemName"),
                    resultSet.getDouble("price"),
                    resultSet.getString("warranty"),
                    resultSet.getInt("qty"),
                    resultSet.getString("category")));
        }
        return allPhone;
    }
    public ArrayList<Item> loadAccessories() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet resultSet = stm.executeQuery("SELECT * from item where itemCode like 'A%'");
        ArrayList<Item> allAccessories = new ArrayList<>();
        while (resultSet.next()){
            allAccessories.add(new Item(
                    resultSet.getString("itemCode"),
                    resultSet.getString("brand"),
                    resultSet.getString("modalNo"),
                    resultSet.getString("itemName"),
                    resultSet.getDouble("price"),
                    resultSet.getString("warranty"),
                    resultSet.getInt("qty"),
                    resultSet.getString("category")));
        }
        return allAccessories;
    }
    public ArrayList<Item> loadParts() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet resultSet = stm.executeQuery("SELECT * from item where itemCode like 'R%'");
        ArrayList<Item> allParts = new ArrayList<>();
        while (resultSet.next()){
            allParts.add(new Item(
                    resultSet.getString("itemCode"),
                    resultSet.getString("brand"),
                    resultSet.getString("modalNo"),
                    resultSet.getString("itemName"),
                    resultSet.getDouble("price"),
                    resultSet.getString("warranty"),
                    resultSet.getInt("qty"),
                    resultSet.getString("category")));
        }
        return allParts;
    }
    public boolean deleteItem(String  code) throws SQLException, ClassNotFoundException {
        String sql = "Delete from item where itemCode = ?";
        return CrudUtil.execute(sql,code);
    }
    public  Item searchItem(String code) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM item WHERE itemCode = ?";
        ResultSet resultSet = CrudUtil.execute(sql, code);
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
}
