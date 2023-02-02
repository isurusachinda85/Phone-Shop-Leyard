package lk.ijse.phoneshop.dao;

import lk.ijse.phoneshop.to.Employee;
import lk.ijse.phoneshop.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAOImpl {
    public boolean saveEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        String sql = "INSERT Into employee values (?,?,?,?,?,?,?,?,?)";
        return CrudUtil.execute(sql,employee.getId(),employee.getName(),employee.getAddress(),employee.getEmail(),
                employee.getPhoneNo(),employee.getDateOfBirth(),employee.getJobRole(),employee.getUserName(),employee.getPassword());
    }
    public ResultSet getAllEmployee() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * From employee";
        return CrudUtil.execute(sql);
    }
    public String getNextEmployeeId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT eId FROM employee ORDER BY eId DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()){
            return getNextEmployeeId(resultSet.getString(1));
        }
        return getNextEmployeeId(resultSet.getString(null));
    }
    public String getNextEmployeeId(String cusId){
        if (cusId!=null){
            String[]split = cusId.split("E0");
            int id = Integer.parseInt(split[1]);
            id+=1;
            return "E00"+id;

        }
        return "E001";

    }
    public boolean deleteEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        String sql = "Delete From employee where eId=?";
        return CrudUtil.execute(sql,employee.getId());
    }
    public Employee searchEmployee(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM employee WHERE eId = ?";
        ResultSet resultSet = CrudUtil.execute(sql, id);
        while (resultSet.next()){
            return new Employee(
                    resultSet.getString("eId"),
                    resultSet.getString("name"),
                    resultSet.getString("address"),
                    resultSet.getInt("phoneNo"),
                    resultSet.getString("email"),
                    resultSet.getString("dateOfBirth"),
                    resultSet.getString("jobRole"),
                    resultSet.getString("userName"),
                    resultSet.getString("password"));
        }
        return null;
    }
    public boolean updateEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        String sql = "Update employee set name=?,address=?,email=?,phoneNo=?,dateOfBirth=?,jobRole=?,userName=? where eId=?";
        return CrudUtil.execute(sql,employee.getName(),employee.getAddress(),employee.getEmail(),employee.getPhoneNo(),employee.getDateOfBirth(),employee.getJobRole(),employee.getUserName(),employee.getId());
    }
}
