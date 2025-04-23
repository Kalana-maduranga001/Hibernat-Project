package lk.ijse.gdse.project.hibernate_project.Dao.custome.impl;

import lk.ijse.gdse.project.hibernate_project.Dao.custome.PaymentDao;

import lk.ijse.gdse.project.hibernate_project.Entity.Patient;
import lk.ijse.gdse.project.hibernate_project.Entity.Payment;
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

public class PaymentDaoImpl implements PaymentDao {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();


    @Override
    public Patient findBy(String patientId) throws SQLException, ClassNotFoundException {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.get(Patient.class, patientId); // This returns the Patient entity
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch the patient by ID: " + patientId);
        }
    }

    @Override
    public List<String> getAllIds() {
        Session session = factoryConfiguration.getSession();
        Query<String> query = session.createQuery("SELECT p.id FROM Patient p", String.class);
        return query.list();
    }


    @Override
    public Optional<String> getNextId() throws SQLException, IOException {
        Session session = factoryConfiguration.getSession();

        String lastPk = session
                .createQuery("SELECT l.id FROM Payment l ORDER BY l.id DESC", String.class)
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
    public boolean save(Payment payment) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {

            Payment existsPayment = session.get(Payment.class, payment.getId());
            if (existsPayment != null) {
                throw new DuplicateException("Payment id duplicated");
            }

            session.persist(payment);
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
    public boolean update(Payment payment) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(payment);
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
            Payment payment = session.get(Payment.class, pk);
            if (payment == null) {
                throw new NotFoundException("Customer not found");
            }

            session.remove(payment);
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
    public List<Payment> getAll() {
        Session session = factoryConfiguration.getSession();
        Query<Payment> query = session.createQuery("from Payment ", Payment.class);
        return query.list();
    }
}
