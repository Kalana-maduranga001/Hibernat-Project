package lk.ijse.gdse.project.hibernate_project.Dao.custome.impl;
import lk.ijse.gdse.project.hibernate_project.Dao.custome.SessionDao;
import lk.ijse.gdse.project.hibernate_project.Entity.TherapySession;
import lk.ijse.gdse.project.hibernate_project.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SessionDaoImpl implements SessionDao {
    FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(TherapySession therapySession) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.persist(therapySession);
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
    public boolean update(TherapySession therapySession) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.merge(therapySession);
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
    public boolean deleteByPK(String pk) throws Exception {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            TherapySession entity = session.get(TherapySession.class, pk);
            session.remove(entity);
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
    public List<TherapySession> getAll() {
        Session session = factoryConfiguration.getSession();
        Query<TherapySession> query = session.createQuery("from TherapySession", TherapySession.class);
        return query.list();
    }

    @Override
    public Optional<TherapySession> findById(String s) {
        Session session = factoryConfiguration.getSession();
        TherapySession therapySession = session.get(TherapySession.class, s);
        session.close();

        if (therapySession == null) {
            return Optional.empty();
        }
        return Optional.of(therapySession);
    }

    @Override
    public List<TherapySession> findSessionsByTherapistId(String therapistId) {
        Session session = factoryConfiguration.getSession();
        List<TherapySession> sessions = session
                .createQuery("FROM TherapySession s WHERE s.therapist.id = :id", TherapySession.class)
                .setParameter("id", therapistId)
                .list();
        session.close();
        return sessions;
    }

    @Override
    public Optional<String> getNextId() throws SQLException, IOException {
        Session session = factoryConfiguration.getSession();
        String lastId = session
                .createQuery("SELECT s.id FROM TherapySession s ORDER BY s.id DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();
        return Optional.ofNullable(lastId);
    }
}
