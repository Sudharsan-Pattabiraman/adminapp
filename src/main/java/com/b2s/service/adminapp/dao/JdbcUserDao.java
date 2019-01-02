package com.b2s.service.adminapp.dao;

import com.b2s.service.adminapp.exception.NotFoundException;
import com.b2s.service.adminapp.model.User;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by spattabiraman on 10/19/2018.
 */

@Repository
public class JdbcUserDao implements UserDao {

    final String SQL_QUERY = "select * from UserDetails where userid = :userId and userpassword = :userPassword";

    private final String INSERT_QUERY = "insert into UserDetails(UserId, UserPassword, UserName, UserAddress, UserCity, UserState, UserZip) " +
            "values(:userId, :userPassword, :userName, :userAddress, :userCity, :userState, :userZip)";

    private final String UPDATE_QUERY = "update UserDetails set userpassword = :userPassword, username = :userName, useraddress = :userAddress, " +
            "usercity = :userCity, userstate = :userState, userzip = :userZip where userid = :id and userpassword = :password";

    private final String DELETE_QUERY = "delete from UserDetails where userid = :userId and userpassword = :userPassword";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public JdbcUserDao(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public int insertUser(final User user) {

        SqlParameterSource userMap = new MapSqlParameterSource()
                .addValue("userId", user.getUserId())
                .addValue("userPassword", user.getUserPassword())
                .addValue("userName", user.getUserName())
                .addValue("userAddress", user.getUserAddress())
                .addValue("userCity", user.getUserCity().orElse(null))
                .addValue("userState", user.getUserState())
                .addValue("userZip", user.getUserZIP());
        return namedParameterJdbcTemplate.update(INSERT_QUERY, userMap);
    }

    public User retrieveUser(final String id, final String password) {

        SqlParameterSource idMap = new MapSqlParameterSource()
                .addValue("userId", id)
                .addValue("userPassword", password);
        try {
            return (User) namedParameterJdbcTemplate.queryForObject(SQL_QUERY, idMap, new UserRowMapper());
        } catch (RuntimeException e) {
            throw new NotFoundException("User not found");
        }
    }

    public int reviseUser(final String id, final String password, final User user) {

        SqlParameterSource userMap = new MapSqlParameterSource()
                .addValue("userPassword", user.getUserPassword())
                .addValue("userName", user.getUserName())
                .addValue("userAddress", user.getUserAddress())
                .addValue("userCity", user.getUserCity().orElse(null))
                .addValue("userState", user.getUserState())
                .addValue("userZip", user.getUserZIP())
                .addValue("id", id)
                .addValue("password", password);
        return namedParameterJdbcTemplate.update(UPDATE_QUERY, userMap);
    }

    public int deleteUser(final String id, final String password) {

        SqlParameterSource idMap = new MapSqlParameterSource()
                .addValue("userId", id)
                .addValue("userPassword", password);
        return namedParameterJdbcTemplate.update(DELETE_QUERY, idMap);
    }

    public List<User> getListOfUsers() {

        return namedParameterJdbcTemplate.query("select * from userdetails", new UserRowMapper());
    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public String erase(String id, String password) {
        return null;
    }

    @Override
    public User modify(String id, String password, User user) {
        return null;
    }

    @Override
    public List<User> saveUsers(List<User> userList) {

        List<Map<String, Object>> userBatch = new ArrayList<>(userList.size());
        for (User user : userList) {
            userBatch.add(new MapSqlParameterSource()
                    .addValue("userId", user.getUserId())
                    .addValue("userPassword", user.getUserPassword())
                    .addValue("userName", user.getUserName())
                    .addValue("userAddress", user.getUserAddress())
                    .addValue("userCity", user.getUserCity().orElse(null))
                    .addValue("userState", user.getUserState())
                    .addValue("userZip", user.getUserZIP())
                    .getValues());
        }
        int[] update = namedParameterJdbcTemplate.batchUpdate(INSERT_QUERY, userBatch.toArray(new Map[userList.size()]));
        return userList;
    }
}
