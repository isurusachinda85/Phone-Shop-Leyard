package lk.ijse.phoneshop.bo;

import lk.ijse.phoneshop.dao.SQLUtil;
import lk.ijse.phoneshop.dao.custom.EmployeeDAO;
import lk.ijse.phoneshop.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.phoneshop.dto.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl {
    private EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    public boolean saveEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(employee);
    }
    public boolean updateEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(employee);
    }
    public Employee searchEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.search(id);
    }
    public ArrayList<Employee> getAllEmployee() throws SQLException, ClassNotFoundException {
        return employeeDAO.getAll();
    }
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }
    public String getNextEmployeeId() throws SQLException, ClassNotFoundException {
        return employeeDAO.getNextId();
    }
}
