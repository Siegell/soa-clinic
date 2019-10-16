package by.siegell.soa.clinic.service.impl;

import by.siegell.soa.clinic.dao.DoctorScheduleDao;
import by.siegell.soa.clinic.domain.DoctorSchedule;
import by.siegell.soa.clinic.service.DoctorScheduleService;

import java.util.List;

public class DoctorScheduleServiceImpl implements DoctorScheduleService {

    DoctorScheduleDao doctorScheduleDao;

    public void setDoctorScheduleDao(DoctorScheduleDao doctorScheduleDao) {
        this.doctorScheduleDao = doctorScheduleDao;
    }

    @Override
    public List<DoctorSchedule> findAll() {
        return doctorScheduleDao.findAll();
    }

    @Override
    public void save(DoctorSchedule doctorSchedule) {
        doctorScheduleDao.save(doctorSchedule);
    }

    @Override
    public void delete(Long id) {

    }
}
