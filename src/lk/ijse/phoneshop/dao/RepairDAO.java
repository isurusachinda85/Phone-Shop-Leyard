package lk.ijse.phoneshop.dao;

import lk.ijse.phoneshop.to.Repair;

import java.sql.SQLException;
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
