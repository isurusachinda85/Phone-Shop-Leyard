package lk.ijse.phoneshop.dao;

import lk.ijse.phoneshop.db.DBConnection;
import lk.ijse.phoneshop.to.Item;
import lk.ijse.phoneshop.to.Repair;
import lk.ijse.phoneshop.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RepairDAOImpl {
    public boolean saveRepair(Repair repair) throws SQLException, ClassNotFoundException {
        String sql = "INSERT Into repair values (?,?,?,?,?,?,?,?,?,?,?)";
        return CrudUtil.execute(sql,repair.getRepairNo(),repair.getCustomerName(),repair.getPhoneNo(),repair.getDeviceName(),
                repair.getDeviceProblem(),repair.getPrice(),repair.getAmount(),repair.getDue(),repair.getState(),
                repair.getDate(),repair.getCustomerId());
    }
    public ArrayList<Repair> getAllRepair() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet resultSet = stm.executeQuery("SELECT repId,customerName,phoneNo,deviceName,problem,repairPrice,amount,due,state from repair");
        ArrayList<Repair> allRepair = new ArrayList<>();
        while (resultSet.next()){
            allRepair.add(new Repair(
                    resultSet.getString("repId"),
                    resultSet.getString("customerName"),
                    resultSet.getInt("phoneNo"),
                    resultSet.getString("deviceName"),
                    resultSet.getString("problem"),
                    resultSet.getDouble("repairPrice"),
                    resultSet.getDouble("amount"),
                    resultSet.getDouble("due"),
                    resultSet.getString("state")));
        }
        return allRepair;
    }
    public boolean deleteRepair(String  rid) throws SQLException, ClassNotFoundException {
        String sql = "Delete From repair where repId=?";
        return CrudUtil.execute(sql,rid);
    }
}
