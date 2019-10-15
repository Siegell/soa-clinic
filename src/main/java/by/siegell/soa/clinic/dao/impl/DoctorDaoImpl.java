package by.siegell.soa.clinic.dao.impl;

import by.siegell.soa.clinic.dao.DoctorDao;
import by.siegell.soa.clinic.db.ConnectionFactory;
import by.siegell.soa.clinic.domain.Doctor;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao {

    @Override
    public void save(Doctor entity) {
        String sql;
        boolean update = false;
        if (entity.getId() != null && findById(entity.getId()) != null) {
            sql = "UPDATE doctor SET "
                    + "firstName = ?, middleName= ?, lastName= ?, specialization= ?,  district= ?, cabinet= ?, createdAt= ?, updatedAt = ? "
                    + "WHERE id=? ";
            update = true;
        } else {
            sql = "INSERT INTO doctor "
                    + "(firstName, middleName, lastName, specialization,  district, cabinet, createdAt, updatedAt) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?, ?, ?, ?)";
        }
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = ConnectionFactory.getConnection();
            s = c.prepareStatement(sql);
            s.setString(1, entity.getFirstName());
            s.setString(2, entity.getMiddleName());
            s.setString(3, entity.getLastName());
            s.setString(4, entity.getSpecialization());
            s.setString(5, entity.getDistrict());
            s.setString(6, entity.getCabinet());
            Timestamp now = Timestamp.valueOf(LocalDateTime.now());
            if (update) {
                s.setTimestamp(7, entity.getCreatedAt());
                s.setLong(9, entity.getId());
            } else {
                s.setTimestamp(7, now);
            }
            s.setTimestamp(8, now);
            s.executeUpdate();
            c.commit();
        } catch (Exception e) {
            try {
                c.rollback();
            } catch (SQLException ignored) {
            }
            e.printStackTrace();
        } finally {
            try {
                s.close();
            } catch (NullPointerException | SQLException ignored) {
            }
            try {
                c.close();
            } catch (NullPointerException | SQLException ignored) {
            }
        }
    }

    @Override
    public Doctor findById(Long id) {
        String sql = "select id, firstName, middleName, lastName, specialization,  district, cabinet, createdAt, updatedAt "
                + "FROM doctor "
                + "WHERE id = ?";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        Doctor doctor = null;
        try {
            c = ConnectionFactory.getConnection();
            s = c.prepareStatement(sql);
            s.setLong(1, id);
            r = s.executeQuery();
            if (r.next()) {
                doctor = Doctor.builder()
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                r.close();

            } catch (NullPointerException | SQLException ignored) {
            }
            try {
                s.close();
            } catch (NullPointerException | SQLException ignored) {
            }
            try {
                c.close();
            } catch (NullPointerException | SQLException ignored) {
            }
        }
        return doctor;
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM doctor "
                + "WHERE id = ?";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = ConnectionFactory.getConnection();
            s = c.prepareStatement(sql);
            s.setLong(1, id);
            s.executeUpdate();
            c.commit();
        } catch (Exception e) {
            try {
                c.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                s.close();
            } catch (NullPointerException | SQLException ignored) {
            }
            try {
                c.close();
            } catch (NullPointerException | SQLException ignored) {
            }
        }
    }

    @Override
    public List<Doctor> findAll() {
        String sql = "select id, firstName, middleName, lastName, specialization,  district, cabinet, createdAt, updatedAt "
                + "FROM doctor";
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
            } catch (NullPointerException | SQLException ignored) {
            }
            try {
                s.close();
            } catch (NullPointerException | SQLException ignored) {
            }
            try {
                c.close();
            } catch (NullPointerException | SQLException ignored) {
            }
        }
        return doctors;
    }

}
