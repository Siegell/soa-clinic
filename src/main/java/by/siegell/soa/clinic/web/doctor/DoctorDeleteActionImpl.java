package by.siegell.soa.clinic.web.doctor;

import by.siegell.soa.clinic.service.DoctorService;
import by.siegell.soa.clinic.web.Action;
import by.siegell.soa.clinic.web.ActionResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DoctorDeleteActionImpl extends Action {
    private DoctorService doctorService;

    {
        permissions = "admin|nurse";
    }

    public void setDoctorService(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Override
    public ActionResult exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
            doctorService.delete(id);
        } catch (NumberFormatException ignored) {}
        return new ActionResult("/doctor/list.html");
    }
}
