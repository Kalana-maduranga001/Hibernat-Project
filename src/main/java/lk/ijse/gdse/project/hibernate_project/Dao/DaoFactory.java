package lk.ijse.gdse.project.hibernate_project.Dao;

import lk.ijse.gdse.project.hibernate_project.Dao.custome.impl.PatientDaoImpl;
import lk.ijse.gdse.project.hibernate_project.Dao.custome.impl.TherapistDaoImpl;

public class DaoFactory {
    private static DaoFactory daoFactory = new DaoFactory();
    private DaoFactory() {

    }
    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();

        }
        return daoFactory;
    }

    public enum daoType {
        USER,THERAPIST,SESSION,PATIENT,PROGRAMME,
    }

    public SuperDao getDao(daoType type) {
        switch (type) {
            case PATIENT:
                return new PatientDaoImpl();
            case THERAPIST:
                return new TherapistDaoImpl();
            default:
                return null;
        }
    }
}
