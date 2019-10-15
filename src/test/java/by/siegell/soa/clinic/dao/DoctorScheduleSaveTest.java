package by.siegell.soa.clinic.dao;

import by.siegell.soa.clinic.dao.impl.DoctorDaoImpl;
import by.siegell.soa.clinic.dao.impl.DoctorScheduleDaoImpl;
import by.siegell.soa.clinic.domain.Doctor;
import by.siegell.soa.clinic.domain.DoctorSchedule;

public class DoctorScheduleSaveTest {
    public static void main(String[] args) {
        DoctorScheduleDao doctorScheduleDao = new DoctorScheduleDaoImpl();
        DoctorDao doctorDao = new DoctorDaoImpl();
        Doctor doctor = TestEntityFactory.getTestDoctor();
        doctorDao.save(doctor);
        doctor = doctorDao.findBySubEntity(doctor).get();

        DoctorSchedule doctorSchedule = TestEntityFactory.getTestDoctorSchedule();
        doctorSchedule.setDoctorId(doctor.getId());

        doctorScheduleDao.findAll().forEach(System.out::println);
        System.out.println("+++++++++++++++++++++++++++");
        System.out.println(doctorSchedule);
        System.out.println("===========================");
        doctorScheduleDao.save(doctorSchedule);
        doctorScheduleDao.findAll().forEach(System.out::println);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        doctorSchedule = doctorScheduleDao.findBySubEntity(doctorSchedule).get();
        doctorSchedule.setMaxAppointmentCount(0);
        System.out.println(doctorSchedule);
        System.out.println("===========================");

        doctorScheduleDao.save(doctorSchedule);
        doctorScheduleDao.findAll().forEach(System.out::println);

        doctorScheduleDao.delete(doctorSchedule.getId());
        doctorDao.delete(doctor.getId());
    }
}
