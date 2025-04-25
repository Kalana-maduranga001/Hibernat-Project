package lk.ijse.gdse.project.hibernate_project.Dao.custome.impl;

import lk.ijse.gdse.project.hibernate_project.Dao.custome.ProgramDao;

import lk.ijse.gdse.project.hibernate_project.Entity.TherapyProgram;
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

public class ProgramDaoImpl implements ProgramDao {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();


    @Override
    public Optional<String> getNextId() throws SQLException, IOException {
        Session session = factoryConfiguration.getSession();

        String lastPk = session
                .createQuery("SELECT l.id FROM TherapyProgram l ORDER BY l.id DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();

        if (lastPk != null && lastPk.startsWith("MT")) {
            String numericPart = lastPk.substring(2);  // Skip "MT" and get the numeric part
            int i = Integer.parseInt(numericPart);
            int newIdIndex = i + 1;
            return String.format("MT%03d", newIdIndex).describeConstable();
        }

        return "MT001".describeConstable();
    }

    @Override
    public boolean save(TherapyProgram therapyProgram) {

        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {

            TherapyProgram existsTherapist = session.get(TherapyProgram.class, therapyProgram.getId());
            if (existsTherapist != null) {
                throw new DuplicateException("Therapist Program id duplicated");
            }

            session.persist(therapyProgram);
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
    public boolean update(TherapyProgram therapyProgram) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(therapyProgram);
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
            TherapyProgram therapyProgram = session.get(TherapyProgram.class, pk);
            if (therapyProgram == null) {
                throw new NotFoundException("Customer not found");
            }

            session.remove(therapyProgram);
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
    public List<TherapyProgram> getAll() {
        Session session = factoryConfiguration.getSession();
        Query<TherapyProgram> query = session.createQuery("from TherapyProgram ", TherapyProgram.class);
        return query.list();
    }


    @Override
    public TherapyProgram findBy(String programId) throws SQLException, ClassNotFoundException {
        TherapyProgram therapyProgram = null;
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            therapyProgram = session.get(TherapyProgram.class, programId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch the Therapist by ID: " + programId);
        }
        return therapyProgram;
    }
}
