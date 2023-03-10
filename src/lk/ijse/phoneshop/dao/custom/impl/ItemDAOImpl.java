package lk.ijse.phoneshop.dao.custom.impl;

import lk.ijse.phoneshop.dao.SQLUtil;
import lk.ijse.phoneshop.dao.custom.ItemDAO;
import lk.ijse.phoneshop.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean save(Item item) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO item VALUES (?,?,?,?,?,?,?,?)";
        return SQLUtil.execute(sql, item.getItemCode(), item.getBrand(), item.getModalNo(), item.getItemName(), item.getPrice(),
                item.getWarranty(), item.getQty(), item.getCategory());
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * from item");
        ArrayList<Item> allItem = new ArrayList<>();
        while (resultSet.next()) {
            allItem.add(new Item(
                    resultSet.getString("itemCode"),
                    resultSet.getString("brand"),
                    resultSet.getString("modalNo"),
                    resultSet.getString("itemName"),
                    resultSet.getDouble("price"),
                    resultSet.getString("warranty"),
                    resultSet.getInt("qty"),
                    resultSet.getString("category")));
        }
        return allItem;
    }

    @Override
    public ArrayList<Item> loadPhone() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * from item where itemCode like 'P%'");
        ArrayList<Item> allPhone = new ArrayList<>();
        while (resultSet.next()) {
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

    @Override
    public ArrayList<Item> loadAccessories() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * from item where itemCode like 'A%'");
        ArrayList<Item> allAccessories = new ArrayList<>();
        while (resultSet.next()) {
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
        while (resultSet.next()) {
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

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("Delete from item where itemCode = ?", code);
    }

    @Override
    public Item search(String code) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT  * FROM item WHERE itemCode = ?", code);
        while (resultSet.next()) {
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
    public boolean update(Item item) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Item SET qty = qty - ? WHERE itemCode = ?";
        return SQLUtil.execute(sql, item.getQty(), item.getItemCode());
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
