package lk.ijse.gdse.project.hibernate_project.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class ProgramDto {

    private String id;
    private String name;
    private BigDecimal fee;
    private int duration;

}
