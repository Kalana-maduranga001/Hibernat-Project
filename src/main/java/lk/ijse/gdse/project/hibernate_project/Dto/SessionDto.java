package lk.ijse.gdse.project.hibernate_project.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SessionDto {
    private String id;
    private Date date;
    private String notes;
    private String patientId;
    private String therapietid;

    public SessionDto(String sessionid, String sessionDate, String program, String patient, String therapist) {
        this.id = sessionid;
        this.date = new Date(Long.parseLong(sessionDate));
        this.notes = program;
        this.patientId = patient;
        this.therapietid = therapist;
    }
}
