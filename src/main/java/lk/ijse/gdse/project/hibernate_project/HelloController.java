package lk.ijse.gdse.project.hibernate_project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lk.ijse.gdse.project.hibernate_project.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

public class HelloController {

//    @FXML
//    private Label welcomeText;
//
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
public static void main(String[] args) throws IOException {
    Session session = FactoryConfiguration.getInstance().getSession();
    Transaction transaction = session.beginTransaction();
    session.close();
}

}