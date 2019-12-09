package by.siegell.soa.clinic.web.doctorSchedule;

import by.siegell.soa.clinic.service.DoctorScheduleService;
import by.siegell.soa.clinic.web.Action;
import by.siegell.soa.clinic.web.ActionResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DoctorScheduleDeleteActionImpl extends Action {
    private DoctorScheduleService doctorScheduleService;

    public void setDoctorScheduleService(DoctorScheduleService doctorScheduleService) {
        this.doctorScheduleService = doctorScheduleService;
    }

    {
        permissions = "admin|nurse";
    }

    @Override
    public ActionResult exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
            doctorScheduleService.delete(id);
        } catch (NumberFormatException ignored) {}
        return new ActionResult("/doctor_schedule/list.html");    }
}
