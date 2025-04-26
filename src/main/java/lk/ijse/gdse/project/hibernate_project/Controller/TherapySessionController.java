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
import lk.ijse.gdse.project.hibernate_project.Dto.ProgramDto;
import lk.ijse.gdse.project.hibernate_project.Dto.SessionDto;
import lk.ijse.gdse.project.hibernate_project.Dto.TherapistDto;
import lk.ijse.gdse.project.hibernate_project.Dto.Tm.SessionTm;
import lk.ijse.gdse.project.hibernate_project.bo.custom.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellValues();

        try {
            refreshPage();
        } catch (Exception e) {
            showErrorAlert(e.getMessage());
        }

        TranslateTransition slider = new TranslateTransition();
        slider.setNode(TherapySessionAncoer);
        slider.setDuration(Duration.seconds(1.0));
        slider.setFromX(-200);
        slider.setToX(0);
        slider.play();
    }

    public void setCellValues(){
        colId.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("sessionDate"));
        colPatient.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colTherapist.setCellValueFactory(new PropertyValueFactory<>("therapistId"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("programId"));

    }



    private void loadTable(){

        try {
            List<SessionDto> therapySessionDTOS = sessionBo.getSessions();
            ObservableList<SessionTm> therapySessionTMS = FXCollections.observableArrayList();

            for (SessionDto therapySessionDTO : therapySessionDTOS) {
                SessionTm therapySessionTM = new SessionTm(
                        therapySessionDTO.getSessionId(),
                        therapySessionDTO.getSessionDate(),
                        therapySessionDTO.getPatientId(),
                        therapySessionDTO.getTherapistId(),
                        therapySessionDTO.getProgramId()
                );
                therapySessionTMS.add(therapySessionTM);
            }
            tblSession.setItems(therapySessionTMS);
        } catch (Exception e) {
            showErrorAlert(e.getMessage());
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws Exception {
        if (!validateInputs()) return;

        try {
            String id = lblId.getText();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this therapy session?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get() == ButtonType.YES) {
                boolean isDeleted = sessionBo.deleteSession(id);
                if (isDeleted) {
                    showSuccessAlert("Therapy session deleted successfully.");
                    refreshPage();
                } else {
                    showErrorAlert("Failed to delete therapy session.");
                }
            }

        } catch (Exception e) {
            showErrorAlert(e.getMessage());
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        refreshPage();
    }



    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        if (!validateInputs()) return;

        try {
            SessionDto dto = new SessionDto(
                    lblId.getText(),
                    datePicker.getValue(),
                    cmbPatient.getValue(),
                    lblTherapist.getText(),
                    cmbProgram.getValue()
            );

            boolean isSaved = sessionBo.saveSession(dto);
            if (isSaved) {
                showSuccessAlert("Therapy session saved successfully.");
                refreshPage();
            } else {
                showErrorAlert("Failed to save therapy session.");
            }

        } catch (Exception e) {
            showErrorAlert(e.getMessage());
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        if (!validateInputs()) return;

        try {
            SessionDto dto = new SessionDto(
                    lblId.getText(),
                    datePicker.getValue(),
                    cmbPatient.getValue(),
                    lblTherapist.getText(),
                    cmbProgram.getValue()
            );

            boolean isUpdated = sessionBo.updateSession(dto);
            if (isUpdated) {
                showSuccessAlert("Therapy session updated successfully.");
                refreshPage();
            } else {
                showErrorAlert("Failed to update therapy session.");
            }

        } catch (Exception e) {
            showErrorAlert(e.getMessage());
        }
    }

    @FXML
    void cmbPatientOnAction(ActionEvent event) {
        if (cmbPatient.getValue() == null) return;

        PatientDto patientDTO = sessionBo.getPatient(cmbPatient.getValue());

        if (patientDTO != null) {
            lblPatientName.setText(patientDTO.getName());
        }
    }

    @FXML
    void cmbProgramOnAction(ActionEvent event) {
        if (cmbProgram.getValue() == null) return;

        ProgramDto programDTO = sessionBo.getProgram(cmbProgram.getValue());

        if (programDTO != null) {
            lblProgramName.setText(programDTO.getName());
            lblTherapist.setText(programDTO.getTherapistId());

            TherapistDto therapistDTO = sessionBo.getTherapist(programDTO.getTherapistId());
            if (therapistDTO != null) {
                lblTherapistName.setText(therapistDTO.getName());
                checkAvailability(therapistDTO.getId());
            }
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        SessionTm selected = tblSession.getSelectionModel().getSelectedItem();
        if (selected != null) {

            lblId.setText(selected.getSessionId());
            datePicker.setValue(selected.getSessionDate());
            cmbPatient.setValue(selected.getPatientId());
            cmbProgram.setValue(selected.getProgramId());

            PatientDto patientDTO = sessionBo.getPatient(selected.getPatientId());
            lblPatientName.setText(patientDTO.getName());

            ProgramDto therapyProgramDTO = sessionBo.getProgram(selected.getProgramId());
            lblProgramName.setText(therapyProgramDTO.getName());

            lblTherapist.setText(therapyProgramDTO.getTherapistId());
            TherapistDto therapistDTO = sessionBo.getTherapist(selected.getTherapistId());
            lblTherapistName.setText(therapistDTO.getName());
            checkAvailability(therapistDTO.getId());

            btnSave.setDisable(true);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    private void refreshPage() throws SQLException, IOException {
        loadTable();
        setNextId();
        clearFields();
        loadPatientCmb();
        loadProgramCmb();
    }

    private void clearFields() {
        datePicker.setValue(null);
        cmbPatient.setValue(null);
        cmbProgram.setValue(null);

        lblTherapist.setText("");
        lblTherapistName.setText("");
        lblPatientName.setText("");
        lblProgramName.setText("");

        applyBlockedDates(new ArrayList<>());

        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }

    private void setNextId() throws SQLException, IOException {
        String nextId = sessionBo.getNextSessionID();
        lblId.setText(nextId);
    }


    private boolean validateInputs() {
        boolean isValid = true;

        resetFieldStyles();

        if (datePicker.getValue() == null) {
            datePicker.setStyle("-fx-border-color: red;");
            isValid = false;
        }

        if (!isValid) {
            showErrorAlert("Please enter valid date.");
        }

        return isValid;
    }

    private void resetFieldStyles() {
        datePicker.setStyle(null);
    }

    private void loadPatientCmb() {
        List<PatientDto> patientDTOS = sessionBo.getPatients();
        ObservableList<String> patientIds = FXCollections.observableArrayList();

        for (PatientDto patientDTO : patientDTOS) {
            patientIds.add(patientDTO.getId());
        }

        cmbPatient.setItems(patientIds);
    }

    private void loadProgramCmb() {
        List<ProgramDto> therapyProgramDTOS = sessionBo.getPrograms();
        ObservableList<String> programIds = FXCollections.observableArrayList();

        for (ProgramDto therapyProgramDTO : therapyProgramDTOS) {
            programIds.add(therapyProgramDTO.getId());
        }

        cmbProgram.setItems(programIds);
    }

    private void checkAvailability(String therapistId) {
        List<SessionDto> sessionsFromTherapist = sessionBo.getSessionsFromTherapist(therapistId);
        List<LocalDate> blockedDates = new ArrayList<>();

        for (SessionDto dto : sessionsFromTherapist) {
            blockedDates.add(dto.getSessionDate());
        }

        applyBlockedDates(blockedDates); // ✅ Clean and reusable
    }

    private void applyBlockedDates(List<LocalDate> blockedDates) {
        datePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                if (blockedDates.contains(date)) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffdddd;");
                }
            }
        });
    }

    private void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Operation Completed");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Operation Failed");
        alert.setContentText(message);
        alert.showAndWait();
    }

}
