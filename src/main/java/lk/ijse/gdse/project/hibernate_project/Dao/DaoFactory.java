package lk.ijse.gdse.project.hibernate_project.Dao;

import lk.ijse.gdse.project.hibernate_project.Dao.custome.impl.*;

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
        USER,THERAPIST,SESSION,PATIENT,PROGRAMME,PAYMENT
    }

    public SuperDao getDao(daoType type) {
        switch (type) {
            case PATIENT:
                return new PatientDaoImpl();
            case THERAPIST:
                return new TherapistDaoImpl();
            case PROGRAMME:
                return new ProgramDaoImpl();
            case USER:
                return new UserDaoImpl();
            case PAYMENT:
                return new PaymentDaoImpl();
<<<<<<< HEAD
            case SESSION:
                return new SessionDaoImpl();
=======
>>>>>>> d8048a3c57b25da4dff1b669c5f8a2db5aa065e8
            default:
                return null;
        }
    }
}
