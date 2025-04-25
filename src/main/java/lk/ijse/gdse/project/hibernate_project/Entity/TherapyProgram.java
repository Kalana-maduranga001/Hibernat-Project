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
public class TherapyProgram implements SuperEntity {
    @Id
    private String id;
    private String name;
    private double fee;

    @Column(name = "duration(weeks)")
    private int duration;



    @ManyToOne
    @JoinColumn(name = "therapistId", referencedColumnName = "id", insertable = false, updatable = false)
    private Therapist therapist;

    @Column(name = "therapistId")
    private String therapistId;


    @OneToMany(mappedBy = "therapyProgram")
    private List<Enrollment> enrollments;



    public TherapyProgram(String id, String name, double fee , int duration ,String therapistId) {
        this.id = id;
        this.name = name;
        this.fee = fee;
        this.duration = duration;
        this.therapistId = therapistId;
    }
}
