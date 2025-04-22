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

    private String id;
    private String name;
    private BigDecimal fee;
    private int duration;

    @Override
    public boolean save(ProgramDto programDto) {
        return programDao.save(new TherapyProgram(programDto.getId(),
                programDto.getName(),
                programDto.getFee(),
                programDto.getDuration()

        ));
    }

    @Override
    public boolean update(ProgramDto programDto) {
        return programDao.update(new TherapyProgram(
                programDto.getId(),
                programDto.getName(),
                programDto.getFee(),
                programDto.getDuration()

        ));
    }

    @Override
    public boolean deleteByPK(String pk) throws Exception {
        return programDao.deleteByPK(pk);
    }

    @Override
    public List<ProgramDto> getAll() throws SQLException, IOException {
        List<TherapyProgram>programs = programDao.getAll();
        List<ProgramDto> programDtos = new ArrayList<>();
        for(TherapyProgram patient:programs){

            ProgramDto programDto = new ProgramDto();
            programDto.setId(patient.getId());
            programDto.setName(patient.getName());
            programDto.setFee(patient.getFee());
            programDto.setDuration(patient.getDuration());

            programDtos.add(programDto);
        }
        return programDtos;
    }
}
