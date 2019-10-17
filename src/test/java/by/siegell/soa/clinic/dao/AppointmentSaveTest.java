package by.siegell.soa.clinic.dao;

import by.siegell.soa.clinic.dao.impl.AppointmentDaoImpl;
import by.siegell.soa.clinic.dao.impl.DoctorDaoImpl;
import by.siegell.soa.clinic.dao.impl.DoctorScheduleDaoImpl;
import by.siegell.soa.clinic.domain.Appointment;
import by.siegell.soa.clinic.domain.Doctor;
import by.siegell.soa.clinic.domain.DoctorSchedule;
import by.siegell.soa.clinic.pool.ConnectionPool;
import by.siegell.soa.clinic.pool.PoolException;

import java.sql.Connection;
import java.sql.SQLException;

public class AppointmentSaveTest {
    public static void main(String[] args) throws PoolException, SQLException {
        ConnectionPool.getInstance().init();
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            AppointmentDaoImpl appointmentDao = new AppointmentDaoImpl();
            DoctorScheduleDaoImpl doctorScheduleDao = new DoctorScheduleDaoImpl();
            DoctorDaoImpl doctorDao = new DoctorDaoImpl();
            appointmentDao.setConnection(connection);
            doctorScheduleDao.setConnection(connection);
            doctorDao.setConnection(connection);

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
        ConnectionPool.getInstance().destroy();
    }
}
