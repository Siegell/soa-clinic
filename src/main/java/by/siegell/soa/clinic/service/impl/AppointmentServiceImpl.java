package by.siegell.soa.clinic.service.impl;

import by.siegell.soa.clinic.dao.AppointmentDao;
import by.siegell.soa.clinic.dao.DoctorScheduleDao;
import by.siegell.soa.clinic.domain.Appointment;
import by.siegell.soa.clinic.domain.DoctorSchedule;
import by.siegell.soa.clinic.service.AppointmentService;
import by.siegell.soa.clinic.service.ServiceException;
import by.siegell.soa.clinic.util.LocalTimeUtil;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AppointmentServiceImpl implements AppointmentService {

    private AppointmentDao appointmentDao;
    private DoctorScheduleDao doctorScheduleDao;

    public void setAppointmentDao(AppointmentDao appointmentDao) {
        this.appointmentDao = appointmentDao;
    }

    public void setDoctorScheduleDao(DoctorScheduleDao doctorScheduleDao) {
        this.doctorScheduleDao = doctorScheduleDao;
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentDao.findAll();
    }

    @Override
    public void save(Appointment appointment) throws ServiceException {
        boolean validAppointment = true;
        Duration appointmentWorkTime;
        if (appointment.getId() != null) {
            appointmentWorkTime = getDoctorAppointmentWorkTimeWithoutAppointment(appointment.getDoctorScheduleId(), appointment.getId());
        } else {
            appointmentWorkTime = getDoctorAppointmentWorkTime(appointment.getDoctorScheduleId());
        }
        appointmentWorkTime = appointmentWorkTime.plus(appointment.getAppointmentDuration());
        if (appointmentWorkTime.multipliedBy(100).compareTo(doctorScheduleDao.findById(appointment.getDoctorScheduleId()).getWorkTime().multipliedBy(80)) > 0) {
            validAppointment = false;
        }

        DoctorSchedule doctorSchedule = doctorScheduleDao.findById(appointment.getDoctorScheduleId());
        if (appointment.getStartTime().isBefore(doctorSchedule.getStartWork()) || appointment.getEndTime().isAfter(doctorSchedule.getEndWork())) {
            validAppointment = false;
        }

        List<Appointment> appointments = appointmentDao.findByDoctorScheduleId(appointment.getDoctorScheduleId());

        for (Appointment app : appointments) {
            if ((LocalTimeUtil.isBetween(appointment.getStartTime(), app.getStartTime(), app.getEndTime())
                    || LocalTimeUtil.isBetween(appointment.getEndTime(), app.getStartTime(), app.getEndTime())
                    || (appointment.getStartTime().isBefore(app.getStartTime()) && appointment.getEndTime().isAfter(app.getEndTime())))
                    && appointment.getId() != null && !Objects.equals(appointment.getId(), app.getId())) {
                validAppointment = false;
            }
        }

        if (validAppointment) {
            appointmentDao.save(appointment);
        } else {
            throw new ServiceException("Not valid appointment");
        }
    }

    @Override
    public void delete(Long id) {
        appointmentDao.delete(id);
    }

    @Override
    public Duration getDoctorAppointmentWorkTime(Long doctorId, LocalDate date) {
        return getDoctorAppointmentWorkTime(doctorScheduleDao.findByDoctorIdAndDate(doctorId, date));
    }

    @Override
    public Appointment findById(long id) {
        return appointmentDao.findById(id);
    }

    private Duration getDoctorAppointmentWorkTime(DoctorSchedule doctorSchedule) {
        return getDoctorAppointmentWorkTime(doctorSchedule.getDoctorId());
    }

    private Duration getDoctorAppointmentWorkTime(Long doctorScheduleId) {
        List<Appointment> appointments = appointmentDao.findByDoctorScheduleId(doctorScheduleId);
        Duration workTime = Duration.ZERO;
        for (Appointment appointment : appointments) {
            workTime = workTime.plus(appointment.getAppointmentDuration());
        }
        return workTime;
    }

    private Duration getDoctorAppointmentWorkTimeWithoutAppointment(Long doctorScheduleId, Long appointmentId) {
        List<Appointment> appointments = appointmentDao.findByDoctorScheduleId(doctorScheduleId).stream()
                .filter(appointment -> !Objects.equals(appointment.getId(), appointmentId))
                .collect(Collectors.toList());
        Duration workTime = Duration.ZERO;
        for (Appointment appointment : appointments) {
            workTime = workTime.plus(appointment.getAppointmentDuration());
        }
        return workTime;
    }
}
