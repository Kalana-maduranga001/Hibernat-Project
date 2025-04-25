package lk.ijse.gdse.project.hibernate_project.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "patient")
public class Patient implements SuperEntity {
    @Id
    private String id;
    private String name;
    private String gender;
    private LocalDate dateOfBirth;
    private String medicalHistory;
    private String address;
    private String contact;

    @OneToMany(mappedBy = "patient")
    private List<Payment> payments;

    @OneToMany(mappedBy = "patient")
    private List<TherapySession> therapySessions;

    @OneToMany(mappedBy = "patient")
    private List<Enrollment> enrollments;



    public Patient(String id, String name, String gender, LocalDate dateOfBirth, String medicalHistory, String address, String contact) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.medicalHistory = medicalHistory;
        this.address = address;
        this.contact = contact;
    }



}
