package lk.ijse.phoneshop.bo;

import lk.ijse.phoneshop.dao.CrudDAO;
import lk.ijse.phoneshop.dao.SQLUtil;
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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class PlaceOrderBOImpl {

    private CustomerDAO customerDAO = new CustomerDAOImpl();
    private ItemDAO itemDAO = new ItemDAOImpl();
    private OrderDAO orderDAO = new OrderDAOImpl();
    private OrderDetailDAO orderDetailDAO = new OrderDetailDAOImpl();

    public  boolean placeOrder(PlaceOrder placeOrder) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean saveOrder = orderDAO.save(new Order(placeOrder.getOrderId(), LocalDate.now(), LocalTime.now(), placeOrder.getCustomerId()));
            if (saveOrder){
                boolean updateQty = itemDAO.update(placeOrder.getOrderDetail());
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
    public Customer searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.search(id);
    }
    public Item searchItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.search(code);
    }
}
