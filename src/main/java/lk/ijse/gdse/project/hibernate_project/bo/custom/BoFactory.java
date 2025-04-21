package lk.ijse.gdse.project.hibernate_project.bo.custom;

import lk.ijse.gdse.project.hibernate_project.bo.SuperBo;
import lk.ijse.gdse.project.hibernate_project.bo.custom.impl.PatientBoImpl;

public class BoFactory {
    public static BoFactory boFactory;
    private BoFactory() {
    }
    public static BoFactory getInstance() {
        return boFactory == null ? boFactory = new BoFactory() : boFactory;
    }

    public <T extends SuperBo>T getBO(BOType boTypes){
        return  switch (boTypes){
            case PATIENT -> (T) new PatientBoImpl();
        };
    }
}
