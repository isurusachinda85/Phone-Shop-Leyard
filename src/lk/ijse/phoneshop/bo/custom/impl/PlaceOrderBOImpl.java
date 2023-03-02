package lk.ijse.phoneshop.bo.custom.impl;

import lk.ijse.phoneshop.bo.custom.PlaceOrderBO;
import lk.ijse.phoneshop.dao.DAOFactory;
import lk.ijse.phoneshop.dao.custom.CustomerDAO;
import lk.ijse.phoneshop.dao.custom.ItemDAO;
import lk.ijse.phoneshop.dao.custom.OrderDAO;
import lk.ijse.phoneshop.dao.custom.OrderDetailDAO;
import lk.ijse.phoneshop.db.DBConnection;
import lk.ijse.phoneshop.dto.CustomerDTO;
import lk.ijse.phoneshop.dto.ItemDTO;
import lk.ijse.phoneshop.dto.OrderDTO;
import lk.ijse.phoneshop.dto.PlaceOrderDTO;
import lk.ijse.phoneshop.entity.Customer;
import lk.ijse.phoneshop.entity.Item;
import lk.ijse.phoneshop.entity.Order;
import lk.ijse.phoneshop.model.ItemM;
import lk.ijse.phoneshop.model.OrderDetailM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class PlaceOrderBOImpl implements PlaceOrderBO {

    private CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOType.CUSTOMER);
    private ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOType.ITEM);
    private OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOType.ORDER);
    private OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOType.ORDERDETAILS);

    @Override
    public  boolean placeOrder(PlaceOrderDTO placeOrderDTO) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean saveOrder = orderDAO.save(new Order(placeOrderDTO.getOrderId(), LocalDate.now(), LocalTime.now(), placeOrderDTO.getCustomerId()));
            if (saveOrder){
                boolean updateQty = ItemM.updateQty(placeOrderDTO.getOrderDetail());
                if (updateQty){
                    boolean saveOrderDetails = OrderDetailM.saveOrderDetails(placeOrderDTO.getOrderDetail());
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
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer search = customerDAO.search(id);
        return new CustomerDTO(search.getCusId());
    }
    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        Item search = itemDAO.search(code);
        return new ItemDTO(search.getItemCode());

    }
    @Override
    public String getNextOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.getNextId();
    }
    @Override
    public ArrayList<CustomerDTO> loadCustomerId() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomer = new ArrayList<>();
        ArrayList<Customer> all = customerDAO.getAll();
        for (Customer customer : all) {
            allCustomer.add(new CustomerDTO(customer.getCusId(),customer.getName(),customer.getAddress(),customer.getPhoneNo(),customer.getEmail()));
        }
        return allCustomer;
    }
    @Override
    public ArrayList<ItemDTO> loadItemCode() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> allItem = new ArrayList<>();
        ArrayList<Item> all = itemDAO.getAll();
        for (Item item: all) {
            allItem.add(new ItemDTO(item.getItemCode(),item.getBrand(),item.getModalNo(),item.getItemName(),item.getPrice(),item.getWarranty(),item.getQty(),item.getCategory()));
        }
        return allItem;
    }
}
