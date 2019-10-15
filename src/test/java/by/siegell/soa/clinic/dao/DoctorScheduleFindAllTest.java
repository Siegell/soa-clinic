package by.siegell.soa.clinic.dao;

import by.siegell.soa.clinic.dao.impl.DoctorScheduleDaoImpl;

public class DoctorScheduleFindAllTest {
    public static void main(String[] args) {
        DoctorScheduleDao doctorScheduleDao = new DoctorScheduleDaoImpl();
        doctorScheduleDao.findAll().forEach(System.out::println);
    }
}
