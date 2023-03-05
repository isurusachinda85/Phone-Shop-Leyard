package lk.ijse.phoneshop.dao.custom;

import lk.ijse.phoneshop.dao.CrudDAO;
import lk.ijse.phoneshop.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;


public interface ItemDAO extends CrudDAO<Item> {

    ArrayList<Item> loadAccessories() throws SQLException, ClassNotFoundException;

    ArrayList<Item> loadParts() throws SQLException, ClassNotFoundException;

    ArrayList<Item> loadPhone() throws SQLException, ClassNotFoundException;
}
