package lk.ijse.phoneshop.dao.custom.impl;

import lk.ijse.phoneshop.dao.SQLUtil;
import lk.ijse.phoneshop.dao.custom.OrderDetailDAO;
import lk.ijse.phoneshop.dto.CartDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public boolean save(CartDetail cartDetail) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO OrderDetail VALUES(?, ?, ?, ?,?)";
        return SQLUtil.execute(sql, cartDetail.getOrderId(), cartDetail.getCode(), cartDetail.getItemName(),cartDetail.getQty(), cartDetail.getUnitPrice());
    }

    @Override
    public ArrayList<CartDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public CartDetail search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(CartDetail dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getNextId(String s) {
        return null;
    }

}