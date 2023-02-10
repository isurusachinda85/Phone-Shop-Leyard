package lk.ijse.phoneshop.bo.custom.impl;

import lk.ijse.phoneshop.bo.custom.PlaceOrderBO;
import lk.ijse.phoneshop.dao.custom.CustomerDAO;
import lk.ijse.phoneshop.dao.custom.ItemDAO;
import lk.ijse.phoneshop.dao.custom.OrderDAO;
import lk.ijse.phoneshop.dao.custom.OrderDetailDAO;
import lk.ijse.phoneshop.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.phoneshop.dao.custom.impl.ItemDAOImpl;
import lk.ijse.phoneshop.dao.custom.impl.OrderDAOImpl;
import lk.ijse.phoneshop.dao.custom.impl.OrderDetailDAOImpl;
import lk.ijse.phoneshop.db.DBConnection;
import lk.ijse.phoneshop.dto.Customer;
import lk.ijse.phoneshop.dto.Item;
import lk.ijse.phoneshop.dto.Order;
import lk.ijse.phoneshop.dto.PlaceOrder;
import lk.ijse.phoneshop.model.ItemM;
import lk.ijse.phoneshop.model.OrderDetailM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class PlaceOrderBOImpl implements PlaceOrderBO {

    private CustomerDAO customerDAO = new CustomerDAOImpl();
    private ItemDAO itemDAO = new ItemDAOImpl();
    private OrderDAO orderDAO = new OrderDAOImpl();
    private OrderDetailDAO orderDetailDAO = new OrderDetailDAOImpl();

    @Override
    public  boolean placeOrder(PlaceOrder placeOrder) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean saveOrder = orderDAO.save(new Order(placeOrder.getOrderId(), LocalDate.now(), LocalTime.now(), placeOrder.getCustomerId()));
            if (saveOrder){
                boolean updateQty = ItemM.updateQty(placeOrder.getOrderDetail());
                if (updateQty){
                    boolean saveOrderDetails = OrderDetailM.saveOrderDetails(placeOrder.getOrderDetail());
                    if (saveOrderDetails){
                        DBConnection.getInstance().getConnection().commit();
                        return true;
                    }
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        } finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }
    @Override
    public Customer searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.search(id);
    }
    @Override
    public Item searchItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.search(code);
    }
    @Override
    public String getNextOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.getNextId();
    }
    @Override
    public ArrayList<Customer> loadCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }
    @Override
    public ArrayList<Item> loadItemCode() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }
}
