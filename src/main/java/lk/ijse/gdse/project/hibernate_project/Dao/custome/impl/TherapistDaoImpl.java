package lk.ijse.gdse.project.hibernate_project.Dao.custome.impl;

import lk.ijse.gdse.project.hibernate_project.Dao.custome.TherapistDao;
import lk.ijse.gdse.project.hibernate_project.Entity.Patient;
import lk.ijse.gdse.project.hibernate_project.Entity.Therapist;
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

public class TherapistDaoImpl implements TherapistDao {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();


    @Override
    public Optional<String> getNextId() throws SQLException, IOException {
        Session session = factoryConfiguration.getSession();

        String lastPk = session
                .createQuery("SELECT l.id FROM Therapist l ORDER BY l.id DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();

        if (lastPk != null) {
            String substring = lastPk.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("T%03d", newIdIndex).describeConstable();
        }

        return "T001".describeConstable();
    }

    @Override
    public boolean save(Therapist therapist) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {

            Therapist existsTherapist = session.get(Therapist.class, therapist.getId());
            if (existsTherapist != null) {
                throw new DuplicateException("Customer id duplicated");
            }

            session.persist(therapist);
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
    public boolean update(Therapist therapist) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(therapist);
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
            Therapist therapist = session.get(Therapist.class, pk);
            if (therapist == null) {
                throw new NotFoundException("Customer not found");
            }

            session.remove(therapist);
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
    public List<Therapist> getAll() {
        Session session = factoryConfiguration.getSession();
        Query<Therapist> query = session.createQuery("from Therapist", Therapist.class);
        return query.list();
    }


    @Override
    public Therapist findBy(String therapistId) throws SQLException, ClassNotFoundException {
        Therapist therapist = null;
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            therapist = session.get(Therapist.class, therapistId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch the Therapist by ID: " + therapistId);
        }
        return therapist;
    }
}
