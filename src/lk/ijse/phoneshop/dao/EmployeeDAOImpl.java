package lk.ijse.phoneshop.dao;

import lk.ijse.phoneshop.dto.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public boolean saveEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        String sql = "INSERT Into employee values (?,?,?,?,?,?,?,?,?)";
        return SQLUtil.execute(sql,employee.getId(),employee.getName(),employee.getAddress(),employee.getEmail(),
                employee.getPhoneNo(),employee.getDateOfBirth(),employee.getJobRole(),employee.getUserName(),employee.getPassword());
    }
    @Override
    public ArrayList<Employee> getAllEmployee() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * From employee");
        ArrayList<Employee> allEmployee = new ArrayList<>();

        while (resultSet.next()){
            allEmployee.add(new Employee(
                    resultSet.getString("eId"),
                    resultSet.getString("name"),
                    resultSet.getString("address"),
                    resultSet.getInt("phoneNo"),
                    resultSet.getString("email"),
                    resultSet.getString("dateOfBirth"),
                    resultSet.getString("jobRole"),
                    resultSet.getString("userName"),
                    resultSet.getString("Password")));
        }

        return allEmployee;
    }
    @Override
    public String getNextEmployeeId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT eId FROM employee ORDER BY eId DESC LIMIT 1");
        if (resultSet.next()){
            return getNextEmployeeId(resultSet.getString(1));
        }
        return getNextEmployeeId(resultSet.getString(null));
    }
    @Override
    public String getNextEmployeeId(String cusId){
        if (cusId!=null){
            String[]split = cusId.split("E0");
            int id = Integer.parseInt(split[1]);
            id+=1;
            return "E00"+id;

        }
        return "E001";

    }
    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("Delete From employee where eId=?",id);
    }
    @Override
    public Employee searchEmployee(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT  * FROM employee WHERE eId = ?", id);
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
    @Override
    public boolean updateEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        String sql = "Update employee set name=?,address=?,email=?,phoneNo=?,dateOfBirth=?,jobRole=?,userName=? where eId=?";
        return SQLUtil.execute(sql,employee.getName(),employee.getAddress(),employee.getEmail(),employee.getPhoneNo(),employee.getDateOfBirth(),employee.getJobRole(),employee.getUserName(),employee.getId());
    }
}
