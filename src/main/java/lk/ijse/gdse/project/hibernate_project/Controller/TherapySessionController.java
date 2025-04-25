package lk.ijse.gdse.project.hibernate_project.Controller;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.gdse.project.hibernate_project.Dto.PatientDto;
import lk.ijse.gdse.project.hibernate_project.Dto.SessionDto;
import lk.ijse.gdse.project.hibernate_project.Dto.TherapistDto;
import lk.ijse.gdse.project.hibernate_project.Dto.Tm.SessionTm;
import lk.ijse.gdse.project.hibernate_project.bo.custom.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class TherapySessionController implements Initializable {

    @FXML
    private AnchorPane TherapySessionAncoer;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbPatient;

    @FXML
    private ComboBox<String> cmbProgram;

    @FXML
    private TableColumn<SessionTm, LocalDate> colDate;

    @FXML
    private TableColumn<SessionTm, String> colId;

    @FXML
    private TableColumn<SessionTm, String> colPatient;

    @FXML
    private TableColumn<SessionTm, String> colProgram;

    @FXML
    private TableColumn<SessionTm, String> colTherapist;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label lblId;

    @FXML
    private Label lblPatientName;

    @FXML
    private Label lblProgramName;

    @FXML
    private Label lblTherapist;

    @FXML
    private Label lblTherapistName;

    @FXML
    private TableView<SessionTm> tblSession;

    SessionBo sessionBo = BoFactory.getInstance().getBO(BOType.SESSION);
//    PatientBo patientBo = BoFactory.getInstance().getBO(BOType.PATIENT);
//    TherapistBo therapistBo = BoFactory.getInstance().getBO(BOType.THERAPIST);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setSelles();
            loadTable();
            loadTherapistNextId();
        }catch (Exception e){
            e.printStackTrace();
        }

        TranslateTransition slider = new TranslateTransition();
        slider.setNode(TherapySessionAncoer);
        slider.setDuration(Duration.seconds(1.0));
        slider.setFromX(-200);
        slider.setToX(0);
        slider.play();
    }

    public void setSelles(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPatient.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("programid"));
        colTherapist.setCellValueFactory(new PropertyValueFactory<>("therapietid"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

    }

    private void loadTherapistNextId() throws SQLException, IOException {
        Optional<String> nextIdOptional = sessionBo.getNextId();
        lblId.setText(nextIdOptional.orElse("TS001"));
    }

    private void loadTable() throws SQLException, IOException, ClassNotFoundException {

        ArrayList<SessionDto> sessionDtos = (ArrayList<SessionDto>) sessionBo.getAll();
        ObservableList<SessionTm> sessionTms = FXCollections.observableArrayList();

        for (SessionDto sessionDto : sessionDtos) {
            PatientDto patient = sessionBo.findPatientByPK(sessionDto.getPatientId());
            TherapistDto therapist = sessionBo.findTherapistByPK(sessionDto.getTherapietid());

            SessionTm sessionTm = new SessionTm(
                    sessionDto.getId(),
                    sessionDto.getDate(),
                    sessionDto.getNotes(),
                    patient.getId(),
                    therapist.getId()


            );
            sessionTms.add(sessionTm);
        }

        tblSession.setItems(sessionTms);

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws Exception {
        String ID = lblId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDelete = sessionBo.deleteByPK(ID);
            if (isDelete) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "session deleted...!").show();
                loadTherapistNextId();

            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete session...!").show();
            }
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        refreshPage();
    }

    private void refreshPage() throws SQLException, IOException, ClassNotFoundException {
        lblId.setText("");
        datePicker.setValue(LocalDate.parse(""));
        lblPatientName.setText("");
        lblProgramName.setText("");
        lblTherapistName.setText("");
        cmbPatient.setValue("");
        cmbProgram.setValue("");

        btnSave.setDisable(false);
        btnDelete.setDisable(false);
        btnUpdate.setDisable(true);
        loadTable();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {

        String sessionid = lblId.getText();
        String sessionDate = datePicker.getValue().toString();
        String program = lblPatientName.getText();
        String patient = lblProgramName.getText();
        String therapist = lblTherapistName.getText();


        SessionDto sessionDtoDto = new SessionDto(sessionid, sessionDate, program,patient,therapist);
        boolean isSaved = sessionBo.save(sessionDtoDto);
        if(isSaved){
            new Alert(Alert.AlertType.INFORMATION,"session Saved successfully").show();
            refreshPage();
            loadTherapistNextId();
        }
        else {
            new Alert(Alert.AlertType.ERROR,"session Not Saved Please try Again").show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        String sessionid = lblId.getText();
        String sessionDate = datePicker.getValue().toString();
        String program = lblPatientName.getText();
        String patient = lblProgramName.getText();
        String therapist = lblTherapistName.getText();


        SessionDto sessionDtoDto = new SessionDto(sessionid, sessionDate, program,patient,therapist);
        boolean isSaved = sessionBo.update(sessionDtoDto);
        if(isSaved){
            new Alert(Alert.AlertType.INFORMATION,"session updated successfully").show();
            refreshPage();
            loadTherapistNextId();
        }
        else {
            new Alert(Alert.AlertType.ERROR,"session Not updated Please try Again").show();
        }
    }

    @FXML
    void cmbPatientOnAction(ActionEvent event) {

    }

    @FXML
    void cmbProgramOnAction(ActionEvent event) {

    }

    @FXML
    void onClickTable(MouseEvent event) {

//        TherapistTm therapistTm = tblTherapist.getSelectionModel().getSelectedItem();
//        if (therapistTm != null) {
//            txtId.setText(therapistTm.getId());
//            txtName.setText(therapistTm.getName());
//            txtAge.setText(String.valueOf(therapistTm.getAge()));
//            txtPhone.setText(therapistTm.getContact());
//            txtAddress.setText(therapistTm.getSpecialization());
//
//            btnDelete.setDisable(false);
//            btnSave.setDisable(true);
//            btnUpdate.setDisable(false);
//            btnClear.setDisable(false);
//        }

    }


}
