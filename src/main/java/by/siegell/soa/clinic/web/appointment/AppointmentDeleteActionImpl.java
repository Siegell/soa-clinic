package by.siegell.soa.clinic.web.appointment;

import by.siegell.soa.clinic.service.AppointmentService;
import by.siegell.soa.clinic.web.Action;
import by.siegell.soa.clinic.web.ActionResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AppointmentDeleteActionImpl extends Action {
    private AppointmentService appointmentService;

    {
        permissions = "admin|nurse";
    }

    @Override
    public ActionResult exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
            appointmentService.delete(id);
        } catch (NumberFormatException ignored) {
        }
        return new ActionResult("/appointment/list.html");
    }

    public void setAppointmentService(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
}
