package lk.ijse.gdse.project.hibernate_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/view/LoginPage.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1130, 741);
        stage.setTitle("Mantel therapy Health System!");

        Image image = new Image(getClass().getResourceAsStream("/images/logodesign.png"));
        stage.getIcons().add(image);

        stage.setScene(scene);
        stage.show();
    }

}