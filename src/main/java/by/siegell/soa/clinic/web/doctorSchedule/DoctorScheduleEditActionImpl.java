package by.siegell.soa.clinic.web.doctorSchedule;

import by.siegell.soa.clinic.domain.DoctorSchedule;
import by.siegell.soa.clinic.service.DoctorScheduleService;
import by.siegell.soa.clinic.web.Action;
import by.siegell.soa.clinic.web.ActionResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DoctorScheduleEditActionImpl extends Action {
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
        Long doctorId = null;
        DoctorSchedule doctorSchedule = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (Exception ignored) {
        }
        try {
            doctorId = Long.parseLong(req.getParameter("doctorId"));
        } catch (Exception ignored) {
        }
        if (id != null) {
            doctorSchedule = doctorScheduleService.findById(id);
        } else {
            doctorSchedule = new DoctorSchedule();
            doctorSchedule.setDoctorId(doctorId);
        }
        req.setAttribute("doctorSchedule", doctorSchedule);
        return null;
    }
}
