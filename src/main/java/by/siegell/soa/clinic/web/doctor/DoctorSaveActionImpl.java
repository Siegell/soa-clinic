package by.siegell.soa.clinic.web.doctor;

import by.siegell.soa.clinic.domain.Doctor;
import by.siegell.soa.clinic.service.DoctorService;
import by.siegell.soa.clinic.web.Action;
import by.siegell.soa.clinic.web.ActionResult;
import by.siegell.soa.clinic.web.ActionResultType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

public class DoctorSaveActionImpl extends Action {
    private DoctorService doctorService;

    public void setDoctorService(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    {
        permissions = "admin|nurse";
    }

    @Override
    public ActionResult exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try {
            Doctor doctor = getDoctor(req);
            doctorService.save(doctor);
            return new ActionResult("/doctor/list.html");
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            return new ActionResult("/error", ActionResultType.FORWARD);
        }
    }

    private Doctor getDoctor(HttpServletRequest req) {
        Doctor entity = Doctor.builder()
                .firstName(req.getParameter("firstName"))
                .lastName(req.getParameter("lastName"))
                .middleName(req.getParameter("middleName"))
                .cabinet(req.getParameter("cabinet"))
                .district(req.getParameter("district"))
                .specialization(req.getParameter("specialization"))
                .build();
        try {
            entity.setId(Long.parseLong(req.getParameter("id")));
            entity.setCreatedAt(Timestamp.valueOf(req.getParameter("createdAt")));
            entity.setUpdatedAt(Timestamp.valueOf(req.getParameter("updatedAt")));
        } catch (Exception ignored) {
        }
        return entity;
    }
}
