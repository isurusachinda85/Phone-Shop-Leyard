package lk.ijse.phoneshop.model;

import lk.ijse.phoneshop.to.Attendance;
import lk.ijse.phoneshop.to.Employee;
import lk.ijse.phoneshop.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendanceM {
    public static ResultSet loadEmployeeId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT eId from employee order by eId asc";
        return SQLUtil.execute(sql);
    }
    public static Employee searchEmployee(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM employee WHERE eId = ?";
        ResultSet resultSet = SQLUtil.execute(sql, id);
        if(resultSet.next()){
            return new Employee(
                    resultSet.getString("name"));
        }
        return null;
    }

    public static String getNextAttendanceId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT aid FROM attendance ORDER BY aid DESC LIMIT 1";
        ResultSet resultSet = SQLUtil.execute(sql);
        if (resultSet.next()){
            return getNextAttendanceId(resultSet.getString(1));
        }else {
            return getNextAttendanceId(resultSet.getString(null));
        }
    }
    public static String getNextAttendanceId(String aId){
        if (aId!=null){
            String[]split = aId.split("A0");
            int id = Integer.parseInt(split[1]);
            id+=1;
            return "A00"+id;

        }
        return "A001";
    }
    public static boolean attendanceSave(Attendance attendance) throws SQLException, ClassNotFoundException {
        String sql = "INSERT Into attendance values (?,?,?,?,?,?,?)";
        return SQLUtil.execute(sql,attendance.getAttendanceId(),attendance.getName(),attendance.getDate(),
                attendance.getInTime(),attendance.getOutTime(),attendance.getSate(),attendance.getEmployeeId());
    }
    public static ResultSet getAllAttendance() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * From attendance";
        return SQLUtil.execute(sql);
    }
}
