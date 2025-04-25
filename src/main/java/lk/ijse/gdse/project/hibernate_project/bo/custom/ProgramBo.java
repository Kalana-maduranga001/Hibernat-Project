package lk.ijse.gdse.project.hibernate_project.bo.custom;

import lk.ijse.gdse.project.hibernate_project.Dto.ProgramDto;
import lk.ijse.gdse.project.hibernate_project.Entity.TherapyProgram;
import lk.ijse.gdse.project.hibernate_project.bo.SuperBo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProgramBo extends SuperBo {
    Optional<String> getNextId() throws SQLException, IOException;
    boolean save(ProgramDto programDto);
    boolean update(ProgramDto programDto);
    boolean deleteByPK(String pk) throws Exception;
    List<ProgramDto> getAll() throws SQLException, IOException;
    List<String> getAllTherapistIds() throws SQLException, IOException, ClassNotFoundException;

}
