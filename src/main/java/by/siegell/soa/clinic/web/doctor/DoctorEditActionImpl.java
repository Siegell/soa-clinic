package by.siegell.soa.clinic.web.doctor;

import by.siegell.soa.clinic.domain.Doctor;
import by.siegell.soa.clinic.service.DoctorService;
import by.siegell.soa.clinic.web.Action;
import by.siegell.soa.clinic.web.ActionResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DoctorEditActionImpl implements Action {
    private DoctorService service;

    public void setDoctorService(DoctorService service) {
        this.service = service;
    }

    @Override
    public ActionResult exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;

        id = Long.parseLong(req.getParameter("id"));

        if (id != null) {
            Doctor doctor = service.findById(id);
            req.setAttribute("doctor", doctor);
        }

        return null;
    }
}
