package lk.ijse.phoneshop.dao;

import lk.ijse.phoneshop.db.DBConnection;
import lk.ijse.phoneshop.to.Attendance;
import lk.ijse.phoneshop.to.Employee;
import lk.ijse.phoneshop.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public interface AttendanceDAO {
    boolean attendanceSave(Attendance attendance) throws SQLException, ClassNotFoundException;
    ArrayList<Attendance> getAllAttendance() throws SQLException, ClassNotFoundException;
    ResultSet loadEmployeeId() throws SQLException, ClassNotFoundException;
    Employee searchEmployee(String id) throws SQLException, ClassNotFoundException;
    String getNextAttendanceId() throws SQLException, ClassNotFoundException;
    String getNextAttendanceId(String aId);
    boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;
}
