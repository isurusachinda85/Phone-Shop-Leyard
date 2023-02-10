package lk.ijse.phoneshop.bo.custom;

import lk.ijse.phoneshop.bo.SuperBO;
import lk.ijse.phoneshop.dto.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    boolean saveEmployee(Employee employee) throws SQLException, ClassNotFoundException ;

    boolean updateEmployee(Employee employee) throws SQLException, ClassNotFoundException ;

    Employee searchEmployee(String id) throws SQLException, ClassNotFoundException ;

    ArrayList<Employee> getAllEmployee() throws SQLException, ClassNotFoundException ;

    boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException ;

    String getNextEmployeeId() throws SQLException, ClassNotFoundException ;

}
