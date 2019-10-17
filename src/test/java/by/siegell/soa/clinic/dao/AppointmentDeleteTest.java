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

public class AppointmentDeleteTest {
    public static void main(String[] args) throws PoolException, SQLException {
        ConnectionPool.getInstance().init();
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            AppointmentDaoImpl appointmentDao = new AppointmentDaoImpl();
            appointmentDao.setConnection(connection);
            DoctorScheduleDaoImpl doctorScheduleDao = new DoctorScheduleDaoImpl();
            doctorScheduleDao.setConnection(connection);
            DoctorDaoImpl doctorDao = new DoctorDaoImpl();
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

            appointmentDao.save(appointment);
            appointment = appointmentDao.findBySubEntity(appointment).get();

            appointmentDao.findAll().forEach(System.out::println);
            System.out.println("---------------------------------");
            System.out.println(appointment);
            System.out.println("=================================");


            appointmentDao.delete(appointment.getId());

            appointmentDao.findAll().forEach(System.out::println);

            doctorScheduleDao.delete(doctorSchedule.getId());
            doctorDao.delete(doctor.getId());
        }
        ConnectionPool.getInstance().destroy();

    }
}
