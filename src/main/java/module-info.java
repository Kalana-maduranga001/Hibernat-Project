module lk.ijse.gdse.project.hibernate_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;


    opens lk.ijse.gdse.project.hibernate_project to javafx.fxml;
    exports lk.ijse.gdse.project.hibernate_project;
}