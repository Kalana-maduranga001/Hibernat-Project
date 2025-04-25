package lk.ijse.gdse.project.hibernate_project.Dao.custome.impl;

import lk.ijse.gdse.project.hibernate_project.Dao.custome.PatientDao;
import lk.ijse.gdse.project.hibernate_project.Entity.Patient;

import lk.ijse.gdse.project.hibernate_project.bo.exeception.DuplicateException;
import lk.ijse.gdse.project.hibernate_project.bo.exeception.NotFoundException;
import lk.ijse.gdse.project.hibernate_project.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PatientDaoImpl implements PatientDao {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public Optional<String> getNextId(){
        Session session = factoryConfiguration.getSession();

        String lastPk = session
                .createQuery("SELECT l.id FROM Patient l ORDER BY l.id DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();

        if (lastPk != null) {
            String substring = lastPk.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("P%03d", newIdIndex).describeConstable();
        }

        return "P001".describeConstable();
    }

    @Override
    public boolean save(Patient patient) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {

            Patient existsPatient = session.get(Patient.class, patient.getId());
            if (existsPatient != null) {
                throw new DuplicateException("Customer id duplicated");
            }

            session.persist(patient);
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
    public boolean update(Patient patient) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(patient);
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
            Patient patient = session.get(Patient.class, pk);
            if (patient == null) {
                throw new NotFoundException("Customer not found");
            }

            session.remove(patient);
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
    public List<Patient> getAll() {
        Session session = factoryConfiguration.getSession();
        Query<Patient> query = session.createQuery("from Patient", Patient.class);
        return query.list();
    }


    @Override
    public Patient findBy(String patientId) throws SQLException, ClassNotFoundException {
        Patient patient = null;
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            patient = session.get(Patient.class, patientId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch the patient by ID: " + patientId);
        }
        return patient;
    }


}
