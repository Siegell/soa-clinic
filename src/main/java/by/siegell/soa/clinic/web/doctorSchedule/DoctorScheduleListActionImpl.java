package by.siegell.soa.clinic.web.doctorSchedule;

import by.siegell.soa.clinic.domain.DoctorSchedule;
import by.siegell.soa.clinic.service.DoctorScheduleService;
import by.siegell.soa.clinic.web.Action;
import by.siegell.soa.clinic.web.ActionResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DoctorScheduleListActionImpl extends Action {
    private DoctorScheduleService doctorScheduleService;

    public void setDoctorScheduleService(DoctorScheduleService doctorScheduleService) {
        this.doctorScheduleService = doctorScheduleService;
    }

    @Override
    public ActionResult exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long doctorId = Long.parseLong(req.getParameter("doctorId"));
        List<DoctorSchedule> doctorSchedules = doctorScheduleService.findAll()
                .stream()
                .filter(doctorSchedule -> Objects.equals(doctorSchedule.getDoctorId(), doctorId))
                .collect(Collectors.toList());
        req.setAttribute("doctorSchedules", doctorSchedules);
        req.setAttribute("doctorId", doctorId);
        return null;
    }
}
