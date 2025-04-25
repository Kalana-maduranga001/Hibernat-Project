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
import lk.ijse.gdse.project.hibernate_project.Dto.TherapistDto;
import lk.ijse.gdse.project.hibernate_project.Dto.Tm.TherapistTm;
import lk.ijse.gdse.project.hibernate_project.bo.custom.BOType;
import lk.ijse.gdse.project.hibernate_project.bo.custom.BoFactory;
import lk.ijse.gdse.project.hibernate_project.bo.custom.TherapistBo;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class TherapistController implements Initializable {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> clmTherapistId;

    @FXML
    private TableColumn<?, ?> clmTherapistName;

<<<<<<< HEAD
//    @FXML
//    private ComboBox<?> cmbProgrmmrId;
=======
    @FXML
    private ComboBox<?> cmbProgrmmrId;
>>>>>>> d8048a3c57b25da4dff1b669c5f8a2db5aa065e8

    @FXML
    private TableColumn<TherapistTm, String> cmlAddress;

    @FXML
    private TableColumn<TherapistTm, String> cmlContactNumber;

    @FXML
    private TableColumn<TherapistTm, Integer> cmlTherpistAge;

<<<<<<< HEAD
//    @FXML
//    private Label llblName;
=======
    @FXML
    private Label llblName;
>>>>>>> d8048a3c57b25da4dff1b669c5f8a2db5aa065e8

    @FXML
    private TableView<TherapistTm> tblTherapist;

    @FXML
    private AnchorPane therpistAncohpane;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @FXML
    void btnClearCustomerOnAction(ActionEvent event) throws SQLException, IOException {
        refreshPage();
    }

    TherapistBo therapistBo = BoFactory.getInstance().getBO(BOType.THERAPIST);


    @FXML
    void btnDeleteCustomerOnAction(ActionEvent event) throws Exception {
        String ID = txtId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDelete = therapistBo.deleteByPK(ID);
            if (isDelete) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Patient deleted...!").show();
                loadTherapistNextId();

            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Patient...!").show();
            }
        }
    }

    @FXML
    void btnSaveCustomerOnAction(ActionEvent event) throws SQLException, IOException {

        String therapistId = txtId.getText();
        String therapistName = txtName.getText();
        int age = Integer.parseInt(txtAge.getText());
        String therapistAddress = txtAddress.getText();
        String phone = txtPhone.getText();
//        String therapyProgrammeId = (String) cmbProgrmmrId.getValue();

        TherapistDto therapistDto = new TherapistDto(therapistId, therapistName, age,therapistAddress,phone);
        boolean isSaved = therapistBo.save( therapistDto);
        if(isSaved){
            new Alert(Alert.AlertType.INFORMATION,"Therapist Saved successfully").show();
            refreshPage();
            loadTherapistNextId();
        }
        else {
            new Alert(Alert.AlertType.ERROR,"Therapist Not Saved Please try Again").show();
        }
        
    }

    private void refreshPage() throws SQLException, IOException {
        txtId.setText("");
        txtName.setText("");
        txtAge.setText("");
        txtAddress.setText("");
        txtPhone.setText("");

        btnSave.setDisable(false);
        btnDelete.setDisable(false);
        btnUpdate.setDisable(true);
        loadTable();
    }

    @FXML
    void btnUpdateCustomerOnAction(ActionEvent event) throws SQLException, IOException {
        String therapistId = txtId.getText();
        String therapistName = txtName.getText();
        int age = Integer.parseInt(txtAge.getText());
        String therapistAddress = txtAddress.getText();
        String phone = txtPhone.getText();
//        String therapyProgrammeId = (String) cmbProgrmmrId.getValue();

        TherapistDto therapistDto = new TherapistDto(therapistId, therapistName, age,therapistAddress,phone);
        boolean isUpdated = therapistBo.update( therapistDto);
        if(isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Therapist Saved successfully").show();
            refreshPage();
            loadTherapistNextId();
        }
        else {
            new Alert(Alert.AlertType.ERROR,"Therapist Not Saved Please try Again").show();
        }
    }

    @FXML
    void tblOnMouseClick(MouseEvent event) {
        TherapistTm therapistTm = tblTherapist.getSelectionModel().getSelectedItem();
        if (therapistTm != null) {
            txtId.setText(therapistTm.getId());
            txtName.setText(therapistTm.getName());
            txtAge.setText(String.valueOf(therapistTm.getAge()));
            txtPhone.setText(therapistTm.getContact());
            txtAddress.setText(therapistTm.getSpecialization());

            btnDelete.setDisable(false);
            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnClear.setDisable(false);
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clmTherapistId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        clmTherapistName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cmlTherpistAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        cmlContactNumber.setCellValueFactory(new PropertyValueFactory<>("contact"));
        cmlAddress.setCellValueFactory(new PropertyValueFactory<>("specialization"));

        try {
            loadTable();
            loadTherapistNextId();
        }catch (Exception e){
            e.printStackTrace();
        }

        TranslateTransition slider = new TranslateTransition();
        slider.setNode(therpistAncohpane);
        slider.setDuration(Duration.seconds(1.0));
        slider.setFromX(-200);
        slider.setToX(0);
        slider.play();
    }

    private void loadTable() throws SQLException, IOException {

        ArrayList<TherapistDto> therapistDtos = (ArrayList<TherapistDto>) therapistBo.getAll();
        ObservableList<TherapistTm> therapistTms = FXCollections.observableArrayList();

        for (TherapistDto therapistDto : therapistDtos) {
            TherapistTm therapistTm = new TherapistTm(
                    therapistDto.getId(),
                    therapistDto.getName(),
                    therapistDto.getAge(),
                    therapistDto.getSpecialization(),
                    therapistDto.getContact()

            );
            therapistTms.add(therapistTm);
        }

        tblTherapist.setItems(therapistTms);

    }

    private void loadTherapistNextId() throws SQLException, IOException {
        Optional<String> nextIdOptional = therapistBo.getNextId();
        txtId.setText(nextIdOptional.orElse("T001"));
    }



}
