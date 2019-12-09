package by.siegell.soa.clinic.web;

import by.siegell.soa.clinic.util.PermissionHelper;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Action {
    protected String permissions;
    private PermissionHelper permissionHelper;

    public abstract ActionResult exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    public void setPermissionHelper(PermissionHelper permissionHelper) {
        this.permissionHelper = permissionHelper;
    }

    public ActionResult execWithPermissionCheck(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = null;
        try {
            userId = Long.valueOf(req.getSession().getAttribute("userId").toString());
        } catch (Exception ignored) {
        }
        if (StringUtils.isEmpty(permissions) || userId != null && permissionHelper.hasAnyPermission(userId, permissions.split("\\|"))) {
            return exec(req, resp);
        } else {
            req.setAttribute("message", "Permission denied!");
            return new ActionResult("/error", ActionResultType.FORWARD);
        }
    }
}