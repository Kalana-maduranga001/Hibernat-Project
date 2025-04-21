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
import lk.ijse.gdse.project.hibernate_project.Dto.Tm.PatientTm;

import lk.ijse.gdse.project.hibernate_project.bo.custom.BOType;
import lk.ijse.gdse.project.hibernate_project.bo.custom.BoFactory;
import lk.ijse.gdse.project.hibernate_project.bo.custom.PatientBo;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class patientController implements Initializable {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<PatientTm, String> clmAddress;

    @FXML
    private TableColumn<PatientTm, String> clmContactNumber;

    @FXML
    private TableColumn<PatientTm, String> clmDate;

    @FXML
    private TableColumn<PatientTm, String> clmGender;

    @FXML
    private TableColumn<PatientTm, String> clmHistory;

    @FXML
    private TableColumn<PatientTm, String> clmPatientId;

    @FXML
    private TableColumn<PatientTm, String> clmPatientName;

    @FXML
    private ComboBox<String> cmbGender;

    @FXML
    private AnchorPane patientAnchorpane;

    @FXML
    private TableView<PatientTm> tblPatient;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtMedicalHistory;

    @FXML
    private TextField txtPAddress;

    @FXML
    private TextField txtPId;

    @FXML
    private TextField txtPName;

    @FXML
    private TextField txtPhone;

    PatientBo patientBo = BoFactory.getInstance().getBO(BOType.PATIENT);

    @FXML
    void btnClearOnAction(ActionEvent event) throws SQLException, IOException {
        refreshPage();
        loadPatientNextId();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws Exception {
        String ID = txtPId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDelete = patientBo.deleteByPK(ID);
            if (isDelete) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Patient deleted...!").show();
                loadPatientNextId();

            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Patient...!").show();
            }
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, IOException {
        String patientId = txtPId.getText();
        String name = txtPName.getText();
        String gender = cmbGender.getValue().toString();
        LocalDate birthday = txtDate.getValue();
        String medicalHistory = txtMedicalHistory.getText();
        String address = txtPAddress.getText();
        String phone = txtPhone.getText();


        PatientDto patientDTO = new PatientDto(patientId, name,gender, birthday,medicalHistory, address, phone);
        boolean isSaved = patientBo.save( patientDTO);
        System.out.println("Patient ID: " + patientDTO.getId());
        if(isSaved){
            new Alert(Alert.AlertType.INFORMATION,"Patient Saved successfully").show();
            refreshPage();
            loadPatientNextId();
        }
        else {
            String nextId = patientBo.getNextId().orElseThrow(() -> new RuntimeException("Cannot generate new patient ID"));
            System.out.println("nextId: " + nextId);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, IOException {
        String patientId = txtPId.getText();
        String name = txtPName.getText();
        String gender = cmbGender.getValue().toString();
        LocalDate birthday = txtDate.getValue();
        String medicalHistory = txtMedicalHistory.getText();
        String address = txtPAddress.getText();
        String phone = txtPhone.getText();


        PatientDto patientDTO = new PatientDto(patientId, name,gender, birthday,medicalHistory, address, phone);
        boolean isUpdated = patientBo.update( patientDTO);
//        System.out.println("Patient ID: " + patientDTO.getId());
        if(isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Patient Saved successfully").show();
            refreshPage();
            loadPatientNextId();
        }
        else {
            String nextId = patientBo.getNextId().orElseThrow(() -> new RuntimeException("Cannot generate new patient ID"));
            System.out.println("nextId: " + nextId);
        }
    }

    @FXML
    void tblOnMouseClick(MouseEvent event) {
        PatientTm patientTM = tblPatient.getSelectionModel().getSelectedItem();
        if (patientTM != null) {
            txtPId.setText(patientTM.getId());
            txtPName.setText(patientTM.getName());
            cmbGender.setValue(patientTM.getGender());
            txtPAddress.setText(patientTM.getAddress());
            txtPhone.setText(patientTM.getContact());
            txtDate.setValue(patientTM.getDateOfBirth());
            txtMedicalHistory.setText(patientTM.getMedicalHistory());

            btnDelete.setDisable(false);
            btnSave.setDisable(true);
            btnClear.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clmPatientId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmPatientName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        clmDate.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        clmHistory.setCellValueFactory(new PropertyValueFactory<>("medicalHistory"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        clmContactNumber.setCellValueFactory(new PropertyValueFactory<>("contact"));


        TranslateTransition slider = new TranslateTransition();
        slider.setNode(patientAnchorpane);
        slider.setDuration(Duration.seconds(1.0));
        slider.setFromX(-200);
        slider.setToX(0);
        slider.play();


        try {
            loadPatientNextId();
            loadTable();
            loadCmb();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void loadCmb() {
        cmbGender.getItems().addAll("Male", "Female");
    }

    private void loadPatientNextId() throws SQLException, IOException {
        Optional<String> nextIdOptional = patientBo.getNextId();
        if (nextIdOptional.isPresent()) {
            txtPId.setText(nextIdOptional.get());
        } else {
            txtPId.setText("P001");
        }
    }

    private void refreshPage() throws SQLException, IOException {
        txtPId.setText("");
        txtPName.setText("");
        txtMedicalHistory.setText("");
        txtDate.setValue(LocalDate.now());
        txtPhone.setText("");
        txtPAddress.setText("");
        cmbGender.setValue("");

        btnSave.setDisable(false);
        btnDelete.setDisable(false);
        btnUpdate.setDisable(true);
        btnClear.setDisable(false);
        loadTable();
    }

    private void loadTable() throws SQLException, IOException {
        ArrayList<PatientDto> patientDTOS = (ArrayList<PatientDto>) patientBo.getAll();
        ObservableList<PatientTm> patientTMs = FXCollections.observableArrayList();

        for (PatientDto patientDTO : patientDTOS) {
            PatientTm patientTM = new PatientTm(
                    patientDTO.getId(),
                    patientDTO.getName(),
                    patientDTO.getGender(),
                    patientDTO.getDateOfBirth(),
                    patientDTO.getMedicalHistory(),
                    patientDTO.getAddress(),
                    patientDTO.getContact()

            );
            patientTMs.add(patientTM);
        }
        tblPatient.setItems(patientTMs);
    }
}

