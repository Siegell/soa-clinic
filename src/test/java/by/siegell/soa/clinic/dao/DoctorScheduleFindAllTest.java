package by.siegell.soa.clinic.dao;

import by.siegell.soa.clinic.dao.impl.DoctorScheduleDaoImpl;
import by.siegell.soa.clinic.pool.ConnectionPool;
import by.siegell.soa.clinic.pool.PoolException;

import java.sql.Connection;
import java.sql.SQLException;

public class DoctorScheduleFindAllTest {
    public static void main(String[] args) throws PoolException, SQLException {
        ConnectionPool.getInstance().init();
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            DoctorScheduleDaoImpl doctorScheduleDao = new DoctorScheduleDaoImpl();
            doctorScheduleDao.setConnection(connection);
            doctorScheduleDao.findAll().forEach(System.out::println);

        }
        ConnectionPool.getInstance().destroy();

    }
}
