package by.siegell.soa.clinic.dao;

import by.siegell.soa.clinic.dao.impl.DoctorDaoImpl;

public class DoctorFindByIdTest {
    public static void main(String[] args) {
        DoctorDao doctorDao = new DoctorDaoImpl();
        System.out.println(doctorDao.findById(1L));
    }
}
