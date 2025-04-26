package lk.ijse.gdse.project.hibernate_project.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SessionDto {
    private String sessionId;
    private LocalDate sessionDate;
    private String patientId;
    private String therapistId;
    private String programId;
}
