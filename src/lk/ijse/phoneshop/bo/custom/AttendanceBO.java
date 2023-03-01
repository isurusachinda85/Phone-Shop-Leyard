package lk.ijse.phoneshop.bo.custom;

import lk.ijse.phoneshop.bo.SuperBO;
import lk.ijse.phoneshop.dto.AttendanceDTO;
import lk.ijse.phoneshop.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AttendanceBO extends SuperBO {
    boolean saveAttendance(AttendanceDTO attendanceDTO) throws SQLException, ClassNotFoundException ;

    ArrayList<AttendanceDTO> getAllAttendance() throws SQLException, ClassNotFoundException ;

    boolean deleteAttendance(String id) throws SQLException, ClassNotFoundException ;

    ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException ;

    EmployeeDTO searchEmployee(String id) throws SQLException, ClassNotFoundException ;

    String getNextAttendanceId() throws SQLException, ClassNotFoundException ;
}
