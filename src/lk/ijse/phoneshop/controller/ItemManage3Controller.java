package lk.ijse.phoneshop.controller;


import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lk.ijse.phoneshop.dao.ItemDAO;
import lk.ijse.phoneshop.dao.ItemDAOImpl;
import lk.ijse.phoneshop.model.ItemM;
import lk.ijse.phoneshop.tm.ItemTM;
import lk.ijse.phoneshop.to.Item;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ItemManage3Controller implements Initializable {
    public AnchorPane backItemPane;
    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXComboBox<String> cmbCategory;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXComboBox<String> cmbWarranty;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtBrand;

    @FXML
    private JFXTextField txtModalNo;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private TableView<ItemTM> tblParts;

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

    @FXML
    private TableColumn<?, ?> colAction;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCmbCategory();
        setCmbWarranty();
        loadItem();
        setCellValueFactory();
    }
    @FXML
    //save parts
    void saveOnAction(ActionEvent event) {
        String itemCode = txtItemCode.getText();
        String brand = txtBrand.getText();
        String modalNo = txtModalNo.getText();
        String name = txtName.getText();
        double price = Double.parseDouble(txtPrice.getText());
        String warranty = cmbWarranty.getValue();
        int qty =Integer.parseInt(txtQty.getText());
        String category = cmbCategory.getValue();

        try {
            ItemDAO itemDAO = new ItemDAOImpl();
            boolean itemAdd = itemDAO.itemAdd(new Item(itemCode,brand,modalNo,name,price,warranty,qty,category));

            if (!itemAdd){
                new Alert(Alert.AlertType.WARNING, "Added Fail !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        loadItem();
        clearTextOnAction(event);
    }
    //load parts
    public void loadItem(){
        ObservableList <ItemTM> itemList = FXCollections.observableArrayList();
        itemList.clear();

        try {
            ItemDAO itemDAO = new ItemDAOImpl();
            ArrayList<Item> allParts = itemDAO.loadParts();
            for(Item it : allParts){
                Button button = new Button("Delete");
                ItemTM tm = new ItemTM(it.getItemCode(),it.getBrand(),it.getModalNo(),it.getName(),it.getPrice(),it.getWarranty(),
                        it.getQty(),it.getCategory(),button);
                itemList.add(tm);
                tblParts.setItems(itemList);
                //delete phone
                button.setOnAction((e->{
                    ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.orElse(no) == ok) {
                        tblParts.getItems().removeAll(tblParts.getSelectionModel().getSelectedItem());
                    }
                    String code = tm.getItemCode();

                    try {
                        boolean deletePhone = itemDAO.deleteItem(code);
                        if (deletePhone) {
                            new Alert(Alert.AlertType.CONFIRMATION,"Delete phone !").show();
                        }else{
                            new Alert(Alert.AlertType.WARNING, "No Phone !").show();
                        }
                    } catch (SQLException | ClassNotFoundException throwable) {
                        throwable.printStackTrace();
                    }
                }));
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
        colAction.setCellValueFactory(new PropertyValueFactory<>("delete"));
    }
    public void setCmbWarranty(){
        ObservableList<String> warranty = FXCollections.observableArrayList();
        warranty.add("1 Year");
        warranty.add("2 Year");
        warranty.add("3 Year");
        warranty.add("No Warranty");
        cmbWarranty.setItems(warranty);
    }
    public void setCmbCategory(){
        ObservableList<String>category = FXCollections.observableArrayList();
        category.add("Phone");
        category.add("Phone Parts ");
        category.add("Accessories");
        cmbCategory.setItems(category);
    }
    public void backOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/phoneshop/view/ItemManage");
    }
    private void setUi(String ui) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        backItemPane.getChildren().clear();
        backItemPane.getChildren().add(load);
    }
    @FXML
    void clearTextOnAction(ActionEvent event) {
        txtItemCode.clear();
        txtBrand.clear();
        txtModalNo.clear();
        txtName.clear();
        txtPrice.clear();
        cmbWarranty.getSelectionModel().clearSelection();
        txtQty.clear();
        cmbCategory.getSelectionModel().clearSelection();
    }


    @FXML
    void brndOnAction(ActionEvent event) {
        txtModalNo.requestFocus();
    }


    @FXML
    void itemCodeOnAction(ActionEvent event) {
        String code = txtItemCode.getText();

        try {
            ItemDAO itemDAO = new ItemDAOImpl();
            Item item = itemDAO.searchItem(code);
            if (item != null) {
                txtBrand.setText(item.getBrand());
                txtModalNo.setText(item.getModalNo());
                txtName.setText(item.getName());
                txtPrice.setText(String.valueOf(item.getPrice()));
                cmbWarranty.setValue(item.getWarranty());
                txtQty.setText(String.valueOf(item.getQty()));
                cmbCategory.setValue(item.getCategory());
            }else{
                new Alert(Alert.AlertType.WARNING, "Not Item Customer !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        txtBrand.requestFocus();
    }

    @FXML
    void modalNoOnAction(ActionEvent event) {
        txtName.requestFocus();
    }

    @FXML
    void nameOnAction(ActionEvent event) {
        txtPrice.requestFocus();
    }

    @FXML
    void priceOnAction(ActionEvent event) {
        cmbWarranty.requestFocus();
    }

    @FXML
    void qtyOnAction(ActionEvent event) {
        cmbCategory.requestFocus();
    }

    @FXML
    void warrantyOnAction(ActionEvent event) {
        txtQty.requestFocus();
    }
}
