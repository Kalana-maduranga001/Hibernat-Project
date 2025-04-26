package lk.ijse.gdse.project.hibernate_project.bo.custom.impl;

import lk.ijse.gdse.project.hibernate_project.Dao.DaoFactory;
import lk.ijse.gdse.project.hibernate_project.Dao.custome.PatientDao;
import lk.ijse.gdse.project.hibernate_project.Dao.custome.ProgramDao;
import lk.ijse.gdse.project.hibernate_project.Dao.custome.SessionDao;
import lk.ijse.gdse.project.hibernate_project.Dao.custome.TherapistDao;
import lk.ijse.gdse.project.hibernate_project.Dto.PatientDto;
import lk.ijse.gdse.project.hibernate_project.Dto.ProgramDto;
import lk.ijse.gdse.project.hibernate_project.Dto.SessionDto;


import lk.ijse.gdse.project.hibernate_project.Dto.TherapistDto;
import lk.ijse.gdse.project.hibernate_project.Entity.Patient;
import lk.ijse.gdse.project.hibernate_project.Entity.Therapist;
import lk.ijse.gdse.project.hibernate_project.Entity.TherapyProgram;
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
    ProgramDao therapyProgramDAO = (ProgramDao) DaoFactory.getInstance().getDao(DaoFactory.daoType.PROGRAMME);

    @Override
    public boolean saveSession(SessionDto dto) {
        Optional<Patient> patientDAOById = patientDao.findById(dto.getPatientId());
        if (patientDAOById.isEmpty()) return false;

        Patient patient = patientDAOById.get();

        Optional<TherapyProgram> therapyProgramDAOById = therapyProgramDAO.findById(dto.getProgramId());
        if (therapyProgramDAOById.isEmpty()) return false;

        TherapyProgram therapyProgram = therapyProgramDAOById.get();

        Optional<Therapist> therapistDAOById = therapistDao.findById(dto.getTherapistId());
        if (therapistDAOById.isEmpty()) return false;

        Therapist therapist = therapistDAOById.get();

        TherapySession therapySession = new TherapySession(
                dto.getSessionId(),
                dto.getSessionDate(),
                patient,
                therapist,
                therapyProgram
        );

        return sessionDao.save(therapySession);
    }

    @Override
    public boolean updateSession(SessionDto dto) {
        Optional<Patient> patientDAOById = patientDao.findById(dto.getPatientId());
        if (patientDAOById.isEmpty()) return false;

        Patient patient = patientDAOById.get();

        Optional<TherapyProgram> therapyProgramDAOById = therapyProgramDAO.findById(dto.getProgramId());
        if (therapyProgramDAOById.isEmpty()) return false;

        TherapyProgram therapyProgram = therapyProgramDAOById.get();

        Optional<Therapist> therapistDAOById = therapistDao.findById(dto.getTherapistId());
        if (therapistDAOById.isEmpty()) return false;

        Therapist therapist = therapistDAOById.get();

        TherapySession therapySession = new TherapySession(
                dto.getSessionId(),
                dto.getSessionDate(),
                patient,
                therapist,
                therapyProgram
        );

        return sessionDao.update(therapySession);
    }

    @Override
    public boolean deleteSession(String id) throws Exception {
        return sessionDao.deleteByPK(id);
    }

    @Override
    public String getNextSessionID() throws SQLException, IOException {
        Optional<String> lastId = sessionDao.getNextId();

        if (lastId.isPresent()) {
            String lastID = lastId.get();
            int numericPart = Integer.parseInt(lastID.substring(2));
            numericPart++;
            return String.format("TS%03d", numericPart);
        } else {
            return "TS001";
        }
    }

    @Override
    public List<SessionDto> getSessions() {
        List<TherapySession> therapySessions = sessionDao.getAll();
        List<SessionDto> therapySessionDTOs = new ArrayList<>();

        for (TherapySession therapySession : therapySessions) {
            SessionDto therapySessionDTO = new SessionDto();
            therapySessionDTO.setSessionId(therapySession.getId());
            therapySessionDTO.setSessionDate(therapySession.getDate());
            therapySessionDTO.setPatientId(therapySession.getPatient().getId());
            therapySessionDTO.setProgramId(therapySession.getProgram().getId());
            therapySessionDTO.setTherapistId(therapySession.getTherapist().getId());
            therapySessionDTOs.add(therapySessionDTO);
        }
        return therapySessionDTOs;
    }

    @Override
    public List<PatientDto> getPatients() {
        List<Patient> patients = patientDao.getAll();
        List<PatientDto> patientDTOS = new ArrayList<>();

        for (Patient patient : patients) {
            PatientDto patientDTO = new PatientDto();
            patientDTO.setId(patient.getId());
            patientDTO.setName(patient.getName());
            patientDTO.setGender(patient.getGender());
            patientDTO.setContact(patient.getContact());
            patientDTO.setMedicalHistory(patient.getMedicalHistory());
            patientDTOS.add(patientDTO);
        }
        return patientDTOS;
    }

    @Override
    public List<ProgramDto> getPrograms() {
        List<TherapyProgram> therapyPrograms = therapyProgramDAO.getAll();
        List<ProgramDto> therapyProgramDTOS = new ArrayList<>();

        for (TherapyProgram program : therapyPrograms) {
            ProgramDto dto = new ProgramDto();
            dto.setId(program.getId());
            dto.setName(program.getName());
            dto.setFee(program.getFee());
            dto.setDuration(program.getDuration());
            dto.setTherapistId(program.getTherapist().getId());
            therapyProgramDTOS.add(dto);
        }
        return therapyProgramDTOS;
    }

    @Override
    public SessionDto getSession(String id) {
        Optional<TherapySession> therapySession = sessionDao.findById(id);
        if (therapySession.isPresent()) {
            TherapySession session = therapySession.get();
            SessionDto therapySessionDTO = new SessionDto();
            therapySessionDTO.setSessionId(session.getId());
            therapySessionDTO.setSessionDate(session.getDate());
            therapySessionDTO.setPatientId(session.getPatient().getId());
            therapySessionDTO.setProgramId(session.getProgram().getId());
            therapySessionDTO.setTherapistId(session.getTherapist().getId());

            return therapySessionDTO;
        }
        return null;
    }

    @Override
    public PatientDto getPatient(String id) {
        Optional<Patient> patient = patientDao.findById(id);
        if (patient.isPresent()) {
            Patient p = patient.get();
            PatientDto patientDTO = new PatientDto();
            patientDTO.setId(p.getId());
            patientDTO.setName(p.getName());
            patientDTO.setGender(p.getGender());
            patientDTO.setContact(p.getContact());
            patientDTO.setMedicalHistory(p.getMedicalHistory());
            return patientDTO;
        }
        return null;
    }

    @Override
    public ProgramDto getProgram(String id) {
        Optional<TherapyProgram> therapyProgram = therapyProgramDAO.findById(id);
        if (therapyProgram.isPresent()) {
            TherapyProgram program = therapyProgram.get();
            ProgramDto therapyProgramDTO = new ProgramDto();
            therapyProgramDTO.setId(program.getId());
            therapyProgramDTO.setName(program.getName());
            therapyProgramDTO.setDuration(program.getDuration());
            therapyProgramDTO.setFee(program.getFee());
            therapyProgramDTO.setTherapistId(program.getTherapist().getId());

            return therapyProgramDTO;
        }
        return null;
    }

    @Override
    public TherapistDto getTherapist(String id) {
        Optional<Therapist> therapist = therapistDao.findById(id);
        if (therapist.isPresent()) {
            Therapist th = therapist.get();
            TherapistDto therapistDTO = new TherapistDto();
            therapistDTO.setId(th.getId());
            therapistDTO.setName(th.getName());
            therapistDTO.setSpecialization(th.getSpecialization());
            therapistDTO.setContact(th.getContact());

            return therapistDTO;
        }
        return null;
    }

    @Override
    public List<SessionDto> getSessionsFromTherapist(String id) {
        List<TherapySession> sessions = sessionDao.findSessionsByTherapistId(id);
        List<SessionDto> therapySessionDTOS = new ArrayList<>();

        for (TherapySession s : sessions) {
            SessionDto therapySessionDTO = new SessionDto();
            therapySessionDTO.setSessionId(s.getId());
            therapySessionDTO.setSessionDate(s.getDate());
            therapySessionDTO.setPatientId(s.getPatient().getId());
            therapySessionDTO.setTherapistId(s.getTherapist().getId());
            therapySessionDTO.setProgramId(s.getProgram().getId());
            therapySessionDTOS.add(therapySessionDTO);
        }
        return therapySessionDTOS;
    }
}

