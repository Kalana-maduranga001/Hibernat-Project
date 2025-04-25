package lk.ijse.gdse.project.hibernate_project.Dao.custome;

import lk.ijse.gdse.project.hibernate_project.Dao.CrudDao;
import lk.ijse.gdse.project.hibernate_project.Entity.Patient;
import lk.ijse.gdse.project.hibernate_project.Entity.Payment;

import java.sql.SQLException;
import java.util.List;

public interface PaymentDao extends CrudDao<Payment , String> {
    public Patient findBy(String patientId) throws SQLException, ClassNotFoundException;
    public List<String> getAllIds();

}
