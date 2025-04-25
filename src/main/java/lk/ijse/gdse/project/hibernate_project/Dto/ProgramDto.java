package lk.ijse.gdse.project.hibernate_project.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class ProgramDto {

    private String id;
    private String name;
    private double fee;
    private int duration;
    private String therapiesId;

}
