package lk.ijse.phoneshop.bo.custom;

import lk.ijse.phoneshop.bo.SuperBO;
import lk.ijse.phoneshop.dto.Attendance;
import lk.ijse.phoneshop.dto.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AttendanceBO extends SuperBO {
    boolean saveAttendance(Attendance attendance) throws SQLException, ClassNotFoundException ;

    ArrayList<Attendance> getAllAttendance() throws SQLException, ClassNotFoundException ;

    boolean deleteAttendance(String id) throws SQLException, ClassNotFoundException ;

    ArrayList<Employee> getAllEmployee() throws SQLException, ClassNotFoundException ;

    Employee searchEmployee(String id) throws SQLException, ClassNotFoundException ;

    String getNextAttendanceId() throws SQLException, ClassNotFoundException ;
}
