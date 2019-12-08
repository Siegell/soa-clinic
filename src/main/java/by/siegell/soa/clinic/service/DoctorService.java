package by.siegell.soa.clinic.service;

import by.siegell.soa.clinic.domain.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> findAll();

    Doctor findById(Long id);

    void save(Doctor doctor);

    void delete(Long id);
}
