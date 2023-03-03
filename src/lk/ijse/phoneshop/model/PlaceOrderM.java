package lk.ijse.phoneshop.model;

import lk.ijse.phoneshop.dao.CrudDAO;
import lk.ijse.phoneshop.dao.custom.impl.OrderDAOImpl;
import lk.ijse.phoneshop.db.DBConnection;
import lk.ijse.phoneshop.dto.OrderDTO;
import lk.ijse.phoneshop.dto.PlaceOrderDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class PlaceOrderM {
    /*public static boolean placeOrder(PlaceOrderDTO placeOrderDTO) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
     CrudDAO<OrderDTO,String> orderDAO = new OrderDAOImpl();
            boolean saveOrder = orderDAO.save(new OrderDTO(placeOrderDTO.getOrderId(), LocalDate.now(), LocalTime.now(), placeOrderDTO.getCustomerId()));
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
    }*/
}
