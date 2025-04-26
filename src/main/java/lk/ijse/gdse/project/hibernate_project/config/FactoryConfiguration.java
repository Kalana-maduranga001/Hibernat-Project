package lk.ijse.gdse.project.hibernate_project.config;

import lk.ijse.gdse.project.hibernate_project.Entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;


public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;
    private FactoryConfiguration() throws IOException {

        Configuration configuration = new Configuration();
        Properties properties = new Properties();

        properties.load(Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("hibernate.properties"));

        configuration.setProperties(properties);

        configuration.addAnnotatedClass(Patient.class);
        configuration.addAnnotatedClass(Enrollment.class);
        configuration.addAnnotatedClass(Payment.class);
        configuration.addAnnotatedClass(Therapist.class);
        configuration.addAnnotatedClass(TherapyProgram.class);
        configuration.addAnnotatedClass(TherapySession.class);
        configuration.addAnnotatedClass(User.class);

        sessionFactory = configuration.buildSessionFactory();

//        Configuration config = new Configuration().configure()
//                .addAnnotatedClass(Enrollment.class)
//                .addAnnotatedClass(Patient.class)
//                .addAnnotatedClass(Payment.class)
//                .addAnnotatedClass(Therapist.class)
//                .addAnnotatedClass(TherapyProgram.class)
//                .addAnnotatedClass(TherapySession.class)
//                .addAnnotatedClass(User.class);
//        sessionFactory = config.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance(){
//        return (factoryConfiguration == null)
//                ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;

        try {
            return factoryConfiguration == null ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

}
