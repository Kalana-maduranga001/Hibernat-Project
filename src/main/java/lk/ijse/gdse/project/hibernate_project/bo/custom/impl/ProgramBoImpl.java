package lk.ijse.gdse.project.hibernate_project.bo.custom.impl;
import lk.ijse.gdse.project.hibernate_project.Dao.DaoFactory;
import lk.ijse.gdse.project.hibernate_project.Dao.custome.ProgramDao;
import lk.ijse.gdse.project.hibernate_project.Dto.ProgramDto;

import lk.ijse.gdse.project.hibernate_project.Entity.TherapyProgram;
import lk.ijse.gdse.project.hibernate_project.bo.custom.ProgramBo;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProgramBoImpl implements ProgramBo {
    ProgramDao programDao = (ProgramDao) DaoFactory.getInstance().getDao(DaoFactory.daoType.PROGRAMME);

    @Override
    public Optional<String> getNextId() throws SQLException, IOException {
        return programDao.getNextId();
    }

    @Override
    public boolean save(ProgramDto programDto) {
        return programDao.save(new TherapyProgram(
                programDto.getId(),
                programDto.getName(),
                programDto.getFee(),
                programDto.getDuration(),
                programDto.getTherapistId()
        ));
    }

    @Override
    public boolean update(ProgramDto programDto) {
        return programDao.update(new TherapyProgram(
                programDto.getId(),
                programDto.getName(),
                programDto.getFee(),
                programDto.getDuration(),
                programDto.getTherapistId()
        ));
    }

    @Override
    public boolean deleteByPK(String pk) throws Exception {
        return programDao.deleteByPK(pk);
    }

    @Override
    public List<ProgramDto> getAll() throws SQLException, IOException {
        List<TherapyProgram> entities = programDao.getAll();
        List<ProgramDto> dtos = new ArrayList<>();

        for (TherapyProgram program : entities) {
            dtos.add(new ProgramDto(
                    program.getId(),
                    program.getName(),
                    program.getFee(),
                    program.getDuration(),
                    program.getTherapistId() // <- must be this
            ));
        }

        return dtos;
    }

    @Override
    public List<String> getAllTherapistIds() throws SQLException, ClassNotFoundException {
        return programDao.getTherapistIds();
    }




}
