package by.siegell.soa.clinic.dao.impl;

import by.siegell.soa.clinic.dao.AppointmentDao;
import by.siegell.soa.clinic.db.ConnectionFactory;
import by.siegell.soa.clinic.domain.Appointment;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class AppointmentDaoImpl implements AppointmentDao {

    /*
    CREATE TABLE appointment
(
    id               SERIAL      NOT NULL,
    createdAt        TIMESTAMP   NOT NULL,
    updatedAt        TIMESTAMP   NOT NULL,
    doctorScheduleId INT         NOT NULL,
    startTime        TIME        NOT NULL,
    endTime          TIME        NOT NULL,
    firstName        varchar(50) NOT NULL,
    middleName       varchar(50) NOT NULL,
    lastName         varchar(50) NOT NULL,
    PRIMARY KEY (id)
);
     */

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
            c = ConnectionFactory.getConnection();
            s = c.prepareStatement(sql);
            s.setLong(1, entity.getDoctorScheduleId());
            s.setTime(2, Time.valueOf(entity.getStartTime()));
            s.setTime(3, Time.valueOf(entity.getEndTime()));
            s.setString(4, entity.getFirstName());
            s.setString(5, entity.getMiddleName());
            s.setString(6, entity.getLastName());
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
    public Appointment findById(Long id) {
        String sql = "select id, doctorScheduleId, startTime, endTime, firstName,  middleName, lastName, createdAt, updatedAt "
                + "FROM appointment "
                + "WHERE id = ?";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        Appointment appointment = null;
        try {
            c = ConnectionFactory.getConnection();
            s = c.prepareStatement(sql);
            s.setLong(1, id);
            r = s.executeQuery();
            if (r.next()) {
                appointment = Appointment.builder()
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
        String sql = "DELETE FROM appointment "
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
    public List<Appointment> findAll() {
        String sql = "select id, doctorScheduleId, startTime, endTime, firstName,  middleName, lastName, createdAt, updatedAt "
                + "FROM appointment ";
        Connection c = null;
        Statement s = null;
        ResultSet r = null;
        LinkedList<Appointment> appointments = new LinkedList<>();
        try {
            c = ConnectionFactory.getConnection();
            s = c.createStatement();
            r = s.executeQuery(sql);
            while (r.next()) {
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
