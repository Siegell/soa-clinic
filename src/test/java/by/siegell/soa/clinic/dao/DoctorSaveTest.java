package by.siegell.soa.clinic.dao;

import by.siegell.soa.clinic.dao.impl.DoctorDaoImpl;
import by.siegell.soa.clinic.domain.Doctor;
import by.siegell.soa.clinic.pool.ConnectionPool;
import by.siegell.soa.clinic.pool.PoolException;

import java.sql.Connection;
import java.sql.SQLException;

public class DoctorSaveTest {
    public static void main(String[] args) throws PoolException, SQLException {
        ConnectionPool.getInstance().init();
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            DoctorDaoImpl doctorDao = new DoctorDaoImpl();
            doctorDao.setConnection(connection);
            doctorDao.findAll().forEach(System.out::println);

            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
            Doctor insertDoctor = TestEntityFactory.getTestDoctor();
            System.out.println(insertDoctor);
            doctorDao.save(insertDoctor);

            System.out.println("=================================================");
            doctorDao.findAll().forEach(System.out::println);

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            Doctor updateDoctor = doctorDao.findBySubEntity(insertDoctor).get();
            updateDoctor.setFirstName("updated");
            System.out.println(updateDoctor);
            doctorDao.save(updateDoctor);

            System.out.println("=================================================");
            doctorDao.findAll().forEach(System.out::println);

            doctorDao.delete(updateDoctor.getId());

        }
        ConnectionPool.getInstance().destroy();

    }
}
