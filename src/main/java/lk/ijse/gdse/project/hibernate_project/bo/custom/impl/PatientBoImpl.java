package lk.ijse.gdse.project.hibernate_project.bo.custom.impl;

import lk.ijse.gdse.project.hibernate_project.Dao.DaoFactory;
import lk.ijse.gdse.project.hibernate_project.Dao.custome.PatientDao;
import lk.ijse.gdse.project.hibernate_project.Dto.PatientDto;
import lk.ijse.gdse.project.hibernate_project.Entity.Patient;
import lk.ijse.gdse.project.hibernate_project.bo.custom.PatientBo;

import java.io.IOException;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientBoImpl implements PatientBo {

    PatientDao patientDao = (PatientDao) DaoFactory.getInstance().getDao(DaoFactory.daoType.PATIENT);

    @Override
    public Optional<String> getNextId() throws SQLException, IOException {
        return patientDao.getNextId();
    }

    @Override
    public boolean save(PatientDto patientDto) {
        return patientDao.save(new Patient(patientDto.getId(),
                                           patientDto.getName(),
                                           patientDto.getGender(),
                                           patientDto.getDateOfBirth(),
                                           patientDto.getMedicalHistory(),
                                           patientDto.getAddress(),
                                           patientDto.getContact()
                                           ));
    }

    @Override
    public boolean update(PatientDto patientDto) {
        return patientDao.update(new Patient(patientDto.getId(),
                patientDto.getName(),
                patientDto.getGender(),
                patientDto.getDateOfBirth(),
                patientDto.getMedicalHistory(),
                patientDto.getAddress(),
                patientDto.getContact()
        ));
    }

    @Override
    public boolean deleteByPK(String pk) throws Exception {
        return patientDao.deleteByPK(pk);
    }

    @Override
    public List<PatientDto> getAll() throws SQLException, IOException {
        List<Patient>patients = patientDao.getAll();
        List<PatientDto> patientDtos = new ArrayList<>();
        for(Patient patient:patients){
            PatientDto patientDto = new PatientDto();
            patientDto.setId(patient.getId());
            patientDto.setName(patient.getName());
            patientDto.setGender(patient.getGender());
            patientDto.setDateOfBirth(patient.getDateOfBirth());
            patientDto.setMedicalHistory(patient.getMedicalHistory());
            patientDto.setAddress(patient.getAddress());
            patientDto.setContact(patient.getContact());
            patientDtos.add(patientDto);
        }
        return patientDtos;
    }


}
