package lk.ijse.gdse.project.hibernate_project.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    public static boolean isAdmin;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnPationt;

    @FXML
    private Button btnPayment;

    @FXML
    private Button btnPrograme;

    @FXML
    private Button btnSesstion;

    @FXML
    private Button btnTheraphist;

    @FXML
    private ImageView imgPrograme;

    @FXML
    private ImageView imgTheraphist;

    @FXML
    private AnchorPane loadAnchorpane;

    @FXML
    private AnchorPane mainAnchorpane;


    @FXML
    void logoutOnAction(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to Log out this Programme?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = true;
            if (isDeleted) {
                mainAnchorpane.getChildren().clear();
                AnchorPane load = FXMLLoader.load(getClass().getResource("/View/LoginPage.fxml"));
                mainAnchorpane.getChildren().add(load);
            } else {
                new Alert(Alert.AlertType.ERROR, "Log out not Programme").show();
            }
        }
    }

    @FXML
    void pationtOnAction(ActionEvent event) {
        navigateTo("/View/patient.fxml");
    }

    @FXML
    void paymentOnAction(ActionEvent event) {
        navigateTo("/View/Payment.fxml");

    }

    @FXML
    void programeOnAction(ActionEvent event) {
        navigateTo("/View/Programme.fxml");

    }

    @FXML
    void sessionOnAction(ActionEvent event) {
        navigateTo("/View/TheraphiSession.fxml");

    }

    @FXML
    void theraphistOnAction(ActionEvent event) {
        navigateTo("/View/therphist.fxml");

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        if (isAdmin){
//            btnTheraphist.setDisable(false);
//            btnSesstion.setDisable(false);
//            btnPrograme.setDisable(false);
//            btnPationt.setDisable(false);
//            btnPayment.setDisable(false);
//        }else {
//
//            btnTheraphist.setDisable(true);
//            imgTheraphist.setVisible(true);
//            btnSesstion.setDisable(false);
//            btnPrograme.setDisable(true);
//            imgPrograme.setVisible(true);
//            btnPationt.setDisable(false);
//            btnPayment.setDisable(false);
//        }
    }


    private void navigateTo(String fxmlPath) {
        try {
            loadAnchorpane.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane loadedPane = loader.load();
            loadAnchorpane.getChildren().add(loadedPane);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to load view: " + fxmlPath);
        }
        System.out.println("Loading FXML: " + fxmlPath);
    }

}
