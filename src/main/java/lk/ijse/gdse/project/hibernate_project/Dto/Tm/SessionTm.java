package lk.ijse.gdse.project.hibernate_project.Dto.Tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class SessionTm {
    private String sessionId;
    private LocalDate sessionDate;
    private String patientId;
    private String therapistId;
    private String programId;
}
