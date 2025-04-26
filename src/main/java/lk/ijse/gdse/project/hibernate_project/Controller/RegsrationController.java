package lk.ijse.gdse.project.hibernate_project.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import lk.ijse.gdse.project.hibernate_project.Dto.UserDto;
import lk.ijse.gdse.project.hibernate_project.bo.custom.BOType;
import lk.ijse.gdse.project.hibernate_project.bo.custom.BoFactory;
import lk.ijse.gdse.project.hibernate_project.bo.custom.UserBo;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class RegsrationController implements Initializable {

    @FXML
    private AnchorPane registorAnchorPane;

    @FXML
    private ComboBox<String> cmbRoles;

    @FXML
    private Button tbtnRegistor;

    @FXML
    private Button tbtnBack;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtUserPassword;

    UserBo userBo = BoFactory.getInstance().getBO(BOType.USER);

    @FXML
    void registorOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to save these details?");
        Optional<ButtonType> result = alert.showAndWait();

        // Only proceed if user clicks OK
//        if (result.isPresent() && result.get() == ButtonType.OK) {
//            try {
//                // Load the new FXML and replace current pane content
//                AnchorPane load = FXMLLoader.load(getClass().getResource("/view/HomePage.fxml"));
//                registorAnchorPane.getChildren().clear();
//                registorAnchorPane.getChildren().add(load);
//            } catch (IOException e) {
//                e.printStackTrace();
//                new Alert(Alert.AlertType.ERROR, "Failed to load the homepage!").show();
//            }
//        }

        String userId = txtUserId.getText();
        String UserName = txtUserName.getText();
        String password = txtUserPassword.getText();
        String Role = cmbRoles.getValue().toString();;


        UserDto userDto = new UserDto(userId , UserName, password, Role);
        boolean isSaved = userBo.save(userDto);
        if(isSaved && result.isPresent() && result.get() == ButtonType.OK){

            new Alert(Alert.AlertType.INFORMATION,"User Saved successfully").show();

            try {
                // Load the new FXML and replace current pane content
                AnchorPane load = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));
                registorAnchorPane.getChildren().clear();
                registorAnchorPane.getChildren().add(load);
            } catch (IOException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to load the homepage!").show();
            }

        }
        else {
            new Alert(Alert.AlertType.ERROR,"User Not Saved Please try Again").show();
        }



    }


    private void loadCmb() {
        cmbRoles.getItems().addAll("Admin", "employer", "Co-admin" , "Manager" , "Co-Manager");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadCmb();
            loadTherapistNextId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadTherapistNextId() throws SQLException, IOException {
        Optional<String> nextIdOptional = userBo.getNextId();
        txtUserId.setText(nextIdOptional.orElse("U001"));
    }

    @FXML
    void BackOnAction(ActionEvent event) {

        try {
            // Load the new FXML and replace current pane content
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));
            registorAnchorPane.getChildren().clear();
            registorAnchorPane.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load the homepage!").show();
        }

    }

}
