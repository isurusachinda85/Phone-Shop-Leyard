package lk.ijse.phoneshop.dao;

import lk.ijse.phoneshop.dto.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements CrudDAO<Item,String> {
    @Override
    public boolean save(Item item) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO item VALUES (?,?,?,?,?,?,?,?)";
        return SQLUtil.execute(sql,item.getItemCode(),item.getBrand(),item.getModalNo(),item.getName(),item.getPrice(),
                item.getWarranty(),item.getQty(),item.getCategory());
    }
    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * from item");
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
    /*@Override
    public ArrayList<Item> loadAccessories() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * from item where itemCode like 'A%'");
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
    @Override
    public ArrayList<Item> loadParts() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * from item where itemCode like 'R%'");
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
    }*/
    @Override
    public boolean delete(String  code) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("Delete from item where itemCode = ?",code);
    }
    @Override
    public  Item search(String code) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT  * FROM item WHERE itemCode = ?", code);
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

    @Override
    public boolean update(Item dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getNextId(String s) {
        return null;
    }
}
