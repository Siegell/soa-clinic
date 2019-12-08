package by.siegell.soa.clinic.web.appointment;

import by.siegell.soa.clinic.domain.Appointment;
import by.siegell.soa.clinic.service.AppointmentService;
import by.siegell.soa.clinic.web.Action;
import by.siegell.soa.clinic.web.ActionResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AppointmentEditActionImpl implements Action {

    private AppointmentService appointmentService;

    public void setAppointmentService(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Override
    public ActionResult exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
            Appointment appointment = appointmentService.findById(id);
            req.setAttribute("appointment", appointment);
        } catch (Exception ignored) {}
        return null;
    }
}
