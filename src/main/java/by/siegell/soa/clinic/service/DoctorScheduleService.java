package by.siegell.soa.clinic.service;

import by.siegell.soa.clinic.domain.DoctorSchedule;

import java.util.List;

public interface DoctorScheduleService {
    List<DoctorSchedule> findAll();

    void save(DoctorSchedule doctorSchedule);

    void delete(Long id);
}
