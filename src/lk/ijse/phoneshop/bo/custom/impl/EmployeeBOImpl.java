package lk.ijse.phoneshop.bo.custom.impl;

import lk.ijse.phoneshop.bo.custom.EmployeeBO;
import lk.ijse.phoneshop.dao.DAOFactory;
import lk.ijse.phoneshop.dao.custom.EmployeeDAO;
import lk.ijse.phoneshop.dto.CustomerDTO;
import lk.ijse.phoneshop.dto.EmployeeDTO;
import lk.ijse.phoneshop.entity.Customer;
import lk.ijse.phoneshop.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    private EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOType.EMPLOYEE);

    @Override
    public boolean saveEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(dto.getId(), dto.getName(),dto.getAddress(),dto.getPhoneNo(),dto.getEmail(),dto.getDateOfBirth(),dto.getJobRole(),dto.getUserName(),dto.getPassword()));
    }
    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(dto.getId(), dto.getName(),dto.getAddress(),dto.getPhoneNo(),dto.getEmail(),dto.getDateOfBirth(),dto.getJobRole(),dto.getUserName(),dto.getPassword()));
    }
    @Override
    public EmployeeDTO searchEmployee(String id) throws SQLException, ClassNotFoundException {
        Employee employee = employeeDAO.search(id);
        return new EmployeeDTO(employee.getEid(),employee.getName(),employee.getAddress(),employee.getPhoneNo(),employee.getEmail(),employee.getDateOfBirth(),employee.getJobRole(),employee.getUserName(),employee.getPassword());
    }
    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> allEmployee = new ArrayList<>();
        ArrayList<Employee> all = employeeDAO.getAll();
        for (Employee employee: all) {
            allEmployee.add(new EmployeeDTO(employee.getEid(),employee.getName(),employee.getAddress(),employee.getPhoneNo(),employee.getEmail(),employee.getDateOfBirth(),employee.getJobRole(),employee.getUserName(),employee.getPassword()));
        }
        return allEmployee;
    }
    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }
    @Override
    public String getNextEmployeeId() throws SQLException, ClassNotFoundException {
        return employeeDAO.getNextId();
    }
}
