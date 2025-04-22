package lk.ijse.gdse.project.hibernate_project.Dao.custome;

import lk.ijse.gdse.project.hibernate_project.Dao.CrudDao;

import lk.ijse.gdse.project.hibernate_project.Entity.Therapist;

import java.sql.SQLException;

public interface TherapistDao extends CrudDao<Therapist , String> {
    Therapist findBy(String therapistId) throws SQLException, ClassNotFoundException;
}
