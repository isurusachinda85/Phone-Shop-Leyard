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
import lk.ijse.phoneshop.model.ItemM;
import lk.ijse.phoneshop.tm.ItemTM;
import lk.ijse.phoneshop.to.Item;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StockManageController {
    public AnchorPane stockPane;
    @FXML
    private TableView<Item> tblPhoneStock;

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

    public void initialize(){
        loadData();
        setCellValueFactory();
    }

    private void loadData() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = ItemM.loadAllPhone();
            while (resultSet.next()){
                itemList.add(new Item(
                        resultSet.getString("itemCode"),
                        resultSet.getString("brand"),
                        resultSet.getString("modalNo"),
                        resultSet.getString("itemName"),
                        resultSet.getDouble("price"),
                        resultSet.getString("warranty"),
                        resultSet.getInt("qty"),
                        resultSet.getString("category")));
            }
            tblPhoneStock.setItems(itemList);
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
