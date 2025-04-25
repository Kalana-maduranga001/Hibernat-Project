package lk.ijse.gdse.project.hibernate_project.Dao.custome.impl;

import lk.ijse.gdse.project.hibernate_project.Dao.custome.SessionDao;

import lk.ijse.gdse.project.hibernate_project.Entity.TherapySession;
import lk.ijse.gdse.project.hibernate_project.bo.exeception.DuplicateException;
import lk.ijse.gdse.project.hibernate_project.bo.exeception.NotFoundException;
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
    public Optional<String> getNextId() throws SQLException, IOException {
        Session session = factoryConfiguration.getSession();

        String lastPk = session
                .createQuery("SELECT l.id FROM TherapySession l ORDER BY l.id DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();

        if (lastPk != null) {
            String substring = lastPk.substring(2);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("TS%03d", newIdIndex).describeConstable();
        }

        return "TS001".describeConstable();
    }

    @Override
    public boolean save(TherapySession therapySession) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {

            TherapySession existsTherapist = session.get(TherapySession.class, therapySession.getId());
            if (existsTherapist != null) {
                throw new DuplicateException("Customer id duplicated");
            }

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
            TherapySession therapySession = session.get(TherapySession.class, pk);
            if (therapySession == null) {
                throw new NotFoundException("Customer not found");
            }

            session.remove(therapySession);
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
        Query<TherapySession> query = session.createQuery("from Therapist", TherapySession.class);
        return query.list();
    }

    @Override
    public TherapySession findBy(String sessionId) throws SQLException, ClassNotFoundException {
        TherapySession therapySession = null;
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            therapySession = session.get(TherapySession.class, sessionId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch the Therapist by ID: " + sessionId);
        }
        return therapySession;
    }
}
