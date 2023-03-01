package lk.ijse.phoneshop.bo.custom.impl;

import lk.ijse.phoneshop.bo.custom.AttendanceBO;
import lk.ijse.phoneshop.dao.DAOFactory;
import lk.ijse.phoneshop.dao.custom.AttendanceDAO;
import lk.ijse.phoneshop.dao.custom.EmployeeDAO;
import lk.ijse.phoneshop.dto.AttendanceDTO;
import lk.ijse.phoneshop.dto.EmployeeDTO;
import lk.ijse.phoneshop.entity.Attendance;
import lk.ijse.phoneshop.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceBOImpl implements AttendanceBO {
    private AttendanceDAO attendanceDAO = (AttendanceDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOType.ATTENDANCE);
    private EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOType.EMPLOYEE);

    @Override
    public boolean saveAttendance(AttendanceDTO dto) throws SQLException, ClassNotFoundException {
        return attendanceDAO.save(new Attendance(dto.getAttendanceId(),dto.getEmployeeId(),dto.getName(),dto.getDate(),dto.getSate(),dto.getInTime(),dto.getOutTime()));
    }
    @Override
    public ArrayList<AttendanceDTO> getAllAttendance() throws SQLException, ClassNotFoundException {
        ArrayList<AttendanceDTO> allAttendance = new ArrayList<>();
        ArrayList<Attendance> all = attendanceDAO.getAll();
        for (Attendance attendance : all) {
            allAttendance.add(new AttendanceDTO(attendance.getAid(),attendance.getEid(),attendance.getEmployeeName(),attendance.getDate(),attendance.getSate(),attendance.getInTime(),attendance.getOutTime()));
        }
        return allAttendance;
    }
    @Override
    public boolean deleteAttendance(String id) throws SQLException, ClassNotFoundException {
        return attendanceDAO.delete(id);
    }
    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> allAttendance = new ArrayList<>();
        ArrayList<Employee> all = employeeDAO.getAll();
        for (Employee employee : all) {
            allAttendance.add(new EmployeeDTO(employee.getEid(),employee.getName(),employee.getAddress(),employee.getPhoneNo(),employee.getEmail(),employee.getDateOfBirth(),employee.getJobRole(),employee.getUserName(),employee.getPassword()));
        }
        return allAttendance;
    }
    @Override
    public EmployeeDTO searchEmployee(String id) throws SQLException, ClassNotFoundException {
        Employee employee = employeeDAO.search(id);
        return new EmployeeDTO(employee.getEid());
    }
    @Override
    public String getNextAttendanceId() throws SQLException, ClassNotFoundException {
        return attendanceDAO.getNextId();
    }
}
