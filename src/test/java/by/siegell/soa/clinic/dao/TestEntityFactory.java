package by.siegell.soa.clinic.dao;

import by.siegell.soa.clinic.domain.Appointment;
import by.siegell.soa.clinic.domain.Doctor;
import by.siegell.soa.clinic.domain.DoctorSchedule;

import java.time.LocalDate;
import java.time.LocalTime;

public class TestEntityFactory {
    public static Doctor getTestDoctor() {
        return Doctor.builder()
                .firstName("test")
                .lastName("test")
                .middleName("test")
                .specialization("test")
                .district("test")
                .cabinet("test")
                .build();
    }

    public static DoctorSchedule getTestDoctorSchedule() {
        return DoctorSchedule.builder()
                .date(LocalDate.now())
                .startWork(LocalTime.MAX)
                .endWork(LocalTime.MAX)
                .maxAppointmentCount(Integer.MAX_VALUE)
                .build();
    }

    public static Appointment getAppointment() {
        return Appointment.builder()
                .startTime(LocalTime.MIN)
                .endTime(LocalTime.MIN)
                .firstName("test")
                .lastName("test")
                .middleName("test")
                .build();
    }
}
