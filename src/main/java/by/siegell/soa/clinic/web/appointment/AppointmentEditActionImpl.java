package by.siegell.soa.clinic.web.appointment;

import by.siegell.soa.clinic.domain.Appointment;
import by.siegell.soa.clinic.service.AppointmentService;
import by.siegell.soa.clinic.web.Action;
import by.siegell.soa.clinic.web.ActionResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AppointmentEditActionImpl extends Action {

    private AppointmentService appointmentService;

    public void setAppointmentService(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    {
        permissions = "admin|nurse";
    }

    @Override
    public ActionResult exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        Long doctorScheduleId = null;
        Appointment appointment = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (Exception ignored) {
        }
        try {
            doctorScheduleId = Long.parseLong(req.getParameter("doctorScheduleId"));
        } catch (Exception ignored) {}
        if (id != null) {
            appointment = appointmentService.findById(id);
        } else {
            appointment = new Appointment();
            appointment.setDoctorScheduleId(doctorScheduleId);
        }
        req.setAttribute("appointment", appointment);

        return null;
    }
}
