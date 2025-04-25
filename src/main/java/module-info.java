module lk.ijse.gdse.project.hibernate_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.desktop;


    requires java.naming;


    opens lk.ijse.gdse.project.hibernate_project to javafx.fxml;
    opens lk.ijse.gdse.project.hibernate_project.config to jakarta.persistence;
    opens lk.ijse.gdse.project.hibernate_project.Controller to javafx.fxml;

    opens lk.ijse.gdse.project.hibernate_project.Dto.Tm to javafx.base;
    opens lk.ijse.gdse.project.hibernate_project.Entity to org.hibernate.orm.core;

    exports lk.ijse.gdse.project.hibernate_project;
}