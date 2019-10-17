package by.siegell.soa.clinic.dao.impl;

import by.siegell.soa.clinic.dao.AppointmentDao;
import by.siegell.soa.clinic.domain.Appointment;
import by.siegell.soa.clinic.imap.IdentityMap;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class AppointmentDaoImpl extends BaseDaoMySqlImpl implements AppointmentDao {

    private IdentityMap<Appointment, Long> imap = new IdentityMap<>();

    private static Appointment fillAppointment(ResultSet r) throws SQLException {
        Appointment appointment = Appointment.builder()
                .doctorScheduleId(r.getLong("doctorScheduleId"))
                .startTime(r.getTime("startTime").toLocalTime())
                .endTime(r.getTime("endTime").toLocalTime())
                .firstName(r.getString("firstName"))
                .middleName(r.getString("middleName"))
                .lastName(r.getString("lastName"))
                .build();
        appointment.setId(r.getLong("id"));
        appointment.setCreatedAt(r.getTimestamp("createdAt"));
        appointment.setUpdatedAt(r.getTimestamp("updatedAt"));
        return appointment;
    }

    @Override
    public void save(Appointment entity) {
        String sql;
        boolean update = false;
        if (entity.getId() != null && findById(entity.getId()) != null) {
            sql = "UPDATE appointment SET "
                    + "doctorScheduleId = ?, startTime= ?, endTime= ?, firstName = ?, middleName= ?, lastName= ?, createdAt= ?, updatedAt = ? "
                    + "WHERE id=? ";
            update = true;
        } else {
            sql = "INSERT INTO appointment "
                    + "(doctorScheduleId, startTime, endTime, firstName,  middleName, lastName, createdAt, updatedAt) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?, ?, ?, ?)";
        }
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setLong(1, entity.getDoctorScheduleId());
            s.setTime(2, Time.valueOf(entity.getStartTime()));
            s.setTime(3, Time.valueOf(entity.getEndTime()));
            s.setString(4, entity.getFirstName());
            s.setString(5, entity.getMiddleName());
            s.setString(6, entity.getLastName());
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
    public Appointment findById(Long id) {
        Appointment appointment = imap.getObject(id);
        if (appointment != null) {
            return appointment;
        }

        String sql = "select id, doctorScheduleId, startTime, endTime, firstName,  middleName, lastName, createdAt, updatedAt "
                + "FROM appointment "
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
                appointment = fillAppointment(r);
                imap.putObject(appointment.getId(), appointment);
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
        return appointment;
    }

    @Override
    public void delete(Long id) {
        imap.deleteObject(id);

        String sql = "DELETE FROM appointment "
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
    public List<Appointment> findAll() {
        String sql = "select id, doctorScheduleId, startTime, endTime, firstName,  middleName, lastName, createdAt, updatedAt "
                + "FROM appointment ";
        Connection c = null;
        Statement s = null;
        ResultSet r = null;
        List<Appointment> appointments = new LinkedList<>();
        try {
            c = getConnection();
            s = c.createStatement();
            r = s.executeQuery(sql);
            while (r.next()) {
                Appointment appointment = fillAppointment(r);
                appointments.add(appointment);
                imap.putObject(appointment.getId(), appointment);
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
        return appointments;
    }

    @Override
    public Optional<Appointment> findBySubEntity(Appointment entity) {
        String sql = "select id, doctorScheduleId, startTime, endTime, firstName,  middleName, lastName, createdAt, updatedAt "
                + "FROM appointment "
                + "WHERE doctorScheduleId = ? AND startTime = ? AND endtime = ? AND firstname = ? AND middlename = ? AND lastname = ?";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        Appointment appointment = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setLong(1, entity.getDoctorScheduleId());
            s.setTime(2, Time.valueOf(entity.getStartTime()));
            s.setTime(3, Time.valueOf(entity.getEndTime()));
            s.setString(4, entity.getFirstName());
            s.setString(5, entity.getMiddleName());
            s.setString(6, entity.getLastName());
            r = s.executeQuery();
            if (r.next()) {
                appointment = fillAppointment(r);
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
        return Optional.of(appointment);
    }

    @Override
    public List<Appointment> findByDoctorScheduleId(Long doctorScheduleId) {
        String sql = "select id, doctorScheduleId, startTime, endTime, firstName,  middleName, lastName, createdAt, updatedAt "
                + "FROM appointment "
                + "WHERE doctorScheduleId = ?";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        List<Appointment> appointments = new LinkedList<>();
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setLong(1, doctorScheduleId);
            r = s.executeQuery();
            while (r.next()) {
                Appointment appointment = fillAppointment(r);
                appointments.add(appointment);
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
        return appointments;
    }
}
