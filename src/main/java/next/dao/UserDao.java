package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import next.model.User;

public class UserDao {
    public void insert(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        PreparedStatementSetter preparedStatementSetter = pstmt -> {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
        };

        jdbcTemplate.update("INSERT INTO USERS VALUES (?, ?, ?, ?)", preparedStatementSetter);
    }

    public void update(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        PreparedStatementSetter preparedStatementSetter = pstmt -> {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getUserId());
        };

        jdbcTemplate.update("UPDATE USERS SET userId=?, password=?, name=?, email=? WHERE userId=?", preparedStatementSetter);
    }


    public List<User> findAll() throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        PreparedStatementSetter preparedStatementSetter = pstmt -> {

        };
        RowMapper rowMapper = rs -> new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                rs.getString("email"));
        return jdbcTemplate.query("SELECT userId, password, name, email FROM USERS", preparedStatementSetter, rowMapper);
    }


    public User findByUserId(String userId) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        PreparedStatementSetter preparedStatementSetter = pstmt -> pstmt.setString(1, userId);
        RowMapper rowMapper = rs -> new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                rs.getString("email"));
        return (User)jdbcTemplate.queryForObject("SELECT userId, password, name, email FROM USERS WHERE userid=?", preparedStatementSetter, rowMapper);
    }

}
