package ru.kononov.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.kononov.DataSource;
import ru.kononov.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User createUser(User user) {
        Object[] params = new Object[]{user.getName()};
        int[] types = new int[]{Types.VARCHAR};
        int newId = this.jdbcTemplate.update("INSERT INTO users (id,username) VALUES (DEFAULT,?)",params,types);
        user.setId(newId);
        return getUserByName(user.getName());
    }

    @Override
    public User getUserById(Long userId) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?", new Object[]{userId}, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new User(rs.getInt(1),rs.getString(2));
            }
        });
    }

    @Override
    public User getUserByName(String userName) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE username = ?", new Object[]{userName}, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new User(rs.getInt(1),rs.getString(2));
            }
        });
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) this.jdbcTemplate.query("SELECT * FROM users", new RowMapper<User>() {
            public User mapRow(ResultSet arg0, int arg1) throws SQLException {
                User user = new User(arg0.getInt(1),arg0.getString(2));
                return user;
            }
        });
    }

    @Override
    public void deleteUser(Long userId) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", new Object[]{userId});
    }
}
