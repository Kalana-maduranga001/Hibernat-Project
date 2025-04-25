package lk.ijse.gdse.project.hibernate_project.bo.custom;


import lk.ijse.gdse.project.hibernate_project.Dto.PatientDto;
import lk.ijse.gdse.project.hibernate_project.Dto.SessionDto;
import lk.ijse.gdse.project.hibernate_project.Dto.TherapistDto;
import lk.ijse.gdse.project.hibernate_project.bo.SuperBo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface SessionBo extends SuperBo {

    Optional<String> getNextId() throws SQLException, IOException;
    boolean save(SessionDto sessionDto) throws SQLException, ClassNotFoundException;
    boolean update(SessionDto sessionDto) throws SQLException, ClassNotFoundException;
    boolean deleteByPK(String pk) throws Exception;
    List<SessionDto> getAll() throws SQLException, IOException;
    PatientDto findPatientByPK(String pk) throws SQLException, ClassNotFoundException;
    TherapistDto findTherapistByPK(String pk) throws SQLException, ClassNotFoundException;

}
