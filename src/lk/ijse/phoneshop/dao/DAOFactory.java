package lk.ijse.phoneshop.dao;

import lk.ijse.phoneshop.dao.custom.impl.AttendanceDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }
    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOType{
        ATTENDANCE,CUSTOMER,EMPLOYEE,ITEM,ORDER,ORDERDETAILS,REPAIR,QUERYDAO
    }
    public void getDao(DAOType types){
        switch (types){
            case ATTENDANCE:
                return new AttendanceDAOImpl();
        }
    }
}
