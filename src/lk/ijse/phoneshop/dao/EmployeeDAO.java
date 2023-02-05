package lk.ijse.phoneshop.dao;

import lk.ijse.phoneshop.db.DBConnection;
import lk.ijse.phoneshop.to.Employee;
import lk.ijse.phoneshop.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
