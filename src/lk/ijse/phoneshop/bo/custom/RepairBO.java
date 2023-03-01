package lk.ijse.phoneshop.bo.custom;

import lk.ijse.phoneshop.bo.SuperBO;
import lk.ijse.phoneshop.dto.CustomerDTO;
import lk.ijse.phoneshop.dto.ItemDTO;
import lk.ijse.phoneshop.dto.RepairDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RepairBO extends SuperBO {
    boolean saveRepair(RepairDTO repairDTO) throws SQLException, ClassNotFoundException ;

    ArrayList<RepairDTO> getAllRepair() throws SQLException, ClassNotFoundException ;

    boolean deleteRepair(String rid) throws SQLException, ClassNotFoundException ;

    ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException ;

    ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException ;

    CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException ;

    ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException ;

    RepairDTO searchRepair(String id) throws SQLException, ClassNotFoundException ;

    boolean updateRepair(RepairDTO repairDTO) throws SQLException, ClassNotFoundException ;

    String getNextRepairId() throws SQLException, ClassNotFoundException ;

}
