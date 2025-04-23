package lk.ijse.gdse.project.hibernate_project.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UserDto {
    private String id;
    private String username;
    private String password;
    private String role;

}
