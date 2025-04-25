package lk.ijse.gdse.project.hibernate_project.bo.custom;

import lk.ijse.gdse.project.hibernate_project.Dto.TherapistDto;
import lk.ijse.gdse.project.hibernate_project.bo.SuperBo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface TherapistBo extends SuperBo {
    Optional<String> getNextId() throws SQLException, IOException;
    boolean save(TherapistDto therapistDto);
    boolean update(TherapistDto therapistDto);
    boolean deleteByPK(String pk) throws Exception;
    List<TherapistDto> getAll() throws SQLException, IOException;
}