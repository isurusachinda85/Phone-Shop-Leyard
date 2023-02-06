package lk.ijse.phoneshop.dao;

import lk.ijse.phoneshop.db.DBConnection;
import lk.ijse.phoneshop.to.Attendance;
import lk.ijse.phoneshop.to.Employee;
import lk.ijse.phoneshop.util.SQLUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AttendanceDAOImpl implements AttendanceDAO{
    @Override
    public boolean attendanceSave(Attendance attendance) throws SQLException, ClassNotFoundException {
        String sql = "INSERT Into attendance values (?,?,?,?,?,?,?)";
        return SQLUtil.execute(sql,attendance.getAttendanceId(),attendance.getName(),attendance.getDate(),
                attendance.getInTime(),attendance.getOutTime(),attendance.getSate(),attendance.getEmployeeId());
    }
    @Override
    public ArrayList<Attendance> getAllAttendance() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet resultSet = stm.executeQuery("SELECT * From attendance");
        ArrayList<Attendance> allAttendance = new ArrayList<>();

        while (resultSet.next()){
            allAttendance.add(new Attendance(
                    resultSet.getString("aid"),
                    resultSet.getString("eid"),
                    resultSet.getString("employeeName"),
                    resultSet.getString("date"),
                    resultSet.getString("state"),
                    resultSet.getString("inTime"),
                    resultSet.getString("outTime")));
        }

        return allAttendance;
    }
    @Override
    public  ResultSet loadEmployeeId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT eId from employee order by eId asc";
        return SQLUtil.execute(sql);
    }
    @Override
    public  Employee searchEmployee(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM employee WHERE eId = ?";
        ResultSet resultSet = SQLUtil.execute(sql, id);
        if(resultSet.next()){
            return new Employee(
                    resultSet.getString("name"));
        }
        return null;
    }
    @Override
    public String getNextAttendanceId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT aid FROM attendance ORDER BY aid DESC LIMIT 1";
        ResultSet resultSet = SQLUtil.execute(sql);
        if (resultSet.next()){
            return getNextAttendanceId(resultSet.getString(1));
        }else {
            return getNextAttendanceId(resultSet.getString(null));
        }
    }
    @Override
    public String getNextAttendanceId(String aId){
        if (aId!=null){
            String[]split = aId.split("A0");
            int id = Integer.parseInt(split[1]);
            id+=1;
            return "A00"+id;

        }
        return "A001";
    }
    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        String sql = "Delete From attendance where aid=?";
        return SQLUtil.execute(sql,id);
    }
}
