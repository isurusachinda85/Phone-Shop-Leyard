package lk.ijse.phoneshop.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.phoneshop.bo.BOFactory;
import lk.ijse.phoneshop.bo.custom.AttendanceBO;
import lk.ijse.phoneshop.dto.AttendanceDTO;
import lk.ijse.phoneshop.dto.EmployeeDTO;
import lk.ijse.phoneshop.tm.AttendanceTM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

public class EmployeeAttendanceController {


    public JFXTextField txtAttendId;
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

    private AttendanceBO attendanceBO = (AttendanceBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ATTENDANCE);

    public void initialize(){
        loadEmployeeId();
        loadNextAttendanceId();
        setCmbState();
        loadAllAttendance();
        setCellValueFactory();
    }

    @FXML
    void attendanceSaveOnAction(ActionEvent event) {
        String attendanceId = txtAttendId.getText();
        String employeeId = cmbEmployeeId.getValue();
        String employeeName = lblName.getText();
        LocalDate date = LocalDate.from(txtDate.getValue());
        String state = cmbState.getValue();
        LocalTime inTime = LocalTime.from(txtInTime.getValue());
        LocalTime outTime = LocalTime.from(txtOutTime.getValue());

        try {

            boolean attendanceSave = attendanceBO.saveAttendance(new AttendanceDTO(attendanceId,employeeId,employeeName,String.valueOf(date),state,String.valueOf(inTime),String.valueOf(outTime)));
            loadNextAttendanceId();
            if (!attendanceSave){
                new Alert(Alert.AlertType.WARNING, "Added Fail !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        loadAllAttendance();
        clearTextOnAction(event);
    }
    public void loadAllAttendance(){
        ObservableList <AttendanceTM> attendanceList = FXCollections.observableArrayList();
        attendanceList.clear();
        try {

            ArrayList<AttendanceDTO> allAttendanceDTO = attendanceBO.getAllAttendance();
            for(AttendanceDTO attendanceDTO : allAttendanceDTO){
                Button button = new Button("Delete");
                AttendanceTM tm = new AttendanceTM(attendanceDTO.getAttendanceId(), attendanceDTO.getEmployeeId(), attendanceDTO.getName(),
                        attendanceDTO.getDate(), attendanceDTO.getSate(), attendanceDTO.getInTime(), attendanceDTO.getOutTime(),button);
                attendanceList.add(tm);
                tblAttendance.setItems(attendanceList);

                button.setOnAction((e -> {
                    ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.orElse(no) == ok) {
                        tblAttendance.getItems().removeAll(tblAttendance.getSelectionModel().getSelectedItem());
                    }
                    String id = tm.getAttendanceId();

                    try {
                        attendanceBO.deleteAttendance(id);
                        loadNextAttendanceId();

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
            ArrayList<EmployeeDTO> allEmployeeDTO = attendanceBO.getAllEmployee();
            for (EmployeeDTO employeeDTO : allEmployeeDTO) {
                data.add(employeeDTO.getId());
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

            EmployeeDTO employeeDTO = attendanceBO.searchEmployee(id);

            filName(employeeDTO);
            txtDate.requestFocus();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }
    public void filName(EmployeeDTO employeeDTO){
        lblName.setText(employeeDTO.getName());
    }

    private void loadNextAttendanceId(){
        try {

            String aId = attendanceBO.getNextAttendanceId();
            txtAttendId.setText(aId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void updateOnAction(ActionEvent actionEvent) {

    }
    @FXML
    void stateOnAction(ActionEvent event) {
        txtInTime.requestFocus();
    }
}
