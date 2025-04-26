package lk.ijse.gdse.project.hibernate_project.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "therapy_session")

public class TherapySession implements SuperEntity {



    @Id
    private String id;

    private LocalDate date;

    @ManyToOne
    @JoinTable(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinTable(name = "therapist_id")
    private Therapist therapist;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private TherapyProgram program;

}
