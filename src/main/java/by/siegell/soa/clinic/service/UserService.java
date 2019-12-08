package by.siegell.soa.clinic.service;

import by.siegell.soa.clinic.domain.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Long id);

    void save(User user);

    void delete(Long id);

    Long login(String username, String password);
}
