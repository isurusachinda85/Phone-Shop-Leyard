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
import lk.ijse.phoneshop.bo.BOFactory;
import lk.ijse.phoneshop.bo.custom.RepairBO;
import lk.ijse.phoneshop.dto.ItemDTO;
import lk.ijse.phoneshop.dto.RepairDTO;
import lk.ijse.phoneshop.tm.RepairTM;
import lk.ijse.phoneshop.dto.CustomerDTO;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;
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

    private final RepairBO repairBO = (RepairBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REPAIR);


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadCustomerId();
        loadItemCode();
        loadState();
        loadDate();
        loadTime();
        loadData();
        setCellValueFactory();
        getNextRepairID();
    }

    @FXML
        //add repair
    void repairOnAction(ActionEvent event) {
        String repairId = txtRepairNo.getText();
        String customerName = txtCustomerName.getText();
        int mobile = Integer.parseInt(txtCusMobil.getText());
        String deviceName = txtDiviceName.getText();
        String problem = txtProblem.getText();
        double price = Double.parseDouble(txtPrice.getText());
        double amount = Double.parseDouble(txtAmount.getText());
        double due = Double.parseDouble(lblDue.getText());
        String state = cmbState.getValue();
        LocalDate date = LocalDate.now();
        String cusId = cmbCusId.getValue();

        try {

            boolean saveRepair = repairBO.saveRepair(new RepairDTO(repairId, customerName, mobile, deviceName, problem, price, amount, due, state, String.valueOf(date),cusId));

            if (saveRepair) {
                Notifications notification = Notifications.create().title("Success").text("Repair Added Success").graphic(null)
                        .hideAfter(Duration.seconds(8))
                        .position(Pos.BOTTOM_RIGHT);
                notification.showConfirm();
            } else {
                System.out.println("Fail");
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        loadData();
        clearTextOnAction(event);
        getNextRepairID();
    }

    //get all repair data
    public void loadData() {
        ObservableList<RepairTM> repairList = FXCollections.observableArrayList();
        repairList.clear();

        try {
            ArrayList<RepairDTO> allRepairDTO = repairBO.getAllRepair();
            for (RepairDTO repairs : allRepairDTO) {
                Button button = new Button("Delete");
                RepairTM tm = new RepairTM(repairs.getRepairNo(), repairs.getCustomerName(), repairs.getPhoneNo(),
                        repairs.getDeviceName(), repairs.getDeviceProblem(), repairs.getPrice(), repairs.getAmount(),
                        repairs.getDue(), repairs.getState(), button);
                repairList.add(tm);
                tblRepair.setItems(repairList);

                button.setOnAction((e -> {
                    ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.orElse(no) == ok) {
                        tblRepair.getItems().removeAll(tblRepair.getSelectionModel().getSelectedItem());
                    }
                    //delete repair
                    String rid = repairs.getRepairNo();
                    try {
                        boolean deleteRepair = repairBO.deleteRepair(rid);
                        if (deleteRepair) {
                            getNextRepairID();
                            System.out.println("delete");
                        } else {
                            System.out.println("no");
                        }
                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                }));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    public void setCellValueFactory() {
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
        double balance = repairPrice - Double.parseDouble(txtAmount.getText());
        lblDue.setText(String.valueOf(balance));
    }

    private void loadTime() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());
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
        ObservableList<String> state = FXCollections.observableArrayList();
        state.add("Complete");
        state.add("Activate");
        cmbState.setItems(state);
    }

    private void loadItemCode() {
        ObservableList<String> itemIdList = FXCollections.observableArrayList();
        try {
            ArrayList<ItemDTO> allPhone = repairBO.getAllItem();
            for (ItemDTO itemDTO : allPhone) {
                itemIdList.add(itemDTO.getItemCode());
                cmbItemCode.setItems(itemIdList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadCustomerId() {
        ObservableList<String> customerIdList = FXCollections.observableArrayList();
        try {
            ArrayList<CustomerDTO> all = repairBO.getAllCustomer();
            for (CustomerDTO customerDTO : all) {
                customerIdList.add(customerDTO.getId());
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
        getNextRepairID();
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
            CustomerDTO customerDTO = repairBO.searchCustomer(cusId);
            fileCustomer(customerDTO);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    public void fileCustomer(CustomerDTO customerDTO) {
        txtCustomerName.setText(customerDTO.getName());
        txtCusMobil.setText(customerDTO.getPhoneNo());
    }

    @FXML
    void itemCodeOnAction(ActionEvent event) {
        String itemCode = cmbItemCode.getValue();
        try {
            ItemDTO itemDTO = repairBO.searchItem(itemCode);
            filItem(itemDTO);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void filItem(ItemDTO itemDTO) {
        txtDiviceName.setText(itemDTO.getName());
    }

    @FXML
    void txtSearch(ActionEvent event) {
        String id = txtRepairNo.getText();
        try {
            RepairDTO repairDTO = repairBO.searchRepair(id);
            if (repairDTO != null) {
                txtCustomerName.setText(repairDTO.getCustomerName());
                txtCusMobil.setText(String.valueOf(repairDTO.getPhoneNo()));
                txtDiviceName.setText(repairDTO.getDeviceName());
                txtProblem.setText(repairDTO.getDeviceProblem());
                txtPrice.setText(String.valueOf(repairDTO.getPrice()));
                txtAmount.setText(String.valueOf(repairDTO.getAmount()));
                lblDue.setText(String.valueOf(repairDTO.getDue()));
                cmbState.setValue(repairDTO.getState());
            } else {
                new Alert(Alert.AlertType.WARNING, "Not Found Repair !").show();
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
        try {
            boolean updateRepair = repairBO.updateRepair(new RepairDTO(id, amount, due, state));
            if (updateRepair) {
                Notifications notification = Notifications.create().title("Success").text("Repair Update Success").graphic(null)
                        .hideAfter(Duration.seconds(8))
                        .position(Pos.BOTTOM_RIGHT);
                notification.showConfirm();
            } else {
                System.out.println("NotUpdate");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        loadData();
        clearTextOnAction(event);
        getNextRepairID();
    }

    private void getNextRepairID() {
        try {
            String repairID = repairBO.getNextRepairId();
            txtRepairNo.setText(repairID);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
