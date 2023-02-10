package lk.ijse.phoneshop.dao;

import lk.ijse.phoneshop.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }
    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    //public final static integer values
    public enum DAOType{
        ATTENDANCE,CUSTOMER,EMPLOYEE,ITEM,ORDER,ORDERDETAILS,REPAIR,QUERYDAO
    }
    public SuperDAO getDao(DAOType types){
        switch (types){
            case ATTENDANCE:
                return new AttendanceDAOImpl();
            case CUSTOMER:
                return new CustomerDAOImpl();
            case EMPLOYEE:
                return  new EmployeeDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDERDETAILS:
                return new OrderDetailDAOImpl();
            case REPAIR:
                return new RepairDAOImpl();
            case QUERYDAO:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }
}
