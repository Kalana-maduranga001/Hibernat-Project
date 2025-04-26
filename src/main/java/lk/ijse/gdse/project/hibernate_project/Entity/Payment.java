package lk.ijse.gdse.project.hibernate_project.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "payment")
public class Payment implements SuperEntity {
    @Id
    private String id;
    private String patientId;
    private String programId;
    private String date;
    private String status;
    private double amount;



//    @ManyToOne
//    @JoinTable(name = "patient_id")
//    private Patient patient;


}
