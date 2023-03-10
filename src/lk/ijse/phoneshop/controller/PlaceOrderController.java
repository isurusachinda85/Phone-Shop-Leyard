package lk.ijse.phoneshop.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.phoneshop.bo.BOFactory;
import lk.ijse.phoneshop.bo.custom.PlaceOrderBO;
import lk.ijse.phoneshop.dto.*;
import lk.ijse.phoneshop.tm.PlaceOrderTM;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

public class PlaceOrderController {

    public Label lblCusName;
    public Label lblCusMobile;
    public Label lblItemName;
    public Label lblQty;
    public TableColumn colQty;
    public Label lblPrice;
    public Label lblCategory;
    public JFXComboBox cmbPayment;
    public TableColumn colItemName;
    public TableColumn colWarranty;
    public TableColumn colCategory;
    public Label lblSubTotal;
    public JFXTextField txtCash;
    public Label lblBalance;
    public JFXTextField texOrderId;

    public Label lblNextOrderId;
    @FXML
    private AnchorPane pane;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;




    @FXML
    private JFXComboBox<String> cmbCustomerId;


    @FXML
    private JFXComboBox<String> cmbItemCode;

    @FXML
    private TextField txtQty;

    @FXML
    private TableView<PlaceOrderTM> tblOrderCart;

    @FXML
    private TableColumn<?, ?> colItemCode;




    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colAction;

    private PlaceOrderBO placeOrderBO = (PlaceOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PLACEORDER);

    
    public void initialize(){
        loadDate();
        loadTime();
        loadCustomerId();
        loadItemCode();
        loadPaymentType();
        setCellValueFactory();
        loadNextOrderId();
    }



    private void loadTime() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO,e->{
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(currentTime.getHour()+":"+currentTime.getMinute()+":"+currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
                );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void loadDate() {
        LocalDate date = LocalDate.now();
        lblDate.setText(String.valueOf(date));
    }

    private void loadCustomerId(){
        ObservableList<String>customerIdList = FXCollections.observableArrayList();
        try {

            ArrayList<CustomerDTO> all = placeOrderBO.loadCustomerId();
            for (CustomerDTO customerDTO : all) {
                customerIdList.add(customerDTO.getId());
                cmbCustomerId.setItems(customerIdList);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void loadItemCode(){
        ObservableList<String>itemIdList = FXCollections.observableArrayList();
        try {
            ArrayList<ItemDTO> all = placeOrderBO.loadItemCode();
            for (ItemDTO itemDTO : all) {
                itemIdList.add(itemDTO.getItemCode());
                cmbItemCode.setItems(itemIdList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    private ObservableList<PlaceOrderTM> list = FXCollections.observableArrayList();
    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String code = cmbItemCode.getValue();
        String itemName = lblItemName.getText();
        double unitPrice = Double.parseDouble(lblPrice.getText());
        int qty =Integer.parseInt(txtQty.getText());
        String category = lblCategory.getText();
        double total = unitPrice * qty;

        Button deleteButton = new Button("Delete");
        txtQty.clear();
        if(!list.isEmpty()){
            for (int i = 0;i<tblOrderCart.getItems().size();i++){
                if(colItemCode.getCellData(i).equals(code)){
                    qty+=(int) colQty.getCellData(i);
                    total=unitPrice*qty;

                    list.get(i).setQty(qty);
                    list.get(i).setTotal(total);
                    tblOrderCart.refresh();
                    return;

                }

            }
        }

        deleteButton.setOnAction((e)->{
            ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.orElse(no) == ok) {

                tblOrderCart.getItems().removeAll(tblOrderCart.getSelectionModel().getSelectedItem());
            }
            calculateTotal();
        });
        list.add(new PlaceOrderTM(code,itemName,unitPrice,qty,category,total,deleteButton));
        tblOrderCart.setItems(list);


        calculateTotal();
    }

    private void calculateTotal(){
        double total = 0;
        for (PlaceOrderTM tm:list) {
            total+=tm.getTotal();
        }
        lblSubTotal.setText(String.valueOf(total));
    }

    public void cashOnKeyReleased(KeyEvent keyEvent) {
        double cash = Double.parseDouble(txtCash.getText());
        double balance = cash-Double.parseDouble(lblSubTotal.getText());
        lblBalance.setText(String.valueOf(balance));
    }
    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory("code"));
        colItemName.setCellValueFactory(new PropertyValueFactory("itemName"));
        colQty.setCellValueFactory(new PropertyValueFactory("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory("total"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colAction.setCellValueFactory(new PropertyValueFactory("deleteButton"));
    }
    private void loadPaymentType() {
        ObservableList<String>payment = FXCollections.observableArrayList();
        payment.addAll("Card","Cash");
        cmbPayment.setItems(payment);
    }

    @FXML
    void btnNewCustomerOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/phoneshop/view/CustomerManage.fxml"))));
        stage.show();
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        String orderId = lblNextOrderId.getText();
        String cusId = cmbCustomerId.getValue();

        ArrayList<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();

        for(int i=0;i<tblOrderCart.getItems().size();i++){
            PlaceOrderTM tm = list.get(i);
            orderDetailDTOS.add(new OrderDetailDTO(orderId,tm.getCode(),tm.getQty(),tm.getItemName(),tm.getUnitPrice(),tm.getCategory()));
        }
        PlaceOrderDTO placeOrderDTO = new PlaceOrderDTO(cusId,orderId, orderDetailDTOS);


        try {
            boolean order = placeOrderBO.placeOrder(placeOrderDTO);
            loadNextOrderId();
            if (order){
                list.clear();
                Notifications notification = Notifications.create().title("Success").text("Order Success").graphic(null)
                        .hideAfter(Duration.seconds(8))
                        .position(Pos.BOTTOM_RIGHT);
                notification.showConfirm();
            }else {
                Notifications notification = Notifications.create().title("Success").text("Order Success").graphic(null)
                        .hideAfter(Duration.seconds(8))
                        .position(Pos.BOTTOM_RIGHT);
                notification.showError();
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    private void loadNextOrderId(){
        try {
            String nextOrderId = placeOrderBO.getNextOrderId();
            lblNextOrderId.setText(nextOrderId);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }
    public void cmbCustomerIdOnAction(ActionEvent actionEvent){
        String cusId = cmbCustomerId.getValue();
        try {
            CustomerDTO customerDTO = placeOrderBO.searchCustomer(cusId);
            fileCustomer(customerDTO);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }
    @FXML
    void cmbItemOnAction(ActionEvent event) {
        String itemCode = cmbItemCode.getValue();
        try {
            ItemDTO itemDTO = placeOrderBO.searchItem(itemCode);
            filItem(itemDTO);
            txtQty.requestFocus();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void fileCustomer(CustomerDTO customerDTO){
        lblCusName.setText(customerDTO.getName());
        lblCusMobile.setText(customerDTO.getPhoneNo());
    }
    public void filItem(ItemDTO itemDTO){
        lblItemName.setText(itemDTO.getName());
        lblPrice.setText(String.valueOf(itemDTO.getPrice()));
        lblCategory.setText(itemDTO.getCategory());
        lblQty.setText(String.valueOf(itemDTO.getQty()));
    }
    @FXML
    void txtQtyOnAction(ActionEvent event) {
        btnAddToCartOnAction(event);
    }

}
