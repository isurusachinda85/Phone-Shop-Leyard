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
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.phoneshop.model.CustomerM;
import lk.ijse.phoneshop.model.ItemM;
import lk.ijse.phoneshop.model.RepairM;
import lk.ijse.phoneshop.tm.AttendanceTM;
import lk.ijse.phoneshop.tm.CustomerTM;
import lk.ijse.phoneshop.tm.RepairTM;
import lk.ijse.phoneshop.to.Attendance;
import lk.ijse.phoneshop.to.Customer;
import lk.ijse.phoneshop.to.Item;
import lk.ijse.phoneshop.to.Repair;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RepairManageController implements Initializable {

    @FXML
    private TableView<RepairTM> tblRepair;

    @FXML
    private TableColumn<?, ?> colNo;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private TableColumn<?, ?> colDiviceName;

    @FXML
    private TableColumn<?, ?> colProblem;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colPay;

    @FXML
    private TableColumn<?, ?> colDue;

    @FXML
    private TableColumn<?, ?> colState;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private JFXComboBox<String> cmbCusId;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtCusMobil;

    @FXML
    private JFXTextField txtDiviceName;

    @FXML
    private JFXComboBox<String> cmbItemCode;

    @FXML
    private TextArea txtProblem;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtAmount;

    @FXML
    private Label lblDue;

    @FXML
    private JFXComboBox<String> cmbState;

    @FXML
    private JFXTextField txtRepairNo;
    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadCustomerId();
        loadItemCode();
        loadState();
        loadDate();
        loadTime();
        loadData();
        setCellValueFactory();
    }
    @FXML
    void repairOnAction(ActionEvent event) {
        String repairId = txtRepairNo.getText();
        String customerId = cmbCusId.getValue();
        String customerName = txtCustomerName.getText();
        int mobile = Integer.parseInt(txtCusMobil.getText());
        String itemCode = cmbItemCode.getValue();
        String deviceName = txtDiviceName.getText();
        String problem = txtProblem.getText();
        double price = Double.parseDouble(txtPrice.getText());
        double amount = Double.parseDouble(txtAmount.getText());
        double due = Double.parseDouble(lblDue.getText());
        String state = cmbState.getValue();
        LocalDate date = LocalDate.now();

        Repair repair = new Repair(repairId,customerId,customerName,mobile,itemCode,deviceName,problem,price,amount,due,state,String.valueOf(date));
        try {
            boolean saveRepair = RepairM.saveRepair(repair);
            if (saveRepair){
                Notifications notification = Notifications.create().title("Success").text("Repair Added Success").graphic(null)
                        .hideAfter(Duration.seconds(8))
                        .position(Pos.BOTTOM_RIGHT);
                notification.showConfirm();
            }else{
                System.out.println("Fail");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        loadData();
    }
    public void loadData(){
        ObservableList <RepairTM> repair = FXCollections.observableArrayList();
        ArrayList<Repair> list = new ArrayList<>();
        list.clear();
        repair.clear();

        try {
            ResultSet allRepair = RepairM.getAllRepair();
            while (allRepair.next()){
                list.add(new Repair(
                        allRepair.getString("repId"),
                        allRepair.getString("customerName"),
                        allRepair.getInt("phoneNo"),
                        allRepair.getString("deviceName"),
                        allRepair.getString("problem"),
                        allRepair.getDouble("repairPrice"),
                        allRepair.getDouble("amount"),
                        allRepair.getDouble("due"),
                        allRepair.getString("state")));
            }
            for(Repair repairs : list){
                Button button = new Button("Delete");
                RepairTM tm = new RepairTM(repairs.getRepairNo(),repairs.getCustomerName(),repairs.getPhoneNo(),
                        repairs.getDeviceName(),repairs.getDeviceProblem(),repairs.getPrice(),repairs.getAmount(),
                        repairs.getDue(),repairs.getState(),button);
                repair.add(tm);
                tblRepair.setItems(repair);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }
    public void setCellValueFactory(){
        colNo.setCellValueFactory(new PropertyValueFactory<>("repairNo"));
        colName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        colDiviceName.setCellValueFactory(new PropertyValueFactory<>("deviceName"));
        colProblem.setCellValueFactory(new PropertyValueFactory<>("deviceProblem"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colPay.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDue.setCellValueFactory(new PropertyValueFactory<>("due"));
        colState.setCellValueFactory(new PropertyValueFactory<>("state"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("delete"));
    }
    @FXML
    void amountOnKeReleased(KeyEvent event) {
        double repairPrice = Double.parseDouble(txtPrice.getText());
        double balance = repairPrice-Double.parseDouble(txtAmount.getText());
        lblDue.setText(String.valueOf(balance));
    }
    private void loadTime() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e->{
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

    private void loadState() {
        ObservableList<String>state = FXCollections.observableArrayList();
        state.add("Complete");
        state.add("Activate");
        cmbState.setItems(state);
    }

    private void loadItemCode() {
        ObservableList<String>itemIdList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = ItemM.loadAllPhone();
            while (resultSet.next()){
                itemIdList.add(resultSet.getString(1));
                cmbItemCode.setItems(itemIdList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadCustomerId() {
        ObservableList<String> customerIdList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = CustomerM.loadCustomerId();
            while (resultSet.next()){
                customerIdList.add(resultSet.getString(1));
                cmbCusId.setItems(customerIdList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void clearTextOnAction(ActionEvent event) {
        txtRepairNo.clear();
        cmbCusId.getSelectionModel().clearSelection();
        txtCustomerName.clear();
        txtCusMobil.clear();
        cmbItemCode.getSelectionModel().clearSelection();
        txtDiviceName.clear();
        txtProblem.clear();
        txtPrice.clear();
        txtAmount.clear();
        lblDue.setText("");
        cmbState.getSelectionModel().clearSelection();
    }

    @FXML
    void newCustomerOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/phoneshop/view/CustomerManage.fxml"))));
        stage.show();
    }


    @FXML
    void customerIdOnAction(ActionEvent event) {
        String cusId = cmbCusId.getValue();
        try {
            Customer customer =CustomerM.searchCustomer(cusId);
            fileCustomer(customer);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }
    public void fileCustomer(Customer customer){
        txtCustomerName.setText(customer.getName());
        txtCusMobil.setText(customer.getPhoneNo());
    }
    @FXML
    void itemCodeOnAction(ActionEvent event) {
        String itemCode = cmbItemCode.getValue();
        try {
            Item item = ItemM.searchItem(itemCode);
            filItem(item);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void filItem(Item item) {
        txtDiviceName.setText(item.getName());
    }
    @FXML
    void txtSearch(ActionEvent event) {
        String id = txtRepairNo.getText();
        try {
            Repair repair = RepairM.searchRepair(id);
            if (repair!=null){
                txtCustomerName.setText(repair.getCustomerName());
                txtCusMobil.setText(String.valueOf(repair.getPhoneNo()));
                txtDiviceName.setText(repair.getDeviceName());
                txtProblem.setText(repair.getDeviceProblem());
                txtPrice.setText(String.valueOf(repair.getPrice()));
                txtAmount.setText(String.valueOf(repair.getAmount()));
                lblDue.setText(String.valueOf(repair.getDue()));

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void updateOnAction(ActionEvent event) {
        String id = txtRepairNo.getText();
        double amount = Double.parseDouble(txtAmount.getText());
        double due = Double.parseDouble(lblDue.getText());
        String state = cmbState.getValue();

        Repair repair = new Repair(id,amount,due,state);
        try {
            boolean updateRepair = RepairM.updateRepair(repair);
            if (updateRepair){
                Notifications notification = Notifications.create().title("Success").text("Repair Update Success").graphic(null)
                        .hideAfter(Duration.seconds(8))
                        .position(Pos.BOTTOM_RIGHT);
                notification.showConfirm();
            }else{
                System.out.println("NotUpdate");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        loadData();
    }
}
