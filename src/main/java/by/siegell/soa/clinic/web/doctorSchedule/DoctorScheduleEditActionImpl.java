package by.siegell.soa.clinic.web.doctorSchedule;

import by.siegell.soa.clinic.domain.DoctorSchedule;
import by.siegell.soa.clinic.service.DoctorScheduleService;
import by.siegell.soa.clinic.web.Action;
import by.siegell.soa.clinic.web.ActionResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DoctorScheduleEditActionImpl implements Action {
    private DoctorScheduleService doctorScheduleService;

    public void setDoctorScheduleService(DoctorScheduleService doctorScheduleService) {
        this.doctorScheduleService = doctorScheduleService;
    }

    @Override
    public ActionResult exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;

        id = Long.parseLong(req.getParameter("id"));

        if (id != null) {
            DoctorSchedule doctorSchedule = doctorScheduleService.findById(id);
            req.setAttribute("doctorSchedule", doctorSchedule);
        }
        return null;
    }
}
