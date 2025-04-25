package lk.ijse.gdse.project.hibernate_project.bo.custom;

import lk.ijse.gdse.project.hibernate_project.bo.SuperBo;
import lk.ijse.gdse.project.hibernate_project.bo.custom.impl.*;

public class BoFactory {
    public static BoFactory boFactory;
    private BoFactory() {
    }
    public static BoFactory getInstance() {
        return boFactory == null ? boFactory = new BoFactory() : boFactory;
    }

    public <T extends SuperBo>T getBO(BOType boTypes){
        return  switch (boTypes){
            case PATIENT    ->  (T) new PatientBoImpl();
            case THERAPIST  ->  (T) new TherapistBoImpl();
            case  PROGRAMME ->  (T) new ProgramBoImpl();
            case USER       ->  (T) new UserBoImpl();
            case PAYMENT    ->  (T) new PaymentBoImpl();
<<<<<<< HEAD
            case SESSION    ->  (T) new SessionBoImpl();
=======
>>>>>>> d8048a3c57b25da4dff1b669c5f8a2db5aa065e8
        };
    }
}
