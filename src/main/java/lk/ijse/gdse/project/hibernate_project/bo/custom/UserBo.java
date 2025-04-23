package lk.ijse.gdse.project.hibernate_project.bo.custom;

import lk.ijse.gdse.project.hibernate_project.Dto.PatientDto;
import lk.ijse.gdse.project.hibernate_project.Dto.UserDto;
import lk.ijse.gdse.project.hibernate_project.bo.SuperBo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public interface UserBo extends SuperBo {
    boolean save(UserDto userDto);
    Optional<String> getNextId() throws SQLException, IOException;
}
