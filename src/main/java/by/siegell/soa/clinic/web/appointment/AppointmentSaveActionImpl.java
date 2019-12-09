package by.siegell.soa.clinic.web.appointment;

import by.siegell.soa.clinic.domain.Appointment;
import by.siegell.soa.clinic.service.AppointmentService;
import by.siegell.soa.clinic.web.Action;
import by.siegell.soa.clinic.web.ActionResult;
import by.siegell.soa.clinic.web.ActionResultType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalTime;

public class AppointmentSaveActionImpl extends Action {

    private AppointmentService appointmentService;

    public void setAppointmentService(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    {
        permissions = "admin|nurse";
    }

    @Override
    public ActionResult exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try {
            Appointment appointment = getAppointment(req);
            appointmentService.save(appointment);
            return new ActionResult("/appointment/list.html?doctorScheduleId=" + appointment.getDoctorScheduleId());
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            return new ActionResult("/error", ActionResultType.FORWARD);
        }
    }

    private Appointment getAppointment(HttpServletRequest req) {
        Appointment entity = Appointment.builder()
                .doctorScheduleId(Long.parseLong(req.getParameter("doctorScheduleId")))
                .firstName(req.getParameter("firstName"))
                .lastName(req.getParameter("lastName"))
                .middleName(req.getParameter("middleName"))
                .startTime(LocalTime.parse(req.getParameter("startTime")))
                .endTime(LocalTime.parse(req.getParameter("endTime")))
                .build();
        try {
            entity.setId(Long.parseLong(req.getParameter("id")));
            entity.setCreatedAt(Timestamp.valueOf(req.getParameter("createdAt")));
            entity.setUpdatedAt(Timestamp.valueOf(req.getParameter("updatedAt")));
        } catch (Exception ignored) {}
        return entity;
    }
}
