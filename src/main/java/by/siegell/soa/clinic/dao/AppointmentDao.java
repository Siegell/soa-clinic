package by.siegell.soa.clinic.dao;

import by.siegell.soa.clinic.domain.Appointment;

import java.util.List;

public interface AppointmentDao extends Dao<Appointment> {
    List<Appointment> findByDoctorScheduleId(Long doctorScheduleId);
}
