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
import lk.ijse.phoneshop.bo.BOFactory;
import lk.ijse.phoneshop.bo.custom.ItemBO;
import lk.ijse.phoneshop.dto.ItemDTO;
import lk.ijse.phoneshop.tm.ItemTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
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

    private ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCmbWarranty();
        setCmbCategory();
        loadItem();
        setCellValueFactory();

    }
    //save phone
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

        try {

            boolean itemAdd = itemBO.saveItem(new ItemDTO(itemCode,brand,modalNo,name,price,warranty,qty,category));

            if (!itemAdd){
                new Alert(Alert.AlertType.WARNING, "Added Fail !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        loadItem();
        clearTextOnAction(event);
    }
    //load allPhone
    public void loadItem(){
        ObservableList <ItemTM> itemList = FXCollections.observableArrayList();
        itemList.clear();

        try {
            ArrayList<ItemDTO> allPhone = itemBO.getAllPhone();

            for(ItemDTO it : allPhone){
                Button button = new Button("Delete");
                ItemTM tm = new ItemTM(it.getItemCode(),it.getBrand(),it.getModalNo(),it.getName(),it.getPrice(),it.getWarranty(),
                        it.getQty(),it.getCategory(),button);
                itemList.add(tm);
                tblItem.setItems(itemList);

                //delete phone
                button.setOnAction((e->{
                    ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.orElse(no) == ok) {
                        tblItem.getItems().removeAll(tblItem.getSelectionModel().getSelectedItem());
                    }
                    String code = tm.getItemCode();

                    try {
                        boolean deletePhone = itemBO.deleteItem(code);
                        if (deletePhone) {
                            new Alert(Alert.AlertType.CONFIRMATION,"Delete Item !").show();
                        }else{
                            new Alert(Alert.AlertType.WARNING, "No Phone !").show();
                        }
                    } catch (SQLException | ClassNotFoundException throwable) {
                        throwable.printStackTrace();
                    }
                }));
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
        String code = txtItemCode.getText();

        try {
            ItemDTO itemDTO = itemBO.searchItem(code);
            if (itemDTO != null) {
                txtBrand.setText(itemDTO.getBrand());
                txtModalNo.setText(itemDTO.getModalNo());
                txtName.setText(itemDTO.getName());
                txtPrice.setText(String.valueOf(itemDTO.getPrice()));
                cmbWarrenty.setValue(itemDTO.getWarranty());
                txtQty.setText(String.valueOf(itemDTO.getQty()));
                cmbCategory.setValue(itemDTO.getCategory());
            }else{
                new Alert(Alert.AlertType.WARNING, "Not Item Customer !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        txtBrand.requestFocus();
    }
    @FXML
    void brandOnAction(ActionEvent event) {
        txtModalNo.requestFocus();
    }
}
