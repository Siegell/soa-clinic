package by.siegell.soa.clinic.dao;

import by.siegell.soa.clinic.domain.User;

public interface UserDao extends Dao<User> {
    User findByUserName(String username);
}
