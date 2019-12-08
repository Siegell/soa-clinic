package by.siegell.soa.clinic.web.user;

import by.siegell.soa.clinic.domain.User;
import by.siegell.soa.clinic.service.UserService;
import by.siegell.soa.clinic.web.Action;
import by.siegell.soa.clinic.web.ActionResult;
import by.siegell.soa.clinic.web.ActionResultType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserSaveActionImpl implements Action {

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ActionResult exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try {
            User user = getUser(req);
            userService.save(user);
            return new ActionResult("/user/list.html");
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            return new ActionResult("/error", ActionResultType.FORWARD);
        }
    }

    private User getUser(HttpServletRequest req) {
        return User.builder()
                .id(Long.parseLong(req.getParameter("id")))
                .username(req.getParameter("username"))
                .password(req.getParameter("password"))
                .build();
    }
}
