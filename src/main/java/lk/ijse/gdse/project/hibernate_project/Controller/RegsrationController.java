package lk.ijse.gdse.project.hibernate_project.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Optional;

public class RegsrationController {

    @FXML
    private AnchorPane registorAnchorPane;

    @FXML
    private Button tbtnRegistor;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtUserPassword;

    @FXML
    void registorOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to save these details?");
        Optional<ButtonType> result = alert.showAndWait();

        // Only proceed if user clicks OK
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                // Load the new FXML and replace current pane content
                AnchorPane load = FXMLLoader.load(getClass().getResource("/view/HomePage.fxml"));
                registorAnchorPane.getChildren().clear();
                registorAnchorPane.getChildren().add(load);
            } catch (IOException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to load the homepage!").show();
            }
        }
    }
}
