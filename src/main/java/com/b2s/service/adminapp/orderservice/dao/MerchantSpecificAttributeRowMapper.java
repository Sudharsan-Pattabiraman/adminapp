package com.b2s.service.adminapp.orderservice.dao;

import com.b2s.service.adminapp.orderservice.model.MerchantSpecificAttribute;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by spattabiraman on 12/27/2018.
 */
public class MerchantSpecificAttributeRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        final Map<String, String> attributesMap = new HashMap<>();
        final MerchantSpecificAttribute merchantSpecificAttribute = new MerchantSpecificAttribute();
        merchantSpecificAttribute.setMerchantMessageId(rs.getInt("MerchantMessage_id"));
        attributesMap.put(rs.getString("name"), rs.getString("value"));
        merchantSpecificAttribute.setAttributes(attributesMap);
        merchantSpecificAttribute.setOrderLineId(rs.getInt("OrderLine_id"));
        return merchantSpecificAttribute;
    }
}
