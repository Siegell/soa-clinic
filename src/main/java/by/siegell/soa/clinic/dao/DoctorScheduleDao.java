package by.siegell.soa.clinic.dao;

import by.siegell.soa.clinic.domain.DoctorSchedule;

import java.time.LocalDate;

public interface DoctorScheduleDao extends Dao<DoctorSchedule> {
    DoctorSchedule findByDoctorIdAndDate(Long doctorId, LocalDate date);
}
