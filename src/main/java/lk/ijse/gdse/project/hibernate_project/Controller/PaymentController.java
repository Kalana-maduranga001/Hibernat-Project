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

import lk.ijse.gdse.project.hibernate_project.Dto.PaymentDto;
import lk.ijse.gdse.project.hibernate_project.Dto.Tm.PaymentTm;
import lk.ijse.gdse.project.hibernate_project.Entity.TherapyProgram;
import lk.ijse.gdse.project.hibernate_project.bo.custom.BOType;
import lk.ijse.gdse.project.hibernate_project.bo.custom.BoFactory;
import lk.ijse.gdse.project.hibernate_project.bo.custom.PaymentBo;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {

    @FXML
    private AnchorPane PaymentAncohpane1;

    @FXML
    private Button btnCalculator;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnClear1;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnDelete1;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnSave1;

    @FXML
    private Button btnSave11;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnReport;

    @FXML
    private TableColumn<PaymentTm, String> clmPatientId;

    @FXML
    private TableColumn<PaymentTm, String> clmPaymentId1;

    @FXML
    private TableColumn<?, ?> clmTherapistId;

    @FXML
    private TableColumn<?, ?> clmTherapistName;

    @FXML
    private ComboBox<String> cmbPatientID;

    @FXML
    private ComboBox<String> cmbProgrmmrId;

    @FXML
    private ComboBox<?> cmbProgrmmrId1;

    @FXML
    private TableColumn<?, ?> cmlAddress;

    @FXML
    private TableColumn<PaymentTm, String> cmlAmount;

    @FXML
    private TableColumn<?, ?> cmlContactNumber;

    @FXML
    private TableColumn<PaymentTm, String> cmlDate;

    @FXML
    private TableColumn<PaymentTm, String> cmlProgramId;

    @FXML
    private TableColumn<PaymentTm, String> cmlState;

    @FXML
    private TableColumn<?, ?> cmlTherpistAge;

    @FXML
    private Label getCalculatePayemntLable;

    @FXML
    private Label llblName;

    @FXML
    private Label llblName1;

    @FXML
    private TableView<PaymentTm> tblPayment;

    @FXML
    private TableView<PaymentTm> tblTherapist;

    @FXML
    private AnchorPane therpistAncohpane;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAddress1;

    @FXML
    private TextField txtAge;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPaiedAmount;

    @FXML
    private TextField txtPatientName;

    @FXML
    private TextField txtPaymentId;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtProgramFee;

    @FXML
    private TextField txtProgramName;

    @FXML
    private TextField txtState;

    @FXML
    private TextField txtamount;

    PaymentBo paymentBo = BoFactory.getInstance().getBO(BOType.PAYMENT);

    @FXML
    void btnClearPaymentOnAction(ActionEvent event) throws SQLException, IOException {
        refreshPage();
    }

    @FXML
    void btnDeletePaymentOnAction(ActionEvent event) throws Exception {
        String ID = txtPaymentId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDelete = paymentBo.deleteByPK(ID);
            if (isDelete) {
                refreshPage();
                loadPaymentNextId();
                new Alert(Alert.AlertType.INFORMATION, "payment deleted...!").show();

            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete payment...!").show();
            }
        }
    }


    @FXML
    void btnSavePaymentOnAction(ActionEvent event) throws SQLException, IOException {

        String paymentId = txtPaymentId.getText();
        String patientId = cmbPatientID.getValue();
        String programId = cmbProgrmmrId.getValue();
        String date      = txtDate.getValue().toString();
        String status    = txtState.getText();
        String text = getCalculatePayemntLable.getText();
        String numberOnly = text.replaceAll("[^\\d.]", "");
        double amount = Double.parseDouble(numberOnly);

        PaymentDto paymentDTO = new PaymentDto(paymentId , patientId , programId , date , status, amount);
        boolean isSaved = paymentBo.save( paymentDTO);
        System.out.println("Payment ID: " + paymentDTO.getId());
        if(isSaved){
            new Alert(Alert.AlertType.INFORMATION,"Patient Saved successfully").show();
            refreshPage();
            loadPaymentNextId();

        }
        else {
            String nextId = paymentBo.getNextId().orElseThrow(() -> new RuntimeException("Cannot generate new payment ID"));
            System.out.println("nextId: " + nextId);
        }
    }

    @FXML
    void btnUpdatePaymentOnAction(ActionEvent event) throws SQLException, IOException {
        String paymentId = txtPaymentId.getText();
        String patientId = cmbPatientID.getValue();
        String programId = cmbProgrmmrId.getValue();
        String date      = txtDate.getValue().toString();
        String status    = txtState.getText();
        String text = getCalculatePayemntLable.getText();
        String numberOnly = text.replaceAll("[^\\d.]", "");
        double amount = Double.parseDouble(numberOnly);

        PaymentDto paymentDTO = new PaymentDto(paymentId , patientId , programId , date , status, amount);
        boolean isSaved = paymentBo.update( paymentDTO);
        if(isSaved){
            new Alert(Alert.AlertType.INFORMATION,"Patient Saved successfully").show();
            refreshPage();
            loadPaymentNextId();

        }
        else {
            String nextId = paymentBo.getNextId().orElseThrow(() -> new RuntimeException("Cannot generate new payment ID"));
            System.out.println("nextId: " + nextId);
        }
    }

    @FXML
    void btnCalculatePaymentOnAction(ActionEvent event) {
        try {
            double programFee = Double.parseDouble(txtProgramFee.getText());
            double paidAmount = Double.parseDouble(txtPaiedAmount.getText());

            double balance = programFee - paidAmount;
            getCalculatePayemntLable.setText(String.format("Remaining: %.2f", balance));

            if (balance == programFee || balance == 0) {
                txtState.setText("Full Payment");
            } else if (balance < programFee) {
                txtState.setText("Half Payment");
            } else if(balance > programFee) {
                txtState.setText("give Customer Cash : " + balance);
            }


        } catch (NumberFormatException e) {
        new Alert(Alert.AlertType.ERROR, "Invalid input! Please enter valid numbers.").show();
    }

    }


    @FXML
    void btnReportPaymentOnAction(ActionEvent event) {


    }

    @FXML
    void tblOnMouseClick(MouseEvent event) {

        PaymentTm paymentTM = tblPayment.getSelectionModel().getSelectedItem();
        if (paymentTM != null) {
            txtPaymentId.setText(paymentTM.getId());
            cmbPatientID.setValue(paymentTM.getPatientId());
            txtState.setText(paymentTM.getStatus());
            cmbProgrmmrId.setValue(paymentTM.getProgramId());
            txtProgramFee.setText(String.valueOf(paymentTM.getAmount()));
            txtDate.setValue(LocalDate.parse(paymentTM.getDate()));

            btnDelete1.setDisable(false);
            btnSave1.setDisable(true);
            btnClear1.setDisable(false);
            btnSave11.setDisable(false);
        }
    }

    @FXML
    void btnClearCustomerOnAction(ActionEvent event) {}  //--> dump Code if this code remove here that effect displaying Error

    @FXML
    void btnDeleteCustomerOnAction(ActionEvent event) {}  //--> dump Code if this code remove here that effect displaying Error

    @FXML
    void btnUpdateCustomerOnAction(ActionEvent event) {}  //--> dump Code if this code remove here that effect displaying Error

    @FXML
    void btnSaveCustomerOnAction(ActionEvent event) {}    //--> dump Code if this code remove here that effect displaying Error

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        clmPaymentId1.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmPatientId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        cmlProgramId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        cmlDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        cmlState.setCellValueFactory(new PropertyValueFactory<>("status"));
        cmlAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        try {
            loadTable();
            loadPaymentNextId();
            loadCmb();
            refreshPage();

            cmbPatientID.setOnAction(event -> {
                String selectedId = cmbPatientID.getValue();
                if (selectedId != null) {
                    try {
                        String name = paymentBo.getPatientNameById(selectedId);
                        txtPatientName.setText(name);
                    } catch (Exception e) {
                        e.printStackTrace();
                        new Alert(Alert.AlertType.ERROR, "Failed to load patient name").show();
                    }
                }
            });

            cmbProgrmmrId.setOnAction(event -> {
                String selectedProgramId = cmbProgrmmrId.getValue();
                if (selectedProgramId != null) {
                    try {
                        TherapyProgram program = paymentBo.getProgramById(selectedProgramId);
                        if (program != null) {
                            txtProgramName.setText(program.getName());
                            txtProgramFee.setText(String.valueOf(program.getFee()));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        new Alert(Alert.AlertType.ERROR, "Failed to load Program details").show();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }


        TranslateTransition slider = new TranslateTransition();
        slider.setNode(PaymentAncohpane1);
        slider.setDuration(Duration.seconds(1.0));
        slider.setFromX(-200);
        slider.setToX(0);
        slider.play();

    }

    private void loadTable() throws SQLException, IOException {
        ArrayList<PaymentDto> paymentDtos = (ArrayList<PaymentDto>) paymentBo.getAll();
        ObservableList<PaymentTm> paymentTms = FXCollections.observableArrayList();

        for (PaymentDto paymentDto : paymentDtos) {
            PaymentTm paymentTm = new PaymentTm(
                    paymentDto.getId(),
                    paymentDto.getPatientId(),
                    paymentDto.getProgramId(),
                    paymentDto.getDate(),
                    paymentDto.getStatus(),
                    paymentDto.getAmount()

            );
            paymentTms.add(paymentTm);
        }

        tblPayment.setItems(paymentTms);
    }

    private void loadPaymentNextId() throws SQLException, IOException {
        Optional<String> nextIdOptional = paymentBo.getNextId();
        txtPaymentId.setText(nextIdOptional.orElse("P001"));
    }

    private void refreshPage() throws SQLException, IOException {
        txtPaymentId.setText("");
        cmbPatientID.setValue("");
        txtPatientName.setText("");
        txtState.setText("");
        txtPaiedAmount.setText("");
        cmbProgrmmrId.setValue("");
        txtProgramName.setText("");
        txtProgramFee.setText("");
        getCalculatePayemntLable.setText("");
        txtDate.setValue(LocalDate.now());

        btnSave1.setDisable(false);
        btnDelete1.setDisable(true);
        btnSave11.setDisable(true);
        btnClear1.setDisable(false);
        loadTable();
        loadPaymentNextId();


    }

    public void loadCmb(){

        try {

            List<String> paymentIds = paymentBo.getAllPaymentIds();
            cmbPatientID.setItems(FXCollections.observableArrayList(paymentIds));

           List<String> programIds = paymentBo.getAllProgramIds();
           cmbProgrmmrId.setItems(FXCollections.observableArrayList(programIds));

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load payment IDs or Program IDs into combo box").show();
        }


    }
}
