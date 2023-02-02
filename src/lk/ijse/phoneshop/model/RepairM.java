package lk.ijse.phoneshop.model;

import lk.ijse.phoneshop.to.Repair;
import lk.ijse.phoneshop.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RepairM {
    public static boolean saveRepair(Repair repair) throws SQLException, ClassNotFoundException {
        String sql = "INSERT Into repair values (?,?,?,?,?,?,?,?,?,?,?)";
        return CrudUtil.execute(sql,repair.getRepairNo(),repair.getCustomerName(),repair.getPhoneNo(),repair.getDeviceName(),
                repair.getDeviceProblem(),repair.getPrice(),repair.getAmount(),repair.getDue(),repair.getState(),
                repair.getDate(),repair.getCustomerId());
    }
    public static ResultSet getAllRepair() throws SQLException, ClassNotFoundException {
        String sql = "SELECT repId,customerName,phoneNo,deviceName,problem,repairPrice,amount,due,state from repair";
        return CrudUtil.execute(sql);
    }
    public static Repair searchRepair(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM repair WHERE repId=?";

        ResultSet resultSet  = CrudUtil.execute(sql, id);

        if(resultSet.next()){
            return new Repair(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getDouble(6),
                    resultSet.getDouble(7),
                    resultSet.getDouble(8),
                    resultSet.getString(9));

        }
        return null;
    }
    public static boolean updateRepair(Repair repair) throws SQLException, ClassNotFoundException {
        String sql = "Update repair set amount=?,due=?,state=? where repId=?";
        return CrudUtil.execute(sql,repair.getAmount(),repair.getDue(),repair.getState(),repair.getRepairNo());
    }
}
