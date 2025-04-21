package lk.ijse.gdse.project.hibernate_project.Dao;

import lk.ijse.gdse.project.hibernate_project.Dao.custome.impl.PatientDaoImpl;

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
        USER,THERAPIST,THERAPYSESSION,PATIENT,THERAPYPROGRAMME,
    }

    public SuperDao getDao(daoType type) {
        switch (type) {
            case PATIENT:
                return new PatientDaoImpl();
            default:
                return null;
        }
    }
}
