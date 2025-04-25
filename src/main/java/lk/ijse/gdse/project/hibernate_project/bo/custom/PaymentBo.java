package lk.ijse.gdse.project.hibernate_project.bo.custom;

import lk.ijse.gdse.project.hibernate_project.Dto.PatientDto;
import lk.ijse.gdse.project.hibernate_project.Dto.PaymentDto;
import lk.ijse.gdse.project.hibernate_project.Entity.TherapyProgram;
import lk.ijse.gdse.project.hibernate_project.bo.SuperBo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PaymentBo extends SuperBo {
    Optional<String> getNextId() throws SQLException, IOException;
    boolean save(PaymentDto paymentDto);
    boolean update(PaymentDto paymentDtoDto);
    boolean deleteByPK(String pk) throws Exception;
    List<PaymentDto> getAll() throws SQLException, IOException;
    String getPatientNameById(String patientId);
    TherapyProgram getProgramById(String id) throws SQLException, ClassNotFoundException;
    List<String> getAllPaymentIds() throws SQLException, IOException;
    List<String> getAllProgramIds() throws SQLException, IOException, ClassNotFoundException;
}
