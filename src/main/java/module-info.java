module lk.ijse.gdse.project.hibernate_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens lk.ijse.gdse.project.hibernate_project to javafx.fxml;
    exports lk.ijse.gdse.project.hibernate_project;
}