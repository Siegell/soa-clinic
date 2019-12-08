package by.siegell.soa.clinic.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Action {
    ActionResult exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}