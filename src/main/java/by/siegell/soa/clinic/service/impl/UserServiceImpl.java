package by.siegell.soa.clinic.service.impl;

import by.siegell.soa.clinic.dao.UserDao;
import by.siegell.soa.clinic.domain.User;
import by.siegell.soa.clinic.service.UserService;

import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public Long login(String username, String password) {
        User user = userDao.findByUserName(username);
        return user != null && Objects.equals(user.getPassword(), password) ? user.getId() : null;
    }
}
