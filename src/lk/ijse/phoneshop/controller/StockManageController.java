package lk.ijse.phoneshop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.phoneshop.dao.CrudDAO;
import lk.ijse.phoneshop.dao.ItemDAO;
import lk.ijse.phoneshop.dao.ItemDAOImpl;
import lk.ijse.phoneshop.tm.ItemTM;
import lk.ijse.phoneshop.dto.Item;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockManageController {
    public AnchorPane stockPane;
    @FXML
    private TableView<ItemTM> tblPhoneStock;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colBrand;

    @FXML
    private TableColumn<?, ?> colModalNo;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colWarranty;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colCategory;

    private CrudDAO<Item,String> itemDAO = new ItemDAOImpl();

    public void initialize(){
        loadData();
        setCellValueFactory();
    }

    private void loadData() {
        ObservableList<ItemTM> itemList = FXCollections.observableArrayList();
        try {
            ArrayList<Item> allPhone = itemDAO.getAll();
            for (Item it : allPhone){
                ItemTM tm = new ItemTM(it.getItemCode(),it.getBrand(),it.getModalNo(),it.getName(),it.getPrice(),it.getWarranty(), it.getQty(),it.getCategory());
                itemList.add(tm);
                tblPhoneStock.setItems(itemList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void setCellValueFactory(){
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colModalNo.setCellValueFactory(new PropertyValueFactory<>("modalNo"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colWarranty.setCellValueFactory(new PropertyValueFactory<>("warranty"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
    }
    public void accessoriesStock(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/phoneshop/view/StockManage2");
    }

    public void partsStock(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/phoneshop/view/StockManage3");
    }
    private void setUi(String ui) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        stockPane.getChildren().clear();
        stockPane.getChildren().add(load);
    }
}
