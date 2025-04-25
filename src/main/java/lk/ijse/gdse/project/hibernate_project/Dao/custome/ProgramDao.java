package lk.ijse.gdse.project.hibernate_project.Dao.custome;

import lk.ijse.gdse.project.hibernate_project.Dao.CrudDao;
import lk.ijse.gdse.project.hibernate_project.Entity.TherapyProgram;

import java.sql.SQLException;
import java.util.List;

public interface ProgramDao extends CrudDao<TherapyProgram , String> {
    TherapyProgram findBy(String programId) throws SQLException, ClassNotFoundException;
    List<String> getTherapistIds() throws SQLException, ClassNotFoundException;

}
