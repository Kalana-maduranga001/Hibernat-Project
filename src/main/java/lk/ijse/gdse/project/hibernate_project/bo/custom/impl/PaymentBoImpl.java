package lk.ijse.gdse.project.hibernate_project.bo.custom.impl;

import lk.ijse.gdse.project.hibernate_project.Dao.DaoFactory;
import lk.ijse.gdse.project.hibernate_project.Dao.custome.PaymentDao;
import lk.ijse.gdse.project.hibernate_project.Dto.PatientDto;
import lk.ijse.gdse.project.hibernate_project.Dto.PaymentDto;
import lk.ijse.gdse.project.hibernate_project.Entity.Patient;
import lk.ijse.gdse.project.hibernate_project.Entity.Payment;
import lk.ijse.gdse.project.hibernate_project.bo.custom.PaymentBo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentBoImpl implements PaymentBo {

    PaymentDao paymentDao = (PaymentDao) DaoFactory.getInstance().getDao(DaoFactory.daoType.PAYMENT);

    @Override
    public Optional<String> getNextId() throws SQLException, IOException {
        return paymentDao.getNextId();
    }

    @Override
    public boolean save(PaymentDto paymentDto) {
        return paymentDao.save(new Payment(
                paymentDto.getId(),
                paymentDto.getPatientId(),
                paymentDto.getProgramId(),
                paymentDto.getDate(),
                paymentDto.getStatus(),
                paymentDto.getAmount()
        ));
    }

    @Override
    public boolean update(PaymentDto paymentDto) {
        return paymentDao.update(new Payment(
                paymentDto.getId(),
                paymentDto.getPatientId(),
                paymentDto.getProgramId(),
                paymentDto.getDate(),
                paymentDto.getStatus(),
                paymentDto.getAmount()
        ));
    }

    @Override
    public boolean deleteByPK(String pk) throws Exception {
        return paymentDao.deleteByPK(pk);
    }

    @Override
    public List<PaymentDto> getAll() throws SQLException, IOException {
        List<Payment> payments = paymentDao.getAll();
        List<PaymentDto> paymentDtos = new ArrayList<>();
        for(Payment patient:payments){

            PaymentDto paymentDto = new PaymentDto();
            paymentDto.setId(patient.getId());
            paymentDto.setPatientId(patient.getPatientId());
            paymentDto.setProgramId(patient.getProgramId());
            paymentDto.setDate(patient.getDate());
            paymentDto.setStatus(patient.getStatus());
            paymentDto.setAmount(patient.getAmount());
            paymentDtos.add(paymentDto);
        }
        return paymentDtos;
    }

    public String getPatientNameById(String patientId) {
        try {
            Patient patient = paymentDao.findBy(patientId);
            return (patient != null) ? patient.getName() : "Not Found";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

    @Override
    public List<String> getAllPaymentIds() throws SQLException, IOException {
        return paymentDao.getAllIds();
    }

}
