package lk.ijse.phoneshop.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import lk.ijse.phoneshop.dao.CustomerDAO;
import lk.ijse.phoneshop.dao.CustomerDAOImpl;
import lk.ijse.phoneshop.db.DBConnection;
import lk.ijse.phoneshop.model.CustomerM;
import lk.ijse.phoneshop.to.Customer;
import lk.ijse.phoneshop.tm.CustomerTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerManageController {

    public JFXTextField txtCusName;
    public JFXTextField txtCusAddress;
    public JFXTextField txtCusPhone;
    public JFXTextField txtCusEmail;
    public TableView<CustomerTM>tblCustomer;

    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colPhoneNo;
    public TableColumn colEmail;
    public Label lblEmailError;
    public TableColumn colAction;
    public JFXTextField txtSearch;

    public TableColumn colId;
    public Label lblNameError;
    public Label lblAddressError;
    public Label lblMobileError;
    public JFXTextField txtcCusId;


    private Matcher nameMatcher;
    private Matcher emailMatcher;
    private Matcher addressMatcher;
    private Matcher phoneNoMatcher;

    public void setPattern(){
        Pattern namePattern = Pattern.compile("^[a-z.\\sA-Z.\\s]{4,}$");
        nameMatcher = namePattern.matcher(txtCusName.getText());

        Pattern addressPattern = Pattern.compile("^[a-z.\\sA-Z.\\s]{4,}$");
        addressMatcher = addressPattern.matcher(txtCusAddress.getText());

        Pattern emailPattern = Pattern.compile("^([a-z0-9]{2,})([@])([a-z]{2,9})([.])([a-z]{2,})$");
        emailMatcher = emailPattern.matcher(txtCusEmail.getText());

        Pattern mobilePattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        phoneNoMatcher = mobilePattern.matcher(txtCusPhone.getText());

    }
    public void patternPerform(){
        if(nameMatcher.matches()) {
            if(emailMatcher.matches()) {
                if(addressMatcher.matches()) {
                    if(phoneNoMatcher.matches()) {
                        } else {
                            txtCusPhone.requestFocus();
                            txtCusPhone.setFocusColor(Paint.valueOf("Red"));
                            lblMobileError.setText("Invalid Mobile");
                        }
                    } else {
                        txtCusAddress.requestFocus();
                        txtCusAddress.setFocusColor(Paint.valueOf("Red"));
                        lblAddressError.setText("Invalid Address");
                    }
                } else {
                    txtCusEmail.requestFocus();
                    txtCusEmail.setFocusColor(Paint.valueOf("Red"));
                    lblEmailError.setText("Invalid Email");
                }
            } else {
                txtCusName.requestFocus();
                txtCusName.setFocusColor(Paint.valueOf("Red"));
                lblNameError.setText("Invalid Name");
            }
    }
    public void initialize()  {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("delete"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("upDate"));

        loadData();
        loadNextCustomerId();
        setPattern();
    }

    //Save Customer
    public void customerSaveOnAction(ActionEvent actionEvent){
        String id = txtcCusId.getText();
        String name = txtCusName.getText();
        String address = txtCusAddress.getText();
        String phoneNo = txtCusPhone.getText();
        String email = txtCusEmail.getText();

        patternPerform();
        try {
            CustomerDAO customerDAO = new CustomerDAOImpl();
            customerDAO.saveCustomer(new Customer(id,name,address,phoneNo,email));
            
            loadNextCustomerId();
        } catch (ClassNotFoundException | SQLException e) {
            new Alert(Alert.AlertType.WARNING, "Pleas File All data  !").show();
            System.out.println(e);
        }
        loadData();
        textFeildClear(actionEvent);

    }

    //load Customer
    public void loadData() {
        ObservableList <CustomerTM> customerList = FXCollections.observableArrayList();
        customerList.clear();
        try {

            CustomerDAO customerDAO = new CustomerDAOImpl();
            ArrayList<Customer>allCustomer =customerDAO.getAllCustomer();

            for (Customer c:allCustomer) {
                Button button = new Button("Delete");
                Button button1 = new Button("Up Date");
                CustomerTM tm = new CustomerTM(c.getId(),c.getName(),c.getAddress(),c.getPhoneNo(),c.getEmail(),button1,button);
                customerList.add(tm);
                tblCustomer.setItems(customerList);

                button.setOnAction((e->{
                    ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.orElse(no) == ok) {
                        tblCustomer.getItems().removeAll(tblCustomer.getSelectionModel().getSelectedItem());
                    }
                    String id = c.getId();
                    //delete Customer
                    try {
                        boolean deleteCustomer = customerDAO.deleteCustomer(id);
                        if (deleteCustomer){
                            loadNextCustomerId();
                            System.out.println("Delete");
                        }else {
                            System.out.println("No");
                        }
                    } catch (SQLException | ClassNotFoundException throwables) {
                        System.out.println(e);
                    }

                }));
            }

        } catch (ClassNotFoundException |SQLException e) {
            System.out.println(e);
        }
    }

    public void textFeildClear(ActionEvent actionEvent) {
        txtCusName.clear();
        txtCusAddress.clear();
        txtCusPhone.clear();
        txtCusEmail.clear();
    }

    private void loadNextCustomerId(){
        try {
            CustomerDAO customerDAO = new CustomerDAOImpl();
            String cusId = customerDAO.getNextCustomerId();
            txtcCusId.setText(cusId);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }



    public void nameOnAction(ActionEvent actionEvent) {
        txtCusEmail.requestFocus();
    }

    public void addressOnAction(ActionEvent actionEvent) {
        txtCusPhone.requestFocus();
    }

    public void emailOnAction(ActionEvent actionEvent) {
        txtCusAddress.requestFocus();
    }

    public void phoneOnAction(ActionEvent actionEvent) {
        customerSaveOnAction(actionEvent);
    }

    public void searchOnAction(ActionEvent actionEvent) {
    }
    @FXML
    void emailOnKeReles(KeyEvent event) {
        lblEmailError.setText("");
        txtCusEmail.setFocusColor(Paint.valueOf("Blue"));

        Pattern emailPattern = Pattern.compile("^([a-z0-9]{2,})([@])([a-z]{2,9})([.])([a-z]{2,})$");
        emailMatcher = emailPattern.matcher(txtCusEmail.getText());

        if (!emailMatcher.matches()){
            txtCusEmail.requestFocus();
            txtCusEmail.setFocusColor(Paint.valueOf("Red"));
            lblEmailError.setText("Invalid Email");
        }
    }

    

    @FXML
    void nameOnKeyRelese(KeyEvent event) {
        lblNameError.setText("");
        txtCusName.setFocusColor(Paint.valueOf("Blue"));

        Pattern namePattern = Pattern.compile("^[a-z.\\sA-Z.\\s]{4,}$");
        nameMatcher = namePattern.matcher(txtCusName.getText());

        if (!nameMatcher.matches()){
            txtCusName.requestFocus();
            txtCusName.setFocusColor(Paint.valueOf("Red"));
            lblNameError.setText("Invalid Name");
        }
    }

    @FXML
    void phoneNoOnKeyReles(KeyEvent event) {
        lblMobileError.setText("");
        txtCusPhone.setFocusColor(Paint.valueOf("Blue"));

        Pattern mobilePattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        phoneNoMatcher = mobilePattern.matcher(txtCusPhone.getText());

        if (!phoneNoMatcher.matches()){
            txtCusPhone.requestFocus();
            txtCusPhone.setFocusColor(Paint.valueOf("Red"));
            lblMobileError.setText("Invalid Mobile");
        }

    }


    public void addressOnKeyReles(KeyEvent keyEvent) {
        lblAddressError.setText("");
        txtCusAddress.setFocusColor(Paint.valueOf("Blue"));

        Pattern addressPattern = Pattern.compile("^[a-z.\\sA-Z.\\s]{4,}$");
        addressMatcher = addressPattern.matcher(txtCusAddress.getText());

        if (!addressMatcher.matches()){
            txtCusAddress.requestFocus();
            txtCusAddress.setFocusColor(Paint.valueOf("Red"));
            lblAddressError.setText("Invalid Address");
        }
    }

    public void searchOnKeyReleased(KeyEvent keyEvent) {

    }

    //update customer
    public void updateOnAction(ActionEvent actionEvent) {
        String id = txtcCusId.getText();
        String name = txtCusName.getText();
        String address = txtCusAddress.getText();
        String phoneNo = txtCusPhone.getText();
        String email = txtCusEmail.getText();


        try {
            CustomerDAO customerDAO = new CustomerDAOImpl();
            boolean updateCustomer = customerDAO.updateCustomer(new Customer(id, name, address, phoneNo, email));

            if (updateCustomer){
                new Alert(Alert.AlertType.CONFIRMATION,"Update customer !").show();
            }else {
                new Alert(Alert.AlertType.WARNING, "No Customer !").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        loadData();
        textFeildClear(actionEvent);
        loadNextCustomerId();
    }

    //search customer
    public void txtCusId(ActionEvent actionEvent) {
        String id= txtcCusId.getText();
        try {
            CustomerDAO customerDAO = new CustomerDAOImpl();
            Customer customer = customerDAO.searchCustomer(id);
            if (customer!=null){
                txtCusName.setText(customer.getName());
                txtCusEmail.setText(customer.getEmail());
                txtCusAddress.setText(customer.getAddress());
                txtCusPhone.setText(customer.getPhoneNo());
            }else {
                new Alert(Alert.AlertType.WARNING, "Not Found Customer !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
