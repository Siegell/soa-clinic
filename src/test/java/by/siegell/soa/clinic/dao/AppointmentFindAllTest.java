package by.siegell.soa.clinic.dao;

import by.siegell.soa.clinic.dao.impl.AppointmentDaoImpl;

public class AppointmentFindAllTest {
    public static void main(String[] args) {
        AppointmentDao appointmentDao = new AppointmentDaoImpl();
        appointmentDao.findAll().forEach(System.out::println);
    }
}
