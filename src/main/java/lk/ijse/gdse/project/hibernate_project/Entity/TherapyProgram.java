package lk.ijse.gdse.project.hibernate_project.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "therapyProgram")
public class TherapyProgram {
    @Id
    private String id;
    private String name;
    private BigDecimal fee;

    @Column(name = "duration(weeks)")
    private int duration;

    @ManyToOne
    @JoinTable(name = "therapistid")
    private Therapist therapist;

    @OneToMany(mappedBy = "therapyProgram")
    private List<Enrollment> enrollments;
}
