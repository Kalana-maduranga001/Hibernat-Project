package lk.ijse.gdse.project.hibernate_project.Dto.Tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class ProgramTm {

    private String id;
    private String name;
    private double fee;
    private int duration;
    private String therapistId;


}
