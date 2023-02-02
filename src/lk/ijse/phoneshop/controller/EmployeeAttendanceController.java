package lk.ijse.phoneshop.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.phoneshop.model.AttendanceM;
import lk.ijse.phoneshop.model.EmployeeM;
import lk.ijse.phoneshop.tm.AttendanceTM;
import lk.ijse.phoneshop.tm.EmployeeTM;
import lk.ijse.phoneshop.to.Attendance;
import lk.ijse.phoneshop.to.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class EmployeeAttendanceController {

    @FXML
    private Label lblAttenId;

    @FXML
    private Label lblName;

    @FXML
    private JFXDatePicker txtDate;

    @FXML
    private JFXTimePicker txtInTime;

    @FXML
    private JFXTimePicker txtOutTime;

    @FXML
    private JFXComboBox<String> cmbState;

    @FXML
    private JFXComboBox<String> cmbEmployeeId;

    @FXML
    private TableView<AttendanceTM> tblAttendance;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colInTime;

    @FXML
    private TableColumn<?, ?> colOutTime;

    @FXML
    private TableColumn<?, ?> colState;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colAction;

    public void initialize(){
        loadEmployeeId();
        loadNextAttendanceId();
        setCmbState();
        loadAllAttendance();
        setCellValueFactory();
    }

    @FXML
    void attendanceSaveOnAction(ActionEvent event) {
        String attendanceId = lblAttenId.getText();
        String employeeId = cmbEmployeeId.getValue();
        String employeeName = lblName.getText();
        LocalDate date = LocalDate.from(txtDate.getValue());
        String state = cmbState.getValue();
        LocalTime inTime = LocalTime.from(txtInTime.getValue());
        LocalTime outTime = LocalTime.from(txtOutTime.getValue());

        Attendance attendance = new Attendance(attendanceId,employeeId,employeeName,String.valueOf(date),state,String.valueOf(inTime),String.valueOf(outTime));
        try {
            boolean attendanceSave = AttendanceM.attendanceSave(attendance);
            loadNextAttendanceId();
            if (!attendanceSave){
                new Alert(Alert.AlertType.WARNING, "Added Fail !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        loadAllAttendance();
    }
    public void loadAllAttendance(){
        ObservableList <AttendanceTM> attendanceList = FXCollections.observableArrayList();
        ArrayList<Attendance> list = new ArrayList<>();

        list.clear();
        attendanceList.clear();
        try {
            ResultSet resultSet = AttendanceM.getAllAttendance();
            while (resultSet.next()){
                list.add(new Attendance(
                        resultSet.getString("aid"),
                        resultSet.getString("eid"),
                        resultSet.getString("employeeName"),
                        resultSet.getString("date"),
                        resultSet.getString("state"),
                        resultSet.getString("inTime"),
                        resultSet.getString("outTime")));
            }
            for(Attendance attendance : list){
                Button button = new Button("Delete");
                AttendanceTM tm = new AttendanceTM(attendance.getAttendanceId(),attendance.getEmployeeId(),attendance.getName(),
                        attendance.getDate(),attendance.getSate(),attendance.getInTime(),attendance.getOutTime(),button);
                attendanceList.add(tm);
                tblAttendance.setItems(attendanceList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }
    public void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("attendanceId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colInTime.setCellValueFactory(new PropertyValueFactory<>("inTime"));
        colOutTime.setCellValueFactory(new PropertyValueFactory<>("outTime"));
        colState.setCellValueFactory(new PropertyValueFactory<>("sate"));
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("delete"));
    }
    public void loadEmployeeId(){
        try {
            ObservableList<String>data = FXCollections.observableArrayList();
            ResultSet resultSet = AttendanceM.loadEmployeeId();
            while (resultSet.next()){
                data.add(resultSet.getString(1));
                cmbEmployeeId.setItems(data);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setCmbState(){
        ObservableList<String>state = FXCollections.observableArrayList();
        state.add("Present");
        state.add("Absent");
        cmbState.setItems(state);
    }

    @FXML
    void clearTextOnAction(ActionEvent event) {
        lblName.setText("");
        txtDate.getEditor().clear();
        txtInTime.getEditor().clear();
        txtOutTime.getEditor().clear();
        cmbState.getSelectionModel().clearSelection();
        cmbEmployeeId.getSelectionModel().clearSelection();

    }

    @FXML
    void dateOnAction(ActionEvent event) {
        cmbState.requestFocus();
    }

    @FXML
    void employeeIdOnAction(ActionEvent event) {
        String id = cmbEmployeeId.getValue();
        try {
            Employee employee = AttendanceM.searchEmployee(id);

            filName(employee);
            txtDate.requestFocus();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }
    public void filName(Employee employee){
        lblName.setText(employee.getName());
    }

    private void loadNextAttendanceId(){
        try {
            String aId = AttendanceM.getNextAttendanceId();
            lblAttenId.setText(aId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void stateOnAction(ActionEvent event) {
        txtInTime.requestFocus();
    }

}
