package by.siegell.soa.clinic.dao;

import by.siegell.soa.clinic.dao.impl.DoctorDaoImpl;
import by.siegell.soa.clinic.pool.ConnectionPool;
import by.siegell.soa.clinic.pool.PoolException;

import java.sql.Connection;
import java.sql.SQLException;

public class DoctorFindByIdTest {
    public static void main(String[] args) throws PoolException, SQLException {
        ConnectionPool.getInstance().init();
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            DoctorDaoImpl doctorDao = new DoctorDaoImpl();
            doctorDao.setConnection(connection);
            System.out.println(doctorDao.findById(1L));

        }
        ConnectionPool.getInstance().destroy();
    }
}
