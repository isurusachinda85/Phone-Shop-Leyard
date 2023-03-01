package lk.ijse.phoneshop.bo.custom;

import lk.ijse.phoneshop.bo.SuperBO;
import lk.ijse.phoneshop.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    boolean saveEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException ;

    boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException ;

    EmployeeDTO searchEmployee(String id) throws SQLException, ClassNotFoundException ;

    ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException ;

    boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException ;

    String getNextEmployeeId() throws SQLException, ClassNotFoundException ;

}
