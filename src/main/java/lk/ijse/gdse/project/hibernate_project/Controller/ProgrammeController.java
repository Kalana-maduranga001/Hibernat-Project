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
    import lk.ijse.gdse.project.hibernate_project.Dto.ProgramDto;
    import lk.ijse.gdse.project.hibernate_project.Dto.Tm.ProgramTm;
    import lk.ijse.gdse.project.hibernate_project.Entity.TherapyProgram;
    import lk.ijse.gdse.project.hibernate_project.bo.custom.BOType;
    import lk.ijse.gdse.project.hibernate_project.bo.custom.BoFactory;
    import lk.ijse.gdse.project.hibernate_project.bo.custom.ProgramBo;

    import java.io.IOException;
    import java.math.BigDecimal;
    import java.net.URL;
    import java.sql.SQLException;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Optional;
    import java.util.ResourceBundle;

    public class ProgrammeController implements Initializable {


        @FXML
        private Button btnClear;

        @FXML
        private Button btnDelete;

        @FXML
        private Button btnSave;

        @FXML
        private Button btnUpdate;

        @FXML

        private ComboBox<String> cmbTherapistId;

        @FXML
        private Label llblName;

        @FXML

        private TableColumn<ProgramTm, String> clmDuration;

        @FXML
        private TableColumn<ProgramTm, String> clmFees;

        @FXML
        private TableColumn<ProgramTm, String> clmProgrammeName;

        @FXML
        private TableColumn<ProgramTm, String> clmProgrmmeId;

        @FXML
        private TableColumn<ProgramTm, String> clmTherapyestIds;

        @FXML
        private AnchorPane programmeAnchorPane;

        @FXML
        private TableView<ProgramTm> tblProgrmme;

        @FXML
        private TextField txtDuration;

        @FXML
        private TextField txtFees;

        @FXML
        private TextField txtProgrammeId;

        @FXML
        private TextField txtProgrammeName;

        ProgramBo programBo = BoFactory.getInstance().getBO(BOType.PROGRAMME);


        @FXML
        void btnClearCustomerOnAction(ActionEvent event) throws SQLException, IOException {
            refreshPage();
        }

        @FXML
        void btnDeleteCustomerOnAction(ActionEvent event) throws Exception {

            String ID = txtProgrammeId.getText();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> optionalButtonType = alert.showAndWait();

            if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

                boolean isDelete = programBo.deleteByPK(ID);
                if (isDelete) {
                    refreshPage();
                    loadPatientNextId();
                    new Alert(Alert.AlertType.INFORMATION, "Therapy Programme deleted...!").show();

                } else {
                    new Alert(Alert.AlertType.ERROR, "Fail to delete Therapy Programme...!").show();
                }
            }



        }

        @FXML
        void btnSaveCustomerOnAction(ActionEvent event) throws SQLException, IOException {

            String programmeId = txtProgrammeId.getText();
            String programmeName = txtProgrammeName.getText();
            double price = Double.parseDouble(txtFees.getText().trim());
            int programmeDuration = Integer.parseInt(txtDuration.getText().trim());
            String therapistId = cmbTherapistId.getValue();

            ProgramDto programDto = new ProgramDto(programmeId, programmeName,  price ,programmeDuration, therapistId);


            boolean isSaved = programBo.save(programDto);
            if(isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Therapy Programme Saved successfully").show();
                refreshPage();
                loadPatientNextId();
            }
            else {
                new Alert(Alert.AlertType.ERROR,"Therapy Programme Not Saved Please try Again").show();
            }

        }

        @FXML
        void btnUpdateCustomerOnAction(ActionEvent event) throws SQLException, IOException {

            String programmeId = txtProgrammeId.getText();
            String programmeName = txtProgrammeName.getText();
            double price = Double.parseDouble(txtFees.getText().trim());
            int programmeDuration = Integer.parseInt(txtDuration.getText());
            String therapistId = cmbTherapistId.getValue();

            ProgramDto programDto = new ProgramDto(programmeId, programmeName,  price ,programmeDuration, therapistId);

            boolean isUpdate = programBo.update(programDto);
            if(isUpdate){
                new Alert(Alert.AlertType.INFORMATION,"Therapy Programme Saved successfully").show();
                refreshPage();
                loadPatientNextId();
            }
            else {
                new Alert(Alert.AlertType.ERROR,"Therapy Programme Not Saved Please try Again").show();
            }
        }

        private void loadTable() throws SQLException, IOException {

            ArrayList<ProgramDto> therapyProgrammeDtos = (ArrayList<ProgramDto>) programBo.getAll();
            ObservableList<ProgramTm> therapistProgrammeTms = FXCollections.observableArrayList();

            for (ProgramDto therapyProgrammeDto : therapyProgrammeDtos) {
                ProgramTm therapistProgrammeTm = new ProgramTm(
                        therapyProgrammeDto.getId(),
                        therapyProgrammeDto.getName(),
                        therapyProgrammeDto.getFee(),
                        therapyProgrammeDto.getDuration(),
                        therapyProgrammeDto.getTherapistId()

                );
                therapistProgrammeTms.add(therapistProgrammeTm);
            }
            tblProgrmme.setItems(therapistProgrammeTms);

        }

        @FXML
        void tblOnMouseClick(MouseEvent event) {

            ProgramTm therapistProgrammeTm = tblProgrmme.getSelectionModel().getSelectedItem();
            if (therapistProgrammeTm != null) {
                txtProgrammeId.setText(therapistProgrammeTm.getId());
                txtProgrammeName.setText(therapistProgrammeTm.getName());
                txtFees.setText(String.valueOf(therapistProgrammeTm.getFee()));
                txtDuration.setText(String.valueOf(therapistProgrammeTm.getDuration()));
                cmbTherapistId.setValue(String.valueOf(therapistProgrammeTm.getTherapistId()));

                btnDelete.setDisable(false);
                btnSave.setDisable(true);
                btnUpdate.setDisable(false);
            }

        }

        @Override
        public void initialize(URL location, ResourceBundle resources) {

            clmProgrmmeId.setCellValueFactory(new PropertyValueFactory<>("id"));
            clmProgrammeName.setCellValueFactory(new PropertyValueFactory<>("name"));
            clmFees.setCellValueFactory(new PropertyValueFactory<>("fee"));
            clmDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
            clmTherapyestIds.setCellValueFactory(new PropertyValueFactory<>("therapistId"));

            try{
                loadPatientNextId();
                loadTable();
                loadCmb();
            }catch (Exception e){
                e.printStackTrace();
            }


            TranslateTransition slider = new TranslateTransition();
            slider.setNode(programmeAnchorPane);
            slider.setDuration(Duration.seconds(1.0));
            slider.setFromX(-200);
            slider.setToX(0);
            slider.play();

        }

        private void loadPatientNextId() throws SQLException, IOException {
            Optional<String> nextIdOptional = programBo.getNextId();
            if (nextIdOptional.isPresent()) {
                txtProgrammeId.setText(nextIdOptional.get());
            } else {
                txtProgrammeId.setText("MT001");
            }
        }

        private void refreshPage() throws SQLException, IOException {
            txtProgrammeId.setText("");
            txtProgrammeName.setText("");
            txtDuration.setText("");
            txtFees.setText("");
            cmbTherapistId.setValue("");

            btnSave.setDisable(false);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(true);
            btnClear.setDisable(false);
            loadTable();
        }


        @FXML
        void cmbTherapistIdOnAction(ActionEvent event) {

        }

        public void loadCmb(){
            try {

                List<String> therapistId = programBo.getAllTherapistIds();
                cmbTherapistId.setItems(FXCollections.observableArrayList(therapistId));

            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to load therapist IDs into combo box").show();

            }
        }


    }
