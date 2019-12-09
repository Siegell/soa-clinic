package by.siegell.soa.clinic.web.appointment;

import by.siegell.soa.clinic.domain.Appointment;
import by.siegell.soa.clinic.service.AppointmentService;
import by.siegell.soa.clinic.web.Action;
import by.siegell.soa.clinic.web.ActionResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class AppointmentListActionImpl extends Action {
    private AppointmentService appointmentService;

    @Override
    public ActionResult exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        long doctorScheduleId = Long.parseLong(req.getParameter("doctorScheduleId"));
        List<Appointment> appointments = appointmentService.findAll().stream()
                .filter(appointment -> appointment.getDoctorScheduleId() == doctorScheduleId)
                .collect(Collectors.toList());
        req.setAttribute("appointments", appointments);
        req.setAttribute("doctorScheduleId", doctorScheduleId);
        return null;
    }

    public void setAppointmentService(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
}
