package lk.ijse.phoneshop.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO {
    boolean save(T dto) throws SQLException, ClassNotFoundException;

    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    T search(String id) throws SQLException, ClassNotFoundException;

    boolean update(T dto) throws SQLException, ClassNotFoundException;

    String getNextId() throws SQLException, ClassNotFoundException;

    String getNextId(String id);
}
