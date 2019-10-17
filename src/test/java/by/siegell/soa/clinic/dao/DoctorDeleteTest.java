package by.siegell.soa.clinic.dao;

import by.siegell.soa.clinic.dao.impl.DoctorDaoImpl;
import by.siegell.soa.clinic.domain.Doctor;
import by.siegell.soa.clinic.pool.ConnectionPool;
import by.siegell.soa.clinic.pool.PoolException;

import java.sql.Connection;
import java.sql.SQLException;

public class DoctorDeleteTest {
    public static void main(String[] args) throws PoolException, SQLException {
        ConnectionPool.getInstance().init();
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            DoctorDaoImpl doctorDao = new DoctorDaoImpl();
            doctorDao.setConnection(connection);
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
        ConnectionPool.getInstance().destroy();
    }
}
