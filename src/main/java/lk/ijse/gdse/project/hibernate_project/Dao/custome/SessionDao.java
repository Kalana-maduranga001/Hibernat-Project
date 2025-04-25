package lk.ijse.gdse.project.hibernate_project.Dao.custome;

import lk.ijse.gdse.project.hibernate_project.Dao.CrudDao;

import lk.ijse.gdse.project.hibernate_project.Entity.TherapySession;

import java.sql.SQLException;

public interface SessionDao extends CrudDao<TherapySession , String> {
     TherapySession findBy(String sessionId) throws SQLException, ClassNotFoundException;
}
