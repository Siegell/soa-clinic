package by.siegell.soa.clinic.dao;

import by.siegell.soa.clinic.dao.impl.AppointmentDaoImpl;
import by.siegell.soa.clinic.pool.ConnectionPool;
import by.siegell.soa.clinic.pool.PoolException;

import java.sql.Connection;
import java.sql.SQLException;

public class AppointmentFindAllTest {
    public static void main(String[] args) throws PoolException, SQLException {
        ConnectionPool.getInstance().init();
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            AppointmentDaoImpl appointmentDao = new AppointmentDaoImpl();
            appointmentDao.setConnection(connection);
            appointmentDao.findAll().forEach(System.out::println);
        }
        ConnectionPool.getInstance().destroy();

    }
}
