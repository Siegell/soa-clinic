package by.siegell.soa.clinic.dao;

import by.siegell.soa.clinic.dao.impl.DoctorScheduleDaoImpl;

public class DoctorScheduleFindByIdTest {
    public static void main(String[] args) {
        DoctorScheduleDao doctorScheduleDao = new DoctorScheduleDaoImpl();
        System.out.println(doctorScheduleDao.findById(1L));
    }
}
