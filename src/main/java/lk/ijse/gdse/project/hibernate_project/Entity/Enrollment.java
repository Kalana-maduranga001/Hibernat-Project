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
@Table(name = "enrollment")
public class Enrollment {
    @Id
    private String id;
    private Date date;

    @ManyToOne
    @JoinTable(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinTable(name = "therapy_program_id")
    private TherapyProgram therapyProgram;
}
