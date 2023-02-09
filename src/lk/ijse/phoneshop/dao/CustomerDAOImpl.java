package lk.ijse.phoneshop.dao;

import lk.ijse.phoneshop.dto.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean save(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "INSERT Into customer values (?,?,?,?,?)";
        return SQLUtil.execute(sql,customer.getId(),customer.getName(),customer.getAddress(),customer.getPhoneNo(),customer.getEmail());
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet  = SQLUtil.execute("SELECT * From Customer");
        ArrayList<Customer> allCustomer = new ArrayList<>();

        while (resultSet.next()){
            allCustomer.add(new Customer(
                    resultSet.getString("cusId"),
                    resultSet.getString("name"),
                    resultSet.getString("address"),
                    resultSet.getString("phoneNo"),
                    resultSet.getString("email")));
        }
        return allCustomer;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("Delete From Customer where cusId=?",id);
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT  * FROM customer WHERE cusId = ?", id);
        while (resultSet.next()){
            return new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5));
        }
        return null;
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "Update Customer set name=?,address=?,phoneNo=?,email=? where cusId=?";
        return SQLUtil.execute(sql,customer.getName(),customer.getAddress(),customer.getPhoneNo(),customer.getEmail(),customer.getId());
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT cusId FROM customer ORDER BY cusId DESC LIMIT 1";
        ResultSet resultSet = SQLUtil.execute(sql);
        if (resultSet.next()){
            return getNextId(resultSet.getString(1));
        }
        return getNextId(resultSet.getString(null));
    }

    @Override
    public String getNextId(String cusId) {
        if (cusId!=null){
            String[]split = cusId.split("C0");
            int id = Integer.parseInt(split[1]);
            id+=1;
            return "C00"+id;

        }
        return "C001";
    }
}
