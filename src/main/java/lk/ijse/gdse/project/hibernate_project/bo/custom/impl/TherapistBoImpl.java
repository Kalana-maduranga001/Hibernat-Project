package lk.ijse.gdse.project.hibernate_project.bo.custom.impl;

import lk.ijse.gdse.project.hibernate_project.Dao.DaoFactory;
import lk.ijse.gdse.project.hibernate_project.Dao.custome.TherapistDao;
import lk.ijse.gdse.project.hibernate_project.Dto.PatientDto;
import lk.ijse.gdse.project.hibernate_project.Dto.TherapistDto;
import lk.ijse.gdse.project.hibernate_project.Entity.Patient;
import lk.ijse.gdse.project.hibernate_project.Entity.Therapist;
import lk.ijse.gdse.project.hibernate_project.bo.custom.TherapistBo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TherapistBoImpl implements TherapistBo {
    TherapistDao therapistDao =(TherapistDao)  DaoFactory.getInstance().getDao(DaoFactory.daoType.THERAPIST);

    @Override
    public Optional<String> getNextId() throws SQLException, IOException {
        return therapistDao.getNextId();
    }

    @Override
    public boolean save(TherapistDto therapistDto) {
        return therapistDao.save(new Therapist(
                                therapistDto.getId(),
                                therapistDto.getName(),
                                therapistDto.getAge(),
                                therapistDto.getSpecialization(),
                                therapistDto.getContact()
        ));
    }

    @Override
    public boolean update(TherapistDto therapistDto) {
        return therapistDao.update(new Therapist(
                therapistDto.getId(),
                therapistDto.getName(),
                therapistDto.getAge(),
                therapistDto.getSpecialization(),
                therapistDto.getContact()
        ));
    }

    @Override
    public boolean deleteByPK(String pk) throws Exception {
        return therapistDao.deleteByPK(pk);
    }

    @Override
    public List<TherapistDto> getAll() throws SQLException, IOException {
        List<Therapist> therapists = therapistDao.getAll();
        List<TherapistDto> therapistDtos = new ArrayList<>();
        for(Therapist patient:therapists){

            TherapistDto therapistDto = new TherapistDto();

            therapistDto.setId(patient.getId());
            therapistDto.setName(patient.getName());
            therapistDto.setAge(patient.getAge());
            therapistDto.setSpecialization(patient.getSpecialization());
            therapistDto.setContact(patient.getContact());

            therapistDtos.add(therapistDto);
        }
        return therapistDtos;
    }
}
