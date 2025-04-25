package lk.ijse.gdse.project.hibernate_project.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "therapy_session")
<<<<<<< HEAD
public class TherapySession implements SuperEntity {
=======
public class TherapySession {
>>>>>>> d8048a3c57b25da4dff1b669c5f8a2db5aa065e8
    @Id
    private String id;
    private String notes;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinTable(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinTable(name = "therapist_id")
    private Therapist therapist;
}
<<<<<<< HEAD

=======
>>>>>>> d8048a3c57b25da4dff1b669c5f8a2db5aa065e8
