package com.b2s.service.adminapp.orderservice.dao;

import com.b2s.service.adminapp.orderservice.model.Address;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by spattabiraman on 12/24/2018.
 */
public class AddressRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        final Address address = new Address();

        address.setPlatformOrderId(rs.getLong("platformOrder_Id"));
        address.setFirstName(rs.getString("firstName"));
        address.setLastName(rs.getString("lastName"));
        address.setBusinessName(rs.getString("businessName"));
        address.setAddressLine1(rs.getString("addressLine1"));
        address.setAddressLine2(rs.getString("addressLine2"));
        address.setAddressLine3(rs.getString("addressLine3"));
        address.setAddressLine4(rs.getString("addressLine4"));
        address.setCity(rs.getString("city"));
        address.setState(rs.getString("state"));
        address.setCountry(rs.getString("country"));
        address.setPostalCode(rs.getString("postalCode"));
        address.setEmail(rs.getString("email"));

        return address;
    }
}
