package by.siegell.soa.clinic.util;

import by.siegell.soa.clinic.domain.User;
import by.siegell.soa.clinic.service.UserService;

import java.util.Arrays;

public class PermissionHelper {
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public boolean hasPermission(Long userId, String role) {
        User user = userService.findById(userId);
        String roles = user.getRoles();
        return Arrays.stream(roles.split("|")).allMatch(s -> s.equals(role));
    }

    public boolean hasAnyPermission(Long userId, String... roles) {
        boolean hasPermission = false;
        for (String role : roles) {
            hasPermission = hasPermission || hasPermission(userId, role);
        }
        return hasPermission;
    }
}
