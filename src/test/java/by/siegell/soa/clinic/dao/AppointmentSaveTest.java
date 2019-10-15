package by.siegell.soa.clinic.dao;

import by.siegell.soa.clinic.dao.impl.AppointmentDaoImpl;
import by.siegell.soa.clinic.dao.impl.DoctorDaoImpl;
import by.siegell.soa.clinic.dao.impl.DoctorScheduleDaoImpl;
import by.siegell.soa.clinic.domain.Appointment;
import by.siegell.soa.clinic.domain.Doctor;
import by.siegell.soa.clinic.domain.DoctorSchedule;

public class AppointmentSaveTest {
    public static void main(String[] args) {
        AppointmentDao appointmentDao = new AppointmentDaoImpl();
        DoctorScheduleDao doctorScheduleDao = new DoctorScheduleDaoImpl();
        DoctorDao doctorDao = new DoctorDaoImpl();

        Doctor doctor = TestEntityFactory.getTestDoctor();
        doctorDao.save(doctor);
        doctor = doctorDao.findBySubEntity(doctor).get();

        DoctorSchedule doctorSchedule = TestEntityFactory.getTestDoctorSchedule();
        doctorSchedule.setDoctorId(doctor.getId());
        doctorScheduleDao.save(doctorSchedule);
        doctorSchedule = doctorScheduleDao.findBySubEntity(doctorSchedule).get();

        Appointment appointment = TestEntityFactory.getAppointment();
        appointment.setDoctorScheduleId(doctorSchedule.getId());

        appointmentDao.findAll().forEach(System.out::println);
        System.out.println("++++++++++++++++++++++++++++++++++++");
        System.out.println(appointment);
        System.out.println("===================================");
        appointmentDao.save(appointment);
        appointmentDao.findAll().forEach(System.out::println);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        appointment = appointmentDao.findBySubEntity(appointment).get();
        appointment.setFirstName("updated");
        System.out.println(appointment);
        System.out.println("===================================");
        appointmentDao.save(appointment);
        appointmentDao.findAll().forEach(System.out::println);


        appointmentDao.delete(appointment.getId());
        doctorScheduleDao.delete(doctorSchedule.getId());
        doctorDao.delete(doctor.getId());
    }
}
