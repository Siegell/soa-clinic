package by.siegell.soa.clinic.dao.impl;

import by.siegell.soa.clinic.dao.DoctorDao;
import by.siegell.soa.clinic.db.ConnectionFactory;
import by.siegell.soa.clinic.domain.Doctor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao {
    @Override
    public Long create(Doctor entity) {
        return null;
    }

    @Override
    public Doctor read(Long id) {
        return null;
    }

    @Override
    public void update(Doctor entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Doctor> readAll() {
        String sql = "select `id`, `firstName`, `middleName`, `lastName`, `specialization`,  `district`, `cabinet`, `createdAt`, `updatedAt`"
                + "from `doctor`";
        Connection c = null;
        Statement s = null;
        ResultSet r = null;
        LinkedList<Doctor> doctors = new LinkedList<>();
        try {
            c = ConnectionFactory.getConnection();
            s = c.createStatement();
            r = s.executeQuery(sql);
            while (r.next()) {
                Doctor doctor = Doctor.builder()
                        .firstName(r.getString("firstName"))
                        .middleName(r.getString("middleName"))
                        .lastName(r.getString("lastName"))
                        .specialization(r.getString("specialization"))
                        .district(r.getString("district"))
                        .cabinet(r.getString("cabinet"))
                        .build();
                doctor.setId(r.getLong("id"));
                doctor.setCreatedAt(r.getTimestamp("createdAt"));
                doctor.setUpdatedAt(r.getTimestamp("updatedAt"));
                doctors.add(doctor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                r.close();
            } catch (NullPointerException | SQLException e) {
            }
            try {
                s.close();
            } catch (NullPointerException | SQLException e) {
            }
            try {
                c.close();
            } catch (NullPointerException | SQLException e) {
            }
        }
        return doctors;
    }
}
