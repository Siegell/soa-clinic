package by.siegell.soa.clinic.dao;

import by.siegell.soa.clinic.dao.impl.DoctorDaoImpl;
import by.siegell.soa.clinic.dao.impl.DoctorScheduleDaoImpl;
import by.siegell.soa.clinic.domain.Doctor;
import by.siegell.soa.clinic.domain.DoctorSchedule;
import by.siegell.soa.clinic.pool.ConnectionPool;
import by.siegell.soa.clinic.pool.PoolException;

import java.sql.Connection;
import java.sql.SQLException;

public class DoctorScheduleDeleteTest {
    public static void main(String[] args) throws PoolException, SQLException {
        ConnectionPool.getInstance().init();
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            DoctorScheduleDaoImpl doctorScheduleDao = new DoctorScheduleDaoImpl();
            DoctorDaoImpl doctorDao = new DoctorDaoImpl();
            doctorDao.setConnection(connection);
            doctorScheduleDao.setConnection(connection);
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
        ConnectionPool.getInstance().destroy();

    }
}
