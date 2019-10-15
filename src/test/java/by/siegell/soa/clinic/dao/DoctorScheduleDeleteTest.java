package by.siegell.soa.clinic.dao;

import by.siegell.soa.clinic.dao.impl.DoctorDaoImpl;
import by.siegell.soa.clinic.dao.impl.DoctorScheduleDaoImpl;
import by.siegell.soa.clinic.domain.Doctor;
import by.siegell.soa.clinic.domain.DoctorSchedule;

public class DoctorScheduleDeleteTest {
    public static void main(String[] args) {
        DoctorScheduleDao doctorScheduleDao = new DoctorScheduleDaoImpl();
        DoctorDao doctorDao = new DoctorDaoImpl();
        Doctor doctor = TestEntityFactory.getTestDoctor();
        doctorDao.save(doctor);
        doctor = doctorDao.findBySubEntity(doctor).get();

        DoctorSchedule doctorSchedule = TestEntityFactory.getTestDoctorSchedule();
        doctorSchedule.setDoctorId(doctor.getId());
        doctorScheduleDao.save(doctorSchedule);
        doctorSchedule = doctorScheduleDao.findBySubEntity(doctorSchedule).get();

        doctorScheduleDao.findAll().forEach(System.out::println);
        System.out.println("---------------------------------");
        System.out.println(doctorSchedule);
        System.out.println("=================================");
        doctorScheduleDao.delete(doctorSchedule.getId());
        doctorScheduleDao.findAll().forEach(System.out::println);

        doctorDao.delete(doctor.getId());
    }
}
