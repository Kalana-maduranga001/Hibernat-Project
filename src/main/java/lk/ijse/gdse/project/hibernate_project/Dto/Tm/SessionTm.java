package lk.ijse.gdse.project.hibernate_project.Dto.Tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class SessionTm {
    private String id;
    private Date date;
    private String programid;
    private String patientId;
    private String therapietid;
}
