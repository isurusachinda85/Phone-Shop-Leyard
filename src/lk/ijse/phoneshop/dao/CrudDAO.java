package lk.ijse.phoneshop.dao;

import lk.ijse.phoneshop.to.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T,ID> {
    boolean save(T customer) throws SQLException, ClassNotFoundException;
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    boolean delete(ID id) throws SQLException, ClassNotFoundException;
    T search(ID id) throws SQLException, ClassNotFoundException;
    boolean update(T customer) throws SQLException, ClassNotFoundException;
    String getNextId() throws SQLException, ClassNotFoundException;
    String getNextId(ID cusId);
    ResultSet loadId() throws SQLException, ClassNotFoundException;
}
