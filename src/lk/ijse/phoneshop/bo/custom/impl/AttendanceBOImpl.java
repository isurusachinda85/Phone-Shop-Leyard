package lk.ijse.phoneshop.bo.custom.impl;

import lk.ijse.phoneshop.bo.custom.AttendanceBO;
import lk.ijse.phoneshop.dao.DAOFactory;
import lk.ijse.phoneshop.dao.custom.AttendanceDAO;
import lk.ijse.phoneshop.dao.custom.EmployeeDAO;
import lk.ijse.phoneshop.dao.custom.impl.AttendanceDAOImpl;
import lk.ijse.phoneshop.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.phoneshop.dto.Attendance;
import lk.ijse.phoneshop.dto.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceBOImpl implements AttendanceBO {
    private AttendanceDAO attendanceDAO = (AttendanceDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOType.ATTENDANCE);
    private EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOType.EMPLOYEE);

    @Override
    public boolean saveAttendance(Attendance attendance) throws SQLException, ClassNotFoundException {
        return attendanceDAO.save(attendance);
    }
    @Override
    public ArrayList<Attendance> getAllAttendance() throws SQLException, ClassNotFoundException {
        return attendanceDAO.getAll();
    }
    @Override
    public boolean deleteAttendance(String id) throws SQLException, ClassNotFoundException {
        return attendanceDAO.delete(id);
    }
    @Override
    public ArrayList<Employee> getAllEmployee() throws SQLException, ClassNotFoundException {
        return employeeDAO.getAll();
    }
    @Override
    public Employee searchEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.search(id);
    }
    @Override
    public String getNextAttendanceId() throws SQLException, ClassNotFoundException {
        return attendanceDAO.getNextId();
    }
}
