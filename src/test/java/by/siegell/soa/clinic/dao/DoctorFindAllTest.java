package by.siegell.soa.clinic.dao;

import by.siegell.soa.clinic.dao.impl.DoctorDaoImpl;

public class DoctorFindAllTest {
    public static void main(String[] args) {
        DoctorDao doctorDao = new DoctorDaoImpl();
        doctorDao.findAll().forEach(System.out::println);
    }
}
