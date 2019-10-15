package by.siegell.soa.clinic.dao;

import by.siegell.soa.clinic.dao.impl.DoctorDaoImpl;
import by.siegell.soa.clinic.domain.Doctor;

public class DoctorDeleteTest {
    public static void main(String[] args) {
        DoctorDao doctorDao = new DoctorDaoImpl();
        Doctor doctor = TestEntityFactory.getTestDoctor();
        doctorDao.save(doctor);
        Doctor deleteDoctor = doctorDao.findBySubEntity(doctor).get();

        doctorDao.findAll().forEach(System.out::println);
        System.out.println("---------------------");
        System.out.println(deleteDoctor);

        System.out.println("=====================");
        doctorDao.delete(deleteDoctor.getId());
        doctorDao.findAll().forEach(System.out::println);
    }
}
