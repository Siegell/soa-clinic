package by.siegell.soa.clinic.web.doctorSchedule;

import by.siegell.soa.clinic.domain.DoctorSchedule;
import by.siegell.soa.clinic.service.DoctorScheduleService;
import by.siegell.soa.clinic.web.Action;
import by.siegell.soa.clinic.web.ActionResult;
import by.siegell.soa.clinic.web.ActionResultType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

public class DoctorScheduleSaveActionImpl implements Action {

    private DoctorScheduleService doctorScheduleService;

    public void setDoctorScheduleService(DoctorScheduleService doctorScheduleService) {
        this.doctorScheduleService = doctorScheduleService;
    }

    @Override
    public ActionResult exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try {
            DoctorSchedule doctorSchedule = getDoctorSchedule(req);
            doctorScheduleService.save(doctorSchedule);
            return new ActionResult("/doctor_schedule/list.html?doctorId="  + doctorSchedule.getDoctorId());
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            return new ActionResult("/error", ActionResultType.FORWARD);
        }
    }

    private DoctorSchedule getDoctorSchedule(HttpServletRequest req) {
        return DoctorSchedule.builder()
                .date(LocalDate.parse(req.getParameter("date")))
                .doctorId(Long.parseLong(req.getParameter("doctorId")))
                .startWork(LocalTime.parse(req.getParameter("startWork")))
                .endWork(LocalTime.parse(req.getParameter("endWork")))
                .maxAppointmentCount(Integer.parseInt(req.getParameter("maxAppointmentCount")))
                .id(Long.parseLong(req.getParameter("id")))
                .createdAt(Timestamp.valueOf(req.getParameter("createdAt")))
                .updatedAt(Timestamp.valueOf(req.getParameter("updatedAt")))
                .build();
    }
}
