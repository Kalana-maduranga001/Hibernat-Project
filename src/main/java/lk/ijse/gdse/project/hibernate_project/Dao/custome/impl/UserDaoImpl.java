package lk.ijse.gdse.project.hibernate_project.Dao.custome.impl;

import lk.ijse.gdse.project.hibernate_project.Dao.custome.UserDao;

import lk.ijse.gdse.project.hibernate_project.Entity.User;
import lk.ijse.gdse.project.hibernate_project.bo.exeception.DuplicateException;
import lk.ijse.gdse.project.hibernate_project.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public Optional<String> getNextId() throws SQLException, IOException {
        Session session = factoryConfiguration.getSession();

        String lastPk = session
                .createQuery("SELECT l.id FROM User l ORDER BY l.id DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();

        if (lastPk != null) {
            String substring = lastPk.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("U%03d", newIdIndex).describeConstable();
        }

        return "U001".describeConstable();
    }

    @Override
    public boolean save(User user) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {

            User existsUser = session.get(User.class, user.getId());
            if (existsUser != null) {
                throw new DuplicateException("Customer id duplicated");
            }

            session.persist(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean deleteByPK(String pk) throws Exception {
        return false;
    }

    @Override
    public List<User> getAll() {
        return List.of();
    }
}
