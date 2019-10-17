package by.siegell.soa.clinic.dao.impl;

import by.siegell.soa.clinic.dao.DoctorScheduleDao;
import by.siegell.soa.clinic.domain.DoctorSchedule;
import by.siegell.soa.clinic.imap.IdentityMap;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class DoctorScheduleDaoImpl extends BaseDaoMySqlImpl implements DoctorScheduleDao {

    private IdentityMap<DoctorSchedule, Long> imap = new IdentityMap<>();

    private static DoctorSchedule fillDoctorSchedule(ResultSet r) throws SQLException {
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
        return doctorSchedule;
    }

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
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setDate(1, Date.valueOf(entity.getDate()));
            s.setLong(2, entity.getDoctorId());
            s.setTime(3, Time.valueOf(entity.getStartWork()));
            s.setTime(4, Time.valueOf(entity.getEndWork()));
            s.setInt(5, entity.getMaxAppointmentCount());
            Timestamp now = Timestamp.valueOf(LocalDateTime.now());
            s.setTimestamp(7, now);
            if (update) {
                s.setTimestamp(6, entity.getCreatedAt());
                s.setLong(8, entity.getId());

                imap.putObject(entity.getId(), entity);

            } else {
                s.setTimestamp(6, now);
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
    public DoctorSchedule findById(Long id) {
        DoctorSchedule doctorSchedule = imap.getObject(id);
        if (doctorSchedule != null) {
            return doctorSchedule;
        }

        String sql = "select id, date, doctorId, startWork, endWork,  maxAppointmentCount, createdAt, updatedAt "
                + "FROM doctor_schedule "
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
                doctorSchedule = fillDoctorSchedule(r);
                imap.putObject(doctorSchedule.getId(), doctorSchedule);
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
        imap.deleteObject(id);
        String sql = "DELETE FROM doctor_schedule "
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
    public List<DoctorSchedule> findAll() {
        String sql = "select id, date, doctorId, startWork, endWork,  maxAppointmentCount, createdAt, updatedAt "
                + "from doctor_schedule";
        Connection c = null;
        Statement s = null;
        ResultSet r = null;
        LinkedList<DoctorSchedule> doctorSchedules = new LinkedList<>();
        try {
            c = getConnection();
            s = c.createStatement();
            r = s.executeQuery(sql);
            while (r.next()) {
                DoctorSchedule doctorSchedule = fillDoctorSchedule(r);
                doctorSchedules.add(doctorSchedule);
                imap.putObject(doctorSchedule.getId(), doctorSchedule);
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

    @Override
    public Optional<DoctorSchedule> findBySubEntity(DoctorSchedule entity) {
        String sql = "select id, date, doctorId, startWork, endWork,  maxAppointmentCount, createdAt, updatedAt "
                + "FROM doctor_schedule "
                + "WHERE date = ? AND doctorid = ? AND startwork = ? AND endwork = ? AND maxappointmentcount = ? ";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        DoctorSchedule doctorSchedule = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setDate(1, Date.valueOf(entity.getDate()));
            s.setLong(2, entity.getDoctorId());
            s.setTime(3, Time.valueOf(entity.getStartWork()));
            s.setTime(4, Time.valueOf(entity.getEndWork()));
            s.setInt(5, entity.getMaxAppointmentCount());
            r = s.executeQuery();
            if (r.next()) {
                doctorSchedule = fillDoctorSchedule(r);
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
        return Optional.of(doctorSchedule);
    }

    @Override
    public DoctorSchedule findByDoctorIdAndDate(Long doctorId, LocalDate date) {
        String sql = "select id, date, doctorId, startWork, endWork,  maxAppointmentCount, createdAt, updatedAt "
                + "FROM doctor_schedule "
                + "WHERE date = ? AND doctorid = ?";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        DoctorSchedule doctorSchedule = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setDate(1, Date.valueOf(date));
            s.setLong(2, doctorId);
            r = s.executeQuery();
            if (r.next()) {
                doctorSchedule = fillDoctorSchedule(r);
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
}
