package by.siegell.soa.clinic.service.impl;

import by.siegell.soa.clinic.dao.DoctorDao;
import by.siegell.soa.clinic.domain.Doctor;
import by.siegell.soa.clinic.service.DoctorService;

import java.util.List;

public class DoctorServiceImpl implements DoctorService {

    private DoctorDao doctorDao;

    public void setDoctorDao(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    @Override
    public List<Doctor> findAll() {
        return doctorDao.findAll();
    }

    @Override
    public void save(Doctor doctor) {

    }

    @Override
    public void delete(Long id) {

    }
}
