package lk.ijse.gdse.project.hibernate_project.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ProgrammeController {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> clmDuration;

    @FXML
    private TableColumn<?, ?> clmFees;

    @FXML
    private TableColumn<?, ?> clmProgrammeName;

    @FXML
    private TableColumn<?, ?> clmProgrmmeId;

    @FXML
    private AnchorPane programmeAnchorPane;

    @FXML
    private TableView<?> tblProgrmme;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtFees;

    @FXML
    private TextField txtProgrammeId;

    @FXML
    private TextField txtProgrammeName;

    @FXML
    void btnClearCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void tblOnMouseClick(MouseEvent event) {

    }

}
