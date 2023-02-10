package lk.ijse.phoneshop.bo;

import lk.ijse.phoneshop.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }
    public enum BOTypes{
        ATTENDANCE,CUSTOMER,EMPLOYEE,ITEM,REPAIR,STOCK,PLACEORDER
    }
    public SuperBO getBO(BOTypes types){
        switch (types){
            case ATTENDANCE:
                return new AttendanceBOImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case REPAIR:
                return new RepairBOImpl();
            case STOCK:
                return new StockManageBOImpl();
            case PLACEORDER:
                return new PlaceOrderBOImpl();
            default:
                return null;
        }
    }
}
