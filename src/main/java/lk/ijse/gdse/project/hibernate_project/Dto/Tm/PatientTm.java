package lk.ijse.gdse.project.hibernate_project.Dto.Tm;

import lombok.*;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class PatientTm {

    private String id;
    private String name;
    private String gender;
    private LocalDate dateOfBirth;
    private String medicalHistory;
    private String address;
    private String contact;

}
