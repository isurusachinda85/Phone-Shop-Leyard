package lk.ijse.phoneshop.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.phoneshop.model.EmployeeM;
import lk.ijse.phoneshop.model.ItemM;
import lk.ijse.phoneshop.tm.EmployeeTM;
import lk.ijse.phoneshop.tm.ItemTM;
import lk.ijse.phoneshop.to.Employee;
import lk.ijse.phoneshop.to.Item;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ItemManageController implements Initializable {
    public AnchorPane itemManagePane;
    public JFXTextField txtItemCode;

    @FXML
    private JFXComboBox<String> cmbCategory;

    @FXML
    private JFXTextField txtModalNo;

    @FXML
    private JFXTextField txtBrand;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXComboBox<String> cmbWarrenty;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtPrice;



    @FXML
    private TableView<ItemTM> tblItem;

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
    private TableColumn<?, ?> colWarrenty;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colAction;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCmbWarranty();
        setCmbCategory();
        loadItem();
        setCellValueFactory();

    }
    @FXML
    void itemAddOnAction(ActionEvent event) {
        String itemCode = txtItemCode.getText();
        String brand = txtBrand.getText();
        String modalNo = txtModalNo.getText();
        String name = txtName.getText();
        double price = Double.parseDouble(txtPrice.getText());
        String warranty = cmbWarrenty.getValue();
        int qty =Integer.parseInt(txtQty.getText());
        String category = cmbCategory.getValue();

        Item item = new Item(itemCode,brand,modalNo,name,price,warranty,qty,category);

        try {
            boolean itemAdd = ItemM.itemAdd(item);

            if (!itemAdd){
                new Alert(Alert.AlertType.WARNING, "Added Fail !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        loadItem();
    }
    public void loadItem(){
        ObservableList <ItemTM> itemList = FXCollections.observableArrayList();
        ArrayList<Item> list = new ArrayList<>();

        list.clear();
        itemList.clear();

        try {
            ResultSet resultSet = ItemM.loadAllPhone();
            while (resultSet.next()){
                list.add(new Item(
                        resultSet.getString("itemCode"),
                        resultSet.getString("brand"),
                        resultSet.getString("modalNo"),
                        resultSet.getString("itemName"),
                        resultSet.getDouble("price"),
                        resultSet.getString("warranty"),
                        resultSet.getInt("qty"),
                        resultSet.getString("category")));
            }
            for(Item it : list){
                Button button = new Button("Delete");
                ItemTM tm = new ItemTM(it.getItemCode(),it.getBrand(),it.getModalNo(),it.getName(),it.getPrice(),it.getWarranty(),
                        it.getQty(),it.getCategory(),button);
                itemList.add(tm);
                tblItem.setItems(itemList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }
    public void setCellValueFactory(){
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colModalNo.setCellValueFactory(new PropertyValueFactory<>("modalNo"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colWarrenty.setCellValueFactory(new PropertyValueFactory<>("warranty"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("delete"));
    }
    public void setCmbWarranty(){
        ObservableList<String>warranty = FXCollections.observableArrayList();
        warranty.add("1 Year");
        warranty.add("2 Year");
        warranty.add("3 Year");
        warranty.add("No Warranty");
        cmbWarrenty.setItems(warranty);
    }
    public void setCmbCategory(){
        ObservableList<String>category = FXCollections.observableArrayList();
        category.add("Phone");
        category.add("Phone Parts ");
        category.add("Accessories");
        cmbCategory.setItems(category);
    }

    public void addAccessoriesOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/phoneshop/view/ItemManage2");
    }

    public void addPartsOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/phoneshop/view/ItemManage3");
    }
    private void setUi(String ui) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        itemManagePane.getChildren().clear();
        itemManagePane.getChildren().add(load);
    }
    @FXML
    void clearTextOnAction(ActionEvent event) {
        txtItemCode.clear();
        txtBrand.clear();
        txtModalNo.clear();
        txtName.clear();
        txtPrice.clear();
        cmbWarrenty.getSelectionModel().clearSelection();
        txtQty.clear();
        cmbCategory.getSelectionModel().clearSelection();
    }

    @FXML
    void modalNoOnAction(ActionEvent event) {
        txtName.setText(txtBrand.getText()+""+txtModalNo.getText());
        txtPrice.requestFocus();
    }

    @FXML
    void priceOnAction(ActionEvent event) {
        cmbWarrenty.requestFocus();
    }

    @FXML
    void qtyOnAction(ActionEvent event) {
        cmbCategory.requestFocus();
    }

    @FXML
    void warrantyOnAction(ActionEvent event) {
        txtQty.requestFocus();
    }

    @FXML
    void itemCodeOnAction(ActionEvent event) {
        txtBrand.requestFocus();
    }
    @FXML
    void brandOnAction(ActionEvent event) {
        txtModalNo.requestFocus();
    }
}
