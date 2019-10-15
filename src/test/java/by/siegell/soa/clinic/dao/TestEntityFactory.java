package by.siegell.soa.clinic.dao;

import by.siegell.soa.clinic.domain.Doctor;

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
}
