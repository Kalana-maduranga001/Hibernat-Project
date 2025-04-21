package lk.ijse.gdse.project.hibernate_project.bo.custom;

import lk.ijse.gdse.project.hibernate_project.Dto.PatientDto;
import lk.ijse.gdse.project.hibernate_project.Entity.Patient;
import lk.ijse.gdse.project.hibernate_project.bo.SuperBo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PatientBo extends SuperBo {
      Optional<String> getNextId() throws SQLException, IOException;
      boolean save(PatientDto patientDto);
      boolean update(PatientDto patientDto);
      boolean deleteByPK(String pk) throws Exception;
      List<PatientDto> getAll() throws SQLException, IOException;

}
