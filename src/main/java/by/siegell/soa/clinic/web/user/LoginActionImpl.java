package by.siegell.soa.clinic.web.user;

import by.siegell.soa.clinic.service.UserService;
import by.siegell.soa.clinic.web.Action;
import by.siegell.soa.clinic.web.ActionResult;
import by.siegell.soa.clinic.web.ActionResultType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginActionImpl implements Action {
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ActionResult exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Long userId = userService.login(username, password);
        if (userId != null) {
            req.getSession().setAttribute("userId", userId);
            return new ActionResult("/doctor/list.html");
        } else {
            req.setAttribute("message", "Not correct login");
            return new ActionResult("/error", ActionResultType.FORWARD);
        }

    }
}
