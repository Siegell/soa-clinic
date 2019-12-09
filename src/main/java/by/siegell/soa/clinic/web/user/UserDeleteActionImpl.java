package by.siegell.soa.clinic.web.user;

import by.siegell.soa.clinic.service.UserService;
import by.siegell.soa.clinic.web.Action;
import by.siegell.soa.clinic.web.ActionResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserDeleteActionImpl extends Action {
    private UserService userService;

    {
        permissions = "admin";
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ActionResult exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
            userService.delete(id);
        } catch (NumberFormatException ignored) {}
        return new ActionResult("/user/list.html");    }
}
