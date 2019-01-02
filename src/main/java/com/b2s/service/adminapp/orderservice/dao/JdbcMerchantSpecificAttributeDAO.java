package com.b2s.service.adminapp.orderservice.dao;

import com.b2s.service.utils.lang.Validations;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by spattabiraman on 12/28/2018.
 */
@Repository
public class JdbcMerchantSpecificAttributeDAO implements MerchantSpecificAttributeDAO {

    private static final Logger logger = LoggerFactory.getLogger(JdbcMerchantSpecificAttributeDAO.class);

    private static final String CREATE_MERCHANT_SPECIFIC_ATTRIBUTE =
            "INSERT INTO MerchantSpecificAttribute(MerchantMessage_id,name, value, OrderLine_id) "
                    + "VALUES (:merchantMessageId, :name, :value, :orderLineId)";

    private static final String FIND_BY_MERCHANT_MESSAGE_ID_QUERY =
            "SELECT * FROM MerchantSpecificAttribute WHERE MerchantMessage_id = :merchantMessageId";

    private static final String GET_BY_ORDER_LINE_ID_QUERY =
            "SELECT * FROM MerchantSpecificAttribute WHERE OrderLine_id = :orderLineId";

    private final NamedParameterJdbcOperations jdbcOperations;

    public JdbcMerchantSpecificAttributeDAO(final NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = Validations.notNull(jdbcOperations, "jdbcOperations");
    }

    @Override
    public void saveAttributes(int merchantMessageId, Map<String, String> merchantAttributeMap, int orderLineId) {

        final int[] batchUpdate = jdbcOperations
                .batchUpdate(
                        CREATE_MERCHANT_SPECIFIC_ATTRIBUTE,
                        SqlParameterSourceUtils.createBatch(merchantAttributeMap.entrySet()
                                .stream()
                                .map(merchantSpecificationEntry -> new MapSqlParameterSource()
                                        .addValue("merchantMessageId", merchantMessageId)
                                        .addValue("name", merchantSpecificationEntry.getKey())
                                        .addValue("value", merchantSpecificationEntry.getValue())
                                        .addValue("orderLineId", orderLineId)
                                        .getValues()
                                ).toArray(Map[]::new)));

        logger.debug(
                "Actual Merchant specific attributes size {} , inserted rows {} ",
                merchantAttributeMap.size(),
                batchUpdate.length);
    }

    @Override
    public Map<String, String> retrieveByMerchantMessageId(int merchantMessageId) {

        final List<Map<String, Object>> result =
                jdbcOperations.queryForList(
                        FIND_BY_MERCHANT_MESSAGE_ID_QUERY,
                        new MapSqlParameterSource("merchantMessageId", merchantMessageId));

        return result
                .stream()
                .map(map -> Pair.of((String) map.get("name"), (String) map.get("value")))
                .collect(Collectors.toMap(Pair::getLeft, Pair::getRight));
    }

    @Override
    public Map<String, String> retrieveByOrderLineId(int orderLineId) {

        final List<Map<String, Object>> result =
                jdbcOperations.queryForList(
                        GET_BY_ORDER_LINE_ID_QUERY,
                        new MapSqlParameterSource("orderLineId", orderLineId));

        return result
                .stream()
                .map(map -> Pair.of((String) map.get("name"), (String) map.get("value")))
                .collect(Collectors.toMap(Pair::getLeft, Pair::getRight));
    }
}
