package by.siegell.soa.clinic.dao;

import by.siegell.soa.clinic.dao.impl.DoctorDaoImpl;
import by.siegell.soa.clinic.dao.impl.DoctorScheduleDaoImpl;
import by.siegell.soa.clinic.domain.Doctor;
import by.siegell.soa.clinic.domain.DoctorSchedule;
import by.siegell.soa.clinic.pool.ConnectionPool;
import by.siegell.soa.clinic.pool.PoolException;

import java.sql.Connection;
import java.sql.SQLException;

public class DoctorScheduleFindBySubEntityTest {
    public static void main(String[] args) throws PoolException, SQLException {
        ConnectionPool.getInstance().init();
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            DoctorScheduleDaoImpl doctorScheduleDao = new DoctorScheduleDaoImpl();
            DoctorDaoImpl doctorDao = new DoctorDaoImpl();
            doctorScheduleDao.setConnection(connection);
            doctorDao.setConnection(connection);
            Doctor doctor = TestEntityFactory.getTestDoctor();
            doctorDao.save(doctor);
            doctor = doctorDao.findBySubEntity(doctor).get();

            DoctorSchedule doctorSchedule = TestEntityFactory.getTestDoctorSchedule();
            doctorSchedule.setDoctorId(doctor.getId());
            doctorScheduleDao.save(doctorSchedule);

            doctorScheduleDao.findAll().forEach(System.out::println);
            System.out.println("???????????????????????????");
            System.out.println(doctorSchedule);
            System.out.println("===========================");

            doctorSchedule = doctorScheduleDao.findBySubEntity(doctorSchedule).get();
            System.out.println(doctorSchedule);

            doctorScheduleDao.delete(doctorSchedule.getId());
            doctorDao.delete(doctor.getId());

        }
        ConnectionPool.getInstance().destroy();

    }
}
