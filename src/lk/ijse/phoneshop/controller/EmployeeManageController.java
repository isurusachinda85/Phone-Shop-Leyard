package lk.ijse.phoneshop.controller;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.phoneshop.dao.CrudDAO;
import lk.ijse.phoneshop.dao.EmployeeDAO;
import lk.ijse.phoneshop.dao.EmployeeDAOImpl;
import lk.ijse.phoneshop.tm.EmployeeTM;
import lk.ijse.phoneshop.dto.Employee;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;


public class EmployeeManageController implements Initializable {

    public JFXButton btn;

    public JFXTextField txtEmployee;


    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtPhoneNo;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXDatePicker txtDate;

    @FXML
    private JFXComboBox<String> cmbJobRole;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private TableView<EmployeeTM> tblEmployee;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colPhoneNo;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colJobRole;

    @FXML
    private TableColumn<?, ?> colAction;

    private CrudDAO<Employee,String> employeeDAO = new EmployeeDAOImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCmbJobRole();
        loadData();
        loadNextEmployeeId();
        setCellValueFactory();
    }


    @FXML
    void clearOnAction(ActionEvent event) {

        txtName.clear();
        txtAddress.clear();
        txtPhoneNo.clear();
        txtEmail.clear();
        txtUserName.clear();
        txtPassword.clear();
        txtDate.getEditor().clear();
        cmbJobRole.getSelectionModel().clearSelection();
    }

    //save employee
    @FXML
    void saveOnAction(ActionEvent event) {
        String id = txtEmployee.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        int phoneNo = Integer.parseInt(txtPhoneNo.getText());
        String email = txtEmail.getText();
        LocalDate date = LocalDate.from(txtDate.getValue());
        String jobRole = cmbJobRole.getValue();
        String userName = txtUserName.getText();
        String password = txtPassword.getText();

        try {
            boolean saveEmployee = employeeDAO.save(new Employee(id,name,address,phoneNo,email,String.valueOf(date),jobRole,userName,password));
            loadNextEmployeeId();
            if (!saveEmployee){
                new Alert(Alert.AlertType.WARNING, "Added Fail !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        loadData();
        clearOnAction(event);
    }
    //update employee
    public void updateOnAction(ActionEvent actionEvent) {
        String id = txtEmployee.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String phoneNo = txtPhoneNo.getText();
        String email = txtEmail.getText();
        LocalDate birth = txtDate.getValue();
        String jobRole = cmbJobRole.getValue();
        String userName = txtUserName.getText();
        String password = txtPassword.getText();

        try {
            boolean updateEmployee = employeeDAO.update(new Employee(id, name, address, Integer.parseInt(phoneNo), email, String.valueOf(birth), jobRole, userName, password));
            if (updateEmployee){
                new Alert(Alert.AlertType.CONFIRMATION,"Update employee !").show();
            }else {
                new Alert(Alert.AlertType.WARNING, "No employee !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        loadData();
        clearOnAction(actionEvent);
        loadNextEmployeeId();
    }
    //search employee
    public void serchOnAction(ActionEvent actionEvent) {
        String id = txtEmployee.getText();
        try {
            Employee employee = employeeDAO.search(id);
            if(employee!=null){
                txtName.setText(employee.getName());
                txtAddress.setText(employee.getAddress());
                txtPhoneNo.setText(String.valueOf(employee.getPhoneNo()));
                txtEmail.setText(employee.getEmail());
                txtDate.setValue(LocalDate.parse(employee.getDateOfBirth()));
                cmbJobRole.setValue(employee.getJobRole());
                txtUserName.setText(employee.getUserName());
            }else {
                new Alert(Alert.AlertType.WARNING, "Not Found Employee !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }
    //load employee
    public void loadData(){
        ObservableList <EmployeeTM> employeeList = FXCollections.observableArrayList();
        employeeList.clear();

        try {
            ArrayList<Employee> allEmployee = employeeDAO.getAll();

            for(Employee em : allEmployee){
                Button button = new Button("Delete");
                EmployeeTM tm = new EmployeeTM(em.getId(),em.getName(),em.getAddress(),em.getPhoneNo(),em.getEmail(),em.getDateOfBirth(),
                        em.getJobRole(),em.getUserName(),em.getPassword(),button);
                employeeList.add(tm);
                tblEmployee.setItems(employeeList);

                button.setOnAction((e->{
                    ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.orElse(no) == ok) {

                        tblEmployee.getItems().removeAll(tblEmployee.getSelectionModel().getSelectedItem());
                    }
                    String id = em.getId();
                    //delete employee
                    try {
                        boolean deleteEmployee = employeeDAO.delete(id);
                        if (deleteEmployee){
                            loadNextEmployeeId();
                            System.out.println("Delete");
                        }else {
                            System.out.println("No");
                        }
                    } catch (SQLException | ClassNotFoundException throwables) {
                        System.out.println(e);
                    }

                }));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void setCmbJobRole(){
        ObservableList<String>list = FXCollections.observableArrayList();
        list.add("Manager");
        list.add("Cashier");
        list.add("Sales men");
        cmbJobRole.setItems(list);
    }
    public void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colPhoneNo.setCellValueFactory(new PropertyValueFactory<>("PhoneNo"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        colJobRole.setCellValueFactory(new PropertyValueFactory<>("JobRole"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("delete"));
    }

    private void loadNextEmployeeId(){
        try {
            String eId = employeeDAO.getNextId();
            txtEmployee.setText(eId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void dateOnAction(ActionEvent event) {
        cmbJobRole.requestFocus();
    }

    @FXML
    void emailOnAction(ActionEvent event) {
        txtDate.requestFocus();
    }


    @FXML
    void nameOnAction(ActionEvent event) {
        txtAddress.requestFocus();
    }

    @FXML
    void passwordOnAction(ActionEvent event) {
        btn.fire();
    }

    @FXML
    void phoneOnAction(ActionEvent event) {
        txtEmail.requestFocus();
    }

    @FXML
    void roleOnAction(ActionEvent event) {
        txtUserName.requestFocus();
    }

    @FXML
    void userNameOnAction(ActionEvent event) {
        txtPassword.requestFocus();
    }
    @FXML
    void addressOnAction(ActionEvent event) {
        txtPhoneNo.requestFocus();
    }

}
