package by.siegell.soa.clinic.dao;

import by.siegell.soa.clinic.dao.impl.AppointmentDaoImpl;

public class AppointmentFindByIdTest {
    public static void main(String[] args) {
        AppointmentDao appointmentDao = new AppointmentDaoImpl();
        System.out.println(appointmentDao.findById(1L));
    }
}
