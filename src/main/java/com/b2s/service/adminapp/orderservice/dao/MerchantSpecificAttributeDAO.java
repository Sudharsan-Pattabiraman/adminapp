package com.b2s.service.adminapp.orderservice.dao;

import java.util.Map;

/**
 * Created by spattabiraman on 12/28/2018.
 */
public interface MerchantSpecificAttributeDAO {

    public void saveAttributes(
            final int merchantMessageId,
            final Map<String, String> merchantAttributeMap,
            final int orderLineId);

    Map<String, String> retrieveByMerchantMessageId(final int merchantMessageId);

    Map<String, String> retrieveByOrderLineId(final int orderLineId);
}
