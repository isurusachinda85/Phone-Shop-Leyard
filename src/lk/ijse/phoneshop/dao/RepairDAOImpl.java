package lk.ijse.phoneshop.dao;

import lk.ijse.phoneshop.dto.Repair;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepairDAOImpl implements CrudDAO<Repair,String>{
    @Override
    public boolean save(Repair repair) throws SQLException, ClassNotFoundException {
        String sql = "INSERT Into repair values (?,?,?,?,?,?,?,?,?,?,?)";
        return SQLUtil.execute(sql,repair.getRepairNo(),repair.getCustomerName(),repair.getPhoneNo(),repair.getDeviceName(),
                repair.getDeviceProblem(),repair.getPrice(),repair.getAmount(),repair.getDue(),repair.getState(),
                repair.getDate(),repair.getCustomerId());
    }
    @Override
    public ArrayList<Repair> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT repId,customerName,phoneNo,deviceName,problem,repairPrice,amount,due,state from repair");
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
    @Override
    public boolean delete(String  rid) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("Delete From repair where repId=?",rid);
    }
    @Override
    public Repair search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet  = SQLUtil.execute("SELECT * FROM repair WHERE repId=?", id);

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
    @Override
    public boolean update(Repair repair) throws SQLException, ClassNotFoundException {
        String sql = "Update repair set amount=?,due=?,state=? where repId=?";
        return SQLUtil.execute(sql,repair.getAmount(),repair.getDue(),repair.getState(),repair.getRepairNo());
    }
    @Override
    public  String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT repId FROM repair ORDER BY repId DESC LIMIT 1;");
        if (resultSet.next()){
            return getNextId(resultSet.getString(1));
        }
        return getNextId(resultSet.getString(null));
    }
    @Override
    public  String getNextId(String cusId){
        if (cusId!=null){
            String[]split = cusId.split("R0");
            int id = Integer.parseInt(split[1]);
            id+=1;
            return "R00"+id;
        }
        return "R001";
    }
}
