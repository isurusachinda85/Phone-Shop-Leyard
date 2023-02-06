package lk.ijse.phoneshop.dao;

import lk.ijse.phoneshop.to.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDAO {
    boolean saveEmployee(Employee employee) throws SQLException, ClassNotFoundException;
    ArrayList<Employee> getAllEmployee() throws SQLException, ClassNotFoundException;
    String getNextEmployeeId() throws SQLException, ClassNotFoundException;
    String getNextEmployeeId(String cusId);
    boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;
    Employee searchEmployee(String id) throws SQLException, ClassNotFoundException;
    boolean updateEmployee(Employee employee) throws SQLException, ClassNotFoundException;
}
