package lk.ijse.gdse.project.hibernate_project.bo.custom;


import lk.ijse.gdse.project.hibernate_project.Dto.PatientDto;
import lk.ijse.gdse.project.hibernate_project.Dto.ProgramDto;
import lk.ijse.gdse.project.hibernate_project.Dto.SessionDto;
import lk.ijse.gdse.project.hibernate_project.Dto.TherapistDto;
import lk.ijse.gdse.project.hibernate_project.bo.SuperBo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public interface SessionBo extends SuperBo {

    boolean saveSession(SessionDto dto);
    boolean updateSession(SessionDto dto);
    boolean deleteSession(String id) throws Exception;
    String getNextSessionID() throws SQLException, IOException;
    List<SessionDto> getSessions();
    List<PatientDto> getPatients();
    List<ProgramDto> getPrograms();
    SessionDto getSession(String id);
    PatientDto getPatient(String id);
    ProgramDto getProgram(String id);
    TherapistDto getTherapist(String id);
    List<SessionDto> getSessionsFromTherapist(String id);

}
