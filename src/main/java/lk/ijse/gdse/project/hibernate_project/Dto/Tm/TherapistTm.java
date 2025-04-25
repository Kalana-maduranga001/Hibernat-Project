package lk.ijse.gdse.project.hibernate_project.Dto.Tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class TherapistTm {
    private String id;
    private String name;
    private int age;
    private String specialization;
    private String contact;
}
