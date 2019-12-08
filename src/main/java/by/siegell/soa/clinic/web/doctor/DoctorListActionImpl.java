package by.siegell.soa.clinic.web.doctor;

import by.siegell.soa.clinic.domain.Doctor;
import by.siegell.soa.clinic.service.DoctorService;
import by.siegell.soa.clinic.web.Action;
import by.siegell.soa.clinic.web.ActionResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DoctorListActionImpl implements Action {
    private DoctorService doctorService;

    @Override
    public ActionResult exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Doctor> doctors = doctorService.findAll();
        req.setAttribute("doctors", doctors);
        return null;
    }

    public void setDoctorService(DoctorService service) {
        doctorService = service;
    }
}
