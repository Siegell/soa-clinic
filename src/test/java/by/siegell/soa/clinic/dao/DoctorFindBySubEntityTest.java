package by.siegell.soa.clinic.dao;

import by.siegell.soa.clinic.dao.impl.DoctorDaoImpl;
import by.siegell.soa.clinic.domain.Doctor;
import by.siegell.soa.clinic.pool.ConnectionPool;
import by.siegell.soa.clinic.pool.PoolException;

import java.sql.Connection;
import java.sql.SQLException;

public class DoctorFindBySubEntityTest {
    public static void main(String[] args) throws PoolException, SQLException {
        ConnectionPool.getInstance().init();
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            DoctorDaoImpl doctorDao = new DoctorDaoImpl();
            doctorDao.setConnection(connection);
            Doctor subEnt = TestEntityFactory.getTestDoctor();
            doctorDao.save(subEnt);

            System.out.println(subEnt);
            System.out.println("???????????????????????????????????");
            doctorDao.findAll().forEach(System.out::println);
            System.out.println("===================================");

            Doctor ent = doctorDao.findBySubEntity(subEnt).get();
            System.out.println(ent);

            doctorDao.delete(ent.getId());
        }
        ConnectionPool.getInstance().destroy();


    }
}
