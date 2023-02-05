package lk.ijse.phoneshop.dao;

import lk.ijse.phoneshop.db.DBConnection;
import lk.ijse.phoneshop.to.Repair;
import lk.ijse.phoneshop.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public interface RepairDAO {
    boolean saveRepair(Repair repair) throws SQLException, ClassNotFoundException;
    ArrayList<Repair> getAllRepair() throws SQLException, ClassNotFoundException;
    boolean deleteRepair(String rid) throws SQLException, ClassNotFoundException;
    Repair searchRepair(String id) throws SQLException, ClassNotFoundException;
    boolean updateRepair(Repair repair) throws SQLException, ClassNotFoundException;
    String getNextRepairID() throws SQLException, ClassNotFoundException;
    String getNextRepairID(String cusId);
}
