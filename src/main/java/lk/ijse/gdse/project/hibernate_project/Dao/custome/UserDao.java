package lk.ijse.gdse.project.hibernate_project.Dao.custome;

import lk.ijse.gdse.project.hibernate_project.Dao.CrudDao;
import lk.ijse.gdse.project.hibernate_project.Entity.User;

import java.util.Optional;

public interface UserDao extends CrudDao<User,String> {
     Optional<User> findByUsername(String username);
     boolean update(User entity);
     Optional<String> getLastId();
     Optional<User> findById(String s);

}
