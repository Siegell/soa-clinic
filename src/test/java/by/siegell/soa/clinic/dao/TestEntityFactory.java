package by.siegell.soa.clinic.dao;

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
                .startWork(LocalTime.now())
                .endWork(LocalTime.now())
                .maxAppointmentCount(Integer.MAX_VALUE)
                .build();
    }
}
