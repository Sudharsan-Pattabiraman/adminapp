package com.b2s.service.adminapp.orderservice.dao;

import com.b2s.service.adminapp.orderservice.model.Address;
import com.b2s.service.utils.lang.MoreIterables;
import com.b2s.service.utils.lang.Validations;
import com.google.common.collect.ImmutableMap;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by spattabiraman on 12/24/2018.
 */
@Repository
public class JdbcAddressDAO implements AddressDAO {

    private static final String SAVE_QUERY = "INSERT INTO Address " +
            "(platformOrder_Id, firstName, lastName, email, addressLine1, addressLine2, addressLine3," +
            " addressLine4, postalCode, businessName, city, state, country) " +
            "VALUES " +
            "(:platformOrderId, :firstName, :lastName, :email, :addressLine1, :addressLine2, :addressLine3," +
            " :addressLine4, :postalCode, :businessName, :city, :state, :country)";

    private static final String FIND_BY_PLATFORM_ID_QUERY = "SELECT * FROM Address WHERE platformOrder_Id = :platformOrderId";

    private static final String FIND_ALL_QUERY = "SELECT * FROM Address";

    private final AddressRowMapper rowMapper = new AddressRowMapper();

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public JdbcAddressDAO(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcOperations = namedParameterJdbcTemplate;
    }

    @Override
    public Address save(final Address address) {

        Validations.notNull(address, "address");
        final SqlParameterSource addressSqlMapper = new MapSqlParameterSource()
                .addValue("platformOrderId", address.getPlatformOrderId())
                .addValue("firstName", address.getFirstName())
                .addValue("lastName", address.getLastName())
                .addValue("email", address.getEmail())
                .addValue("addressLine1", address.getAddressLine1())
                .addValue("addressLine2", address.getAddressLine2())
                .addValue("addressLine3", address.getAddressLine3())
                .addValue("addressLine4", address.getAddressLine4())
                .addValue("postalCode", address.getPostalCode())
                .addValue("businessName", address.getBusinessName())
                .addValue("city", address.getCity())
                .addValue("state", address.getState())
                .addValue("country", address.getCountry());

        if (namedParameterJdbcOperations.update(SAVE_QUERY, addressSqlMapper) == 0) {
            final String errorMessage = String.format(
                    "saveAddress :: No rows were inserted in Address table for "
                            + "platform order id : %d",
                    address.getPlatformOrderId());
            throw new RuntimeException(errorMessage);
        }
        return address;
    }

    @Override
    public Optional<Address> findByPlatformOrderId(final long platformOrderId) {

        List<Address> addressList = namedParameterJdbcOperations.query(FIND_BY_PLATFORM_ID_QUERY,
                ImmutableMap.of("platformOrderId", platformOrderId), rowMapper);

        return MoreIterables.getOnlyElementIfPresent(addressList);
    }

    @Override
    public List<Address> getAllAddress() {

        return namedParameterJdbcOperations.query(FIND_ALL_QUERY, rowMapper);
    }
}
