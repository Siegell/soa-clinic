package by.siegell.soa.clinic.dao;

import by.siegell.soa.clinic.dao.impl.AppointmentDaoImpl;
import by.siegell.soa.clinic.pool.ConnectionPool;
import by.siegell.soa.clinic.pool.PoolException;

import java.sql.Connection;
import java.sql.SQLException;

public class AppointmentFindByIdTest {
    public static void main(String[] args) throws PoolException, SQLException {
        ConnectionPool.getInstance().init();
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            AppointmentDaoImpl appointmentDao = new AppointmentDaoImpl();
            appointmentDao.setConnection(connection);
            System.out.println(appointmentDao.findById(1L));
        }
        ConnectionPool.getInstance().destroy();

    }
}
