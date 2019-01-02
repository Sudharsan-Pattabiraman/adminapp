package com.b2s.service.adminapp.dao;

import com.b2s.service.adminapp.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by spattabiraman on 10/23/2018.
 */
public class UserRowMapper implements RowMapper {
    public User mapRow(ResultSet resultSet, int i) throws SQLException {

        return User.builder().withUserId(resultSet.getString("userid"))
                .withUserPassword(resultSet.getString("userpassword"))
                .withUserName(resultSet.getString("username"))
                .withUserAddress(resultSet.getString("useraddress"))
                .withUserCity(resultSet.getString("usercity"))
                .withUserState(resultSet.getString("userstate"))
                .withUserZIP(resultSet.getInt("userzip"))
                .build();
    }
}
