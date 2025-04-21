package lk.ijse.gdse.project.hibernate_project.config;

import lk.ijse.gdse.project.hibernate_project.Entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;
    private FactoryConfiguration() {
        Configuration config = new Configuration().configure()
                .addAnnotatedClass(Enrollment.class)
                .addAnnotatedClass(Patient.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Therapist.class)
                .addAnnotatedClass(TherapyProgram.class)
                .addAnnotatedClass(TherapySession.class)
                .addAnnotatedClass(User.class);
        sessionFactory = config.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        return (factoryConfiguration == null)
                ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

}
