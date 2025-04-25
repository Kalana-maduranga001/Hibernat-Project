package lk.ijse.gdse.project.hibernate_project.Dao.custome;

import lk.ijse.gdse.project.hibernate_project.Dao.CrudDao;
import lk.ijse.gdse.project.hibernate_project.Entity.Patient;

import java.sql.SQLException;
import java.util.Optional;


public interface PatientDao extends CrudDao<Patient , String> {
    Patient findBy(String patientId) throws SQLException, ClassNotFoundException;

}
