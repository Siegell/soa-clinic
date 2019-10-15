package by.siegell.soa.clinic.dao.impl;

import by.siegell.soa.clinic.dao.DoctorScheduleDao;
import by.siegell.soa.clinic.db.ConnectionFactory;
import by.siegell.soa.clinic.domain.DoctorSchedule;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class DoctorScheduleDaoImpl implements DoctorScheduleDao {

    @Override
    public void save(DoctorSchedule entity) {
        String sql;
        boolean update = false;
        if (entity.getId() != null && findById(entity.getId()) != null) {
            sql = "UPDATE doctor_schedule SET "
                    + "date = ?, doctorId= ?, startWork= ?, endWork= ?,  maxAppointmentCount= ?, createdAt= ?, updatedAt = ? "
                    + "WHERE id=? ";
            update = true;
        } else {
            sql = "INSERT INTO doctor_schedule "
                    + "(date, doctorId, startWork, endWork,  maxAppointmentCount, createdAt, updatedAt) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?, ?, ?)";
        }
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = ConnectionFactory.getConnection();
            s = c.prepareStatement(sql);
            s.setDate(1, Date.valueOf(entity.getDate()));
            s.setLong(2, entity.getDoctorId());
            s.setTime(3, Time.valueOf(entity.getStartWork()));
            s.setTime(4, Time.valueOf(entity.getEndWork()));
            s.setInt(5, entity.getMaxAppointmentCount());
            Timestamp now = Timestamp.valueOf(LocalDateTime.now());
            if (update) {
                s.setTimestamp(6, entity.getCreatedAt());
                s.setLong(8, entity.getId());
            } else {
                s.setTimestamp(6, now);
            }
            s.setTimestamp(7, now);
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
    public DoctorSchedule findById(Long id) {
        String sql = "select id, date, doctorId, startWork, endWork,  maxAppointmentCount, createdAt, updatedAt "
                + "FROM doctor_schedule "
                + "WHERE id = ?";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        DoctorSchedule doctorSchedule = null;
        try {
            c = ConnectionFactory.getConnection();
            s = c.prepareStatement(sql);
            s.setLong(1, id);
            r = s.executeQuery();
            if (r.next()) {
                doctorSchedule = DoctorSchedule.builder()
                        .date(r.getDate("date").toLocalDate())
                        .doctorId(r.getLong("doctorId"))
                        .startWork(r.getTime("startWork").toLocalTime())
                        .endWork(r.getTime("endWork").toLocalTime())
                        .maxAppointmentCount(r.getInt("maxAppointmentCount"))
                        .build();
                doctorSchedule.setId(r.getLong("id"));
                doctorSchedule.setCreatedAt(r.getTimestamp("createdAt"));
                doctorSchedule.setUpdatedAt(r.getTimestamp("updatedAt"));
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
        return doctorSchedule;
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM doctor_schedule "
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
    public List<DoctorSchedule> findAll() {
        String sql = "select id, date, doctorId, startWork, endWork,  maxAppointmentCount, createdAt, updatedAt "
                + "from doctor_schedule";
        Connection c = null;
        Statement s = null;
        ResultSet r = null;
        LinkedList<DoctorSchedule> doctorSchedules = new LinkedList<>();
        try {
            c = ConnectionFactory.getConnection();
            s = c.createStatement();
            r = s.executeQuery(sql);
            while (r.next()) {
                DoctorSchedule doctorSchedule = DoctorSchedule.builder()
                        .date(r.getDate("date").toLocalDate())
                        .doctorId(r.getLong("doctorId"))
                        .startWork(r.getTime("startWork").toLocalTime())
                        .endWork(r.getTime("endWork").toLocalTime())
                        .maxAppointmentCount(r.getInt("maxAppointmentCount"))
                        .build();
                doctorSchedule.setId(r.getLong("id"));
                doctorSchedule.setCreatedAt(r.getTimestamp("createdAt"));
                doctorSchedule.setUpdatedAt(r.getTimestamp("updatedAt"));
                doctorSchedules.add(doctorSchedule);
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
        return doctorSchedules;
    }
}
