package by.siegell.soa.clinic.dao.impl;

import by.siegell.soa.clinic.dao.UserDao;
import by.siegell.soa.clinic.domain.User;
import by.siegell.soa.clinic.imap.IdentityMap;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends BaseDaoMySqlImpl implements UserDao {

    private IdentityMap<User, Long> imap = new IdentityMap<>();


    @Override
    public void save(User entity) {
        String sql;
        boolean update = false;
        if (entity.getId() != null && findById(entity.getId()) != null) {
            sql = "UPDATE \"user\" SET "
                    + "username = ?, password= ?, roles= ?"
                    + "WHERE id=? ";
            update = true;
        } else {
            sql = "INSERT INTO \"user\" "
                    + "(username, password, roles) "
                    + "VALUES "
                    + "(?, ?, ?)";
        }
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setString(1, entity.getUsername());
            s.setString(2, entity.getPassword());
            s.setString(3, entity.getRoles());
            if (update) {
                s.setLong(4, entity.getId());
            }
            s.executeUpdate();
            c.commit();
        } catch (Exception e) {
            try {
                c.rollback();
            } catch (SQLException ignored) {
                e.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                s.close();
            } catch (NullPointerException | SQLException ignored) {
            }
            try {
                c.close();
            } catch (NullPointerException | SQLException ignored) {
            }
        }
    }

    @Override
    public User findById(Long id) {
        User user = imap.getObject(id);
        if (user != null) {
            return user;
        }


        String sql = "select id, username, password, roles "
                + "FROM \"user\" "
                + "WHERE id = ?";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setLong(1, id);
            r = s.executeQuery();
            if (r.next()) {
                user = User.builder()
                        .username(r.getString("username"))
                        .password(r.getString("password"))
                        .roles(r.getString("roles"))
                        .build();
                user.setId(r.getLong("id"));

                imap.putObject(user.getId(), user);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                r.close();

            } catch (NullPointerException | SQLException e) {
                e.printStackTrace();
            }
            try {
                s.close();
            } catch (NullPointerException | SQLException e) {
                e.printStackTrace();
            }
            try {
                c.close();
            } catch (NullPointerException | SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public void delete(Long id) {
        imap.deleteObject(id);

        String sql = "DELETE FROM \"user\" "
                + "WHERE id = ?";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setLong(1, id);
            s.executeUpdate();
            c.commit();
        } catch (Exception e) {
            try {
                c.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                s.close();
            } catch (NullPointerException | SQLException ignored) {
            }
            try {
                c.close();
            } catch (NullPointerException | SQLException ignored) {
            }
        }
    }

    @Override
    public List<User> findAll() {
        String sql = "select id, username, password, roles "
                + "FROM \"user\"";
        Connection c = null;
        Statement s = null;
        ResultSet r = null;
        LinkedList<User> users = new LinkedList<>();
        try {
            c = getConnection();
            s = c.createStatement();
            r = s.executeQuery(sql);
            while (r.next()) {
                User doctor = User.builder()
                        .username(r.getString("username"))
                        .password(r.getString("password"))
                        .roles(r.getString("roles"))
                        .build();
                doctor.setId(r.getLong("id"));

                users.add(doctor);
                imap.putObject(doctor.getId(), doctor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                r.close();
            } catch (NullPointerException | SQLException ignored) {
            }
            try {
                s.close();
            } catch (NullPointerException | SQLException ignored) {
            }
            try {
                c.close();
            } catch (NullPointerException | SQLException ignored) {
            }
        }
        return users;
    }

    @Override
    public Optional<User> findBySubEntity(User entity) {
        return Optional.empty();
    }

    @Override
    public User findByUserName(String username) {
        User user = null;
        String sql = "select id, username, password, roles "
                + "FROM \"user\" "
                + "WHERE username = ?";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setString(1, username);
            r = s.executeQuery();
            if (r.next()) {
                user = User.builder()
                        .username(r.getString("username"))
                        .password(r.getString("password"))
                        .roles(r.getString("roles"))
                        .build();
                user.setId(r.getLong("id"));

                imap.putObject(user.getId(), user);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                r.close();

            } catch (NullPointerException | SQLException e) {
                e.printStackTrace();
            }
            try {
                s.close();
            } catch (NullPointerException | SQLException e) {
                e.printStackTrace();
            }
            try {
                c.close();
            } catch (NullPointerException | SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }
}
