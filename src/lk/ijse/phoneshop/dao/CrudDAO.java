package lk.ijse.phoneshop.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T,ID> {
    boolean save(T dto) throws SQLException, ClassNotFoundException;
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    boolean delete(ID id) throws SQLException, ClassNotFoundException;
    T search(ID id) throws SQLException, ClassNotFoundException;
    boolean update(T dto) throws SQLException, ClassNotFoundException;
    String getNextId() throws SQLException, ClassNotFoundException;
    String getNextId(ID id);
}
