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
    public Repair searchRepair(String id) throws SQLException, ClassNotFoundException {
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
    public boolean updateRepair(Repair repair) throws SQLException, ClassNotFoundException {
        String sql = "Update repair set amount=?,due=?,state=? where repId=?";
        return CrudUtil.execute(sql,repair.getAmount(),repair.getDue(),repair.getState(),repair.getRepairNo());
    }
    public  String getNextRepairID() throws SQLException, ClassNotFoundException {
        String sql = "SELECT repId FROM repair ORDER BY repId DESC LIMIT 1;";
        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()){
            return getNextRepairID(resultSet.getString(1));
        }
        return getNextRepairID(resultSet.getString(null));
    }
    public  String getNextRepairID(String cusId){
        if (cusId!=null){
            String[]split = cusId.split("R0");
            int id = Integer.parseInt(split[1]);
            id+=1;
            return "R00"+id;
        }
        return "R001";
    }
}
