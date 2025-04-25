package lk.ijse.gdse.project.hibernate_project.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "therapist")
public class Therapist implements SuperEntity {
    @Id
    private String id;
    private String name;
    private int age;
    private String specialization;
    private String contact;

//    @OneToMany(mappedBy = "therapist")
//    private List<TherapyProgram> programs;

    @OneToMany(mappedBy = "therapist")
    private List<TherapySession> therapySessions;

    public Therapist(String id ,String name ,int age, String specialization, String contact) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.specialization = specialization;
        this.contact = contact;
    }


}


