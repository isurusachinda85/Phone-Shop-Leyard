package lk.ijse.phoneshop.model;

import lk.ijse.phoneshop.to.Attendance;
import lk.ijse.phoneshop.to.Employee;
import lk.ijse.phoneshop.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendanceM {
    public static ResultSet loadEmployeeId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT eId from employee order by eId asc";
        return CrudUtil.execute(sql);
    }
    public static Employee searchEmployee(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM employee WHERE eId = ?";
        ResultSet resultSet = CrudUtil.execute(sql, id);
        if(resultSet.next()){
            return new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt("phoneNo"),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9));
        }
        return null;
    }

    public static String getNextAttendanceId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT aid FROM attendance ORDER BY aid DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);
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
        return CrudUtil.execute(sql,attendance.getAttendanceId(),attendance.getName(),attendance.getDate(),
                attendance.getInTime(),attendance.getOutTime(),attendance.getSate(),attendance.getEmployeeId());
    }
    public static ResultSet getAllAttendance() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * From attendance";
        return CrudUtil.execute(sql);
    }
}
