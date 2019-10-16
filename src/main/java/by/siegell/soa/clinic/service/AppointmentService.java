package by.siegell.soa.clinic.service;

import by.siegell.soa.clinic.domain.Appointment;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {
    List<Appointment> findAll();

    void save(Appointment appointment) throws ServiceException;

    void delete(Long id);

    Duration getDoctorAppointmentWorkTime(Long doctorId, LocalDate date);
}
