package lk.ijse.phoneshop.dao;

import lk.ijse.phoneshop.db.DBConnection;
import lk.ijse.phoneshop.to.Item;
import lk.ijse.phoneshop.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public interface ItemDAO {
    boolean itemAdd(Item item) throws SQLException, ClassNotFoundException;
    ArrayList<Item> loadAllPhone() throws SQLException, ClassNotFoundException;
    ArrayList<Item> loadAccessories() throws SQLException, ClassNotFoundException;
    ArrayList<Item> loadParts() throws SQLException, ClassNotFoundException;
    boolean deleteItem(String code) throws SQLException, ClassNotFoundException;
    Item searchItem(String code) throws SQLException, ClassNotFoundException;
    ResultSet loadItemCode() throws SQLException, ClassNotFoundException;
}
