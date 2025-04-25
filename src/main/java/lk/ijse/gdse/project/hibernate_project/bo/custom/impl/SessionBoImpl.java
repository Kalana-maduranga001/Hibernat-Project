package lk.ijse.gdse.project.hibernate_project.bo.custom.impl;

import lk.ijse.gdse.project.hibernate_project.Dao.DaoFactory;
import lk.ijse.gdse.project.hibernate_project.Dao.custome.PatientDao;
import lk.ijse.gdse.project.hibernate_project.Dao.custome.SessionDao;
import lk.ijse.gdse.project.hibernate_project.Dao.custome.TherapistDao;
import lk.ijse.gdse.project.hibernate_project.Dto.PatientDto;
import lk.ijse.gdse.project.hibernate_project.Dto.SessionDto;


import lk.ijse.gdse.project.hibernate_project.Dto.TherapistDto;
import lk.ijse.gdse.project.hibernate_project.Entity.Patient;
import lk.ijse.gdse.project.hibernate_project.Entity.Therapist;
import lk.ijse.gdse.project.hibernate_project.Entity.TherapySession;
import lk.ijse.gdse.project.hibernate_project.bo.custom.SessionBo;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SessionBoImpl implements SessionBo {

    SessionDao sessionDao = (SessionDao) DaoFactory.getInstance().getDao(DaoFactory.daoType.SESSION);
    PatientDao patientDao = (PatientDao) DaoFactory.getInstance().getDao(DaoFactory.daoType.PATIENT);
    TherapistDao therapistDao = (TherapistDao) DaoFactory.getInstance().getDao(DaoFactory.daoType.THERAPIST);

    @Override
    public Optional<String> getNextId() throws SQLException, IOException {
        return sessionDao.getNextId();
    }

    @Override
    public boolean save(SessionDto sessionDto) throws SQLException, ClassNotFoundException {
        Patient patient = patientDao.findBy(sessionDto.getPatientId());
        Therapist therapist = therapistDao.findBy(sessionDto.getTherapietid());

        return sessionDao.save(new TherapySession(
                sessionDto.getId(),
                sessionDto.getNotes(),
                sessionDto.getDate(),
                patient,
                therapist
        ));
    }

    @Override
    public boolean update(SessionDto sessionDto) throws SQLException, ClassNotFoundException {
        Patient patient = patientDao.findBy(sessionDto.getPatientId());
        Therapist therapist = therapistDao.findBy(sessionDto.getTherapietid());

        return sessionDao.update(new TherapySession(
                sessionDto.getId(),
                sessionDto.getNotes(),
                sessionDto.getDate(),
                patient,
                therapist
        ));
    }

    @Override
    public boolean deleteByPK(String pk) throws Exception {
        return sessionDao.deleteByPK(pk);
    }

    @Override
    public List<SessionDto> getAll() throws SQLException, IOException {

        List<TherapySession> sessions = sessionDao.getAll();
        List<SessionDto> sessionDtos = new ArrayList<>();
        for(TherapySession session:sessions){

            SessionDto sessionDto = new SessionDto();

            sessionDto.setId(session.getId());
            sessionDto.setNotes(session.getNotes());
            sessionDto.setDate(session.getDate());
            sessionDto.setPatientId(session.getPatient().getId());
            sessionDto.setTherapietid(session.getTherapist().getId());

            sessionDtos.add(sessionDto);
        }
        return sessionDtos;
    }

    @Override
    public PatientDto findPatientByPK(String pk) throws SQLException, ClassNotFoundException {
        Patient patient = patientDao.findBy(pk);
        PatientDto patientDto = new PatientDto();

        patientDto.setId(patient.getId());
        patientDto.setMedicalHistory(patient.getMedicalHistory());
        patientDto.setDateOfBirth(patient.getDateOfBirth());
        patientDto.setGender(patient.getGender());
        patientDto.setContact(patient.getContact());
        patientDto.setAddress(patient.getAddress());
        patientDto.setName(patient.getName());

        return null;
    }

    @Override
    public TherapistDto findTherapistByPK(String pk) throws SQLException, ClassNotFoundException {
        Therapist therapist = therapistDao.findBy(pk);
        TherapistDto therapistDto = new TherapistDto();

        therapistDto.setId(therapist.getId());
        therapistDto.setContact(therapist.getContact());
        therapistDto.setName(therapist.getName());
        therapistDto.setAge(therapist.getAge());
        therapistDto.setSpecialization(therapist.getSpecialization());

        return null;
    }


}
