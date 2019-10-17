package by.siegell.soa.clinic.dao.impl;

import by.siegell.soa.clinic.dao.DoctorDao;
import by.siegell.soa.clinic.domain.Doctor;
import by.siegell.soa.clinic.imap.IdentityMap;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class DoctorDaoImpl extends BaseDaoMySqlImpl implements DoctorDao {

    private IdentityMap<Doctor, Long> imap = new IdentityMap<>();

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
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setString(1, entity.getFirstName());
            s.setString(2, entity.getMiddleName());
            s.setString(3, entity.getLastName());
            s.setString(4, entity.getSpecialization());
            s.setString(5, entity.getDistrict());
            s.setString(6, entity.getCabinet());
            Timestamp now = Timestamp.valueOf(LocalDateTime.now());
            s.setTimestamp(8, now);
            if (update) {
                s.setTimestamp(7, entity.getCreatedAt());
                s.setLong(9, entity.getId());

                imap.putObject(entity.getId(), entity);

            } else {
                s.setTimestamp(7, now);
            }
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
        Doctor doctor = imap.getObject(id);
        if (doctor != null) {
            return doctor;
        }


        String sql = "select id, firstName, middleName, lastName, specialization,  district, cabinet, createdAt, updatedAt "
                + "FROM doctor "
                + "WHERE id = ?";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
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

                imap.putObject(doctor.getId(), doctor);

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
        imap.deleteObject(id);

        String sql = "DELETE FROM doctor "
                + "WHERE id = ?";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
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
            c = getConnection();
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
                imap.putObject(doctor.getId(), doctor);
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

    @Override
    public Optional<Doctor> findBySubEntity(Doctor entity) {
        String sql = "select id, firstName, middleName, lastName, specialization,  district, cabinet, createdAt, updatedAt "
                + "FROM doctor "
                + "WHERE firstname = ? AND middlename = ? AND lastname = ? AND specialization = ? AND cabinet = ?";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        Doctor doctor = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setString(1, entity.getFirstName());
            s.setString(2, entity.getMiddleName());
            s.setString(3, entity.getLastName());
            s.setString(4, entity.getSpecialization());
            s.setString(5, entity.getCabinet());
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
        return Optional.of(doctor);
    }
}
