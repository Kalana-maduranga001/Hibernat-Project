package lk.ijse.gdse.project.hibernate_project.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lk.ijse.gdse.project.hibernate_project.Dto.UserDto;
import lk.ijse.gdse.project.hibernate_project.bo.custom.BOType;
import lk.ijse.gdse.project.hibernate_project.bo.custom.BoFactory;
import lk.ijse.gdse.project.hibernate_project.bo.custom.UserBo;
import lk.ijse.gdse.project.hibernate_project.util.CurrentUser;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private AnchorPane LoginAnchorPane;

    @FXML
    private Button loginBut;

    @FXML
    private Button registerBtn;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Pane userNamePasswordAnchorePane;

    @FXML
    private RadioButton radioShow;

    @FXML
    private TextField txtPassword1;

    private UserBo userBO = BoFactory.getInstance().getBO(BOType.USER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtPassword1.managedProperty().bind(radioShow.selectedProperty());
        txtPassword1.visibleProperty().bind(radioShow.selectedProperty());

        txtPassword.managedProperty().bind(radioShow.selectedProperty().not());
        txtPassword.visibleProperty().bind(radioShow.selectedProperty().not());

        txtPassword1.textProperty().bindBidirectional(txtPassword.textProperty());
    }

    @FXML
    void loginOnAction(ActionEvent event) throws IOException {

//        Node node = FXMLLoader.load(getClass().getResource("/view/homepage.fxml"));  // Load the homepage view
//        LoginAnchorPane.getChildren().setAll(node);

        String username = txtName.getText();
        String password = radioShow.isSelected() ? txtPassword1.getText() : txtPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Username and password must not be empty.");
            return;
        }

        try {
            boolean isValid = userBO.validateLogin(username, password);  // Validate user login

            if (isValid) {
                UserDto loggedInUser = userBO.getUserByUsername(username);  // Retrieve user data
                CurrentUser.setCurrentUser(loggedInUser);  // Set the logged-in user in CurrentUser class
                Node node = FXMLLoader.load(getClass().getResource("/view/homepage.fxml"));  // Load the homepage view
                LoginAnchorPane.getChildren().setAll(node);  // Replace the current content with the homepage view
            } else {
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");  // Show error if login fails
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Login Error", "An error occurred while trying to log in.");
        }

    }

    @FXML
    void registerOnAction(ActionEvent event) throws IOException {
        LoginAnchorPane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Registration.fxml"));
        LoginAnchorPane.getChildren().add(load);
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
