package by.siegell.soa.clinic.dao;

import by.siegell.soa.clinic.dao.impl.DoctorDaoImpl;
import by.siegell.soa.clinic.domain.Doctor;

public class DoctorFindBySubEntityTest {
    public static void main(String[] args) {
        DoctorDao doctorDao = new DoctorDaoImpl();
        Doctor subEnt = TestEntityFactory.getTestDoctor();
        doctorDao.save(subEnt);

        System.out.println(subEnt);
        System.out.println("???????????????????????????????????");
        doctorDao.findAll().forEach(System.out::println);
        System.out.println("===================================");

        Doctor ent = doctorDao.findBySubEntity(subEnt).get();
        System.out.println(ent);

        doctorDao.delete(ent.getId());
    }
}
