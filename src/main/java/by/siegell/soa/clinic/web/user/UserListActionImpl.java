package by.siegell.soa.clinic.web.user;

import by.siegell.soa.clinic.domain.User;
import by.siegell.soa.clinic.service.UserService;
import by.siegell.soa.clinic.web.Action;
import by.siegell.soa.clinic.web.ActionResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserListActionImpl extends Action {
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    {
        permissions = "admin";
    }

    @Override
    public ActionResult exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.findAll();
        req.setAttribute("users", users);
        return null;
    }
}
