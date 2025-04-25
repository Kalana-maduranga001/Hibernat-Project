package lk.ijse.gdse.project.hibernate_project.Dao.custome;

import lk.ijse.gdse.project.hibernate_project.Dao.CrudDao;
import lk.ijse.gdse.project.hibernate_project.Entity.Patient;
import lk.ijse.gdse.project.hibernate_project.Entity.Payment;
import lk.ijse.gdse.project.hibernate_project.Entity.TherapyProgram;

import java.sql.SQLException;
import java.util.List;

public interface PaymentDao extends CrudDao<Payment , String> {
     Patient findBy(String patientId) throws SQLException, ClassNotFoundException;
     List<String> getAllIds();
     List<String> getAllprogramIds() throws SQLException, ClassNotFoundException;
     TherapyProgram findProgramById(String id) throws SQLException, ClassNotFoundException;

}
