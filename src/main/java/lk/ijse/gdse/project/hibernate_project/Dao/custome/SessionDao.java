package lk.ijse.gdse.project.hibernate_project.Dao.custome;
import lk.ijse.gdse.project.hibernate_project.Dao.CrudDao;
import lk.ijse.gdse.project.hibernate_project.Entity.TherapySession;

import java.util.List;


public interface SessionDao extends CrudDao<TherapySession , String> {
     List<TherapySession> findSessionsByTherapistId(String therapistId);
}
