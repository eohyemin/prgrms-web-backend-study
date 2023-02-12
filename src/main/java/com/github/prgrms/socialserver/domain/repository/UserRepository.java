package com.github.prgrms.socialserver.domain.repository;

import com.github.prgrms.socialserver.domain.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {
    private JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long save(User user) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("users").usingGeneratedKeyColumns("seq");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", user.getEmail());
        parameters.put("passwd", user.getPasswd());
        parameters.put("login_count", 0);
        parameters.put("create_at", LocalDateTime.now());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        user.setSeq(key.longValue());

        return user.getSeq();
    }

    public List<User> findAll() {
         return jdbcTemplate.query("SELECT * FROM USERS", userRowMapper());
    }

    public Optional<User> findById(Long id) {
        User user = jdbcTemplate.queryForObject("SELECT * FROM USERS WHERE SEQ = ?", userRowMapper(), id);
        return Optional.ofNullable(user);
    }

    public Optional<User> findByEmail(String email) {
        try {
            User user = jdbcTemplate.queryForObject("SELECT * FROM USERS WHERE EMAIL = ?", userRowMapper(), email);
            return Optional.ofNullable(user);
        } catch(EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public boolean existByEmail(String email) {
        try{
            User user = jdbcTemplate.queryForObject("SELECT * FROM USERS WHERE EMAIL = ?", userRowMapper(), email);
            return Optional.of(user).isPresent();
        }catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    private RowMapper<User> userRowMapper() {
        return new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                Timestamp lastLoginDate = rs.getTimestamp("last_login_date");
                return new User.Builder(rs.getString("email"), rs.getString("passwd"))
                        .seq(rs.getLong("seq"))
                        .loginCount(rs.getInt("login_count"))
                        .lastLoginDate(lastLoginDate == null ? null : lastLoginDate.toLocalDateTime())
                        .createAt(rs.getTimestamp("create_at").toLocalDateTime())
                        .build();
            }
        };
    }

}
