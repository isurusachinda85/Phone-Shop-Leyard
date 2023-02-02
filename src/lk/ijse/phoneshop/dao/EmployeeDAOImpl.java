package lk.ijse.phoneshop.dao;

import lk.ijse.phoneshop.to.Employee;
import lk.ijse.phoneshop.util.CrudUtil;

import java.sql.SQLException;

public class EmployeeDAOImpl {
    public  boolean saveEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        String sql = "INSERT Into employee values (?,?,?,?,?,?,?,?,?)";
        return CrudUtil.execute(sql,employee.getId(),employee.getName(),employee.getAddress(),employee.getEmail(),
                employee.getPhoneNo(),employee.getDateOfBirth(),employee.getJobRole(),employee.getUserName(),employee.getPassword());
    }
}
