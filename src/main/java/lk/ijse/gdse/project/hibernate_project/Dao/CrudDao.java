package lk.ijse.gdse.project.hibernate_project.Dao;

import lk.ijse.gdse.project.hibernate_project.Entity.Patient;
import lk.ijse.gdse.project.hibernate_project.Entity.SuperEntity;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudDao <T extends SuperEntity , ID> extends SuperDao {

     Optional<String> getNextId() throws SQLException, IOException;
     boolean save(T t);
     boolean update(T t);
     boolean deleteByPK(ID pk) throws Exception;
     List<T> getAll();

}
