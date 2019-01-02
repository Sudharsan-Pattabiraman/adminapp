package com.b2s.service.adminapp.orderservice.dao

import com.b2s.service.adminapp.ProjectApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * Created by spattabiraman on 12/28/2018.
 */
@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [ProjectApplication.class])
@ActiveProfiles('local')
class JdbcMerchantSpecificAttributeDAOIT extends Specification {

    @Autowired
    JdbcMerchantSpecificAttributeDAO jdbcMerchantSpecificAttributeDAO

    def "Saving Merchant Specific Attributes Successfully"() {

        given:
        Map<String, String> attributeMap = new HashMap()
        attributeMap.put("name1", "value1")
        attributeMap.put("name2", "value2")
        def merchantMessageId = 123456
        def orderLineId = 987654

        expect:
        jdbcMerchantSpecificAttributeDAO.saveAttributes(merchantMessageId, attributeMap, orderLineId)
    }

    def "Retrieving Merchant Specific Attribute using Merchant Message id - Successfully"() {

        given:
        def expectedMerchantAttributeMap = new HashMap()
        expectedMerchantAttributeMap.put("name1", "value1")
        expectedMerchantAttributeMap.put("name2", "value2")

        def merchantMessageId = 123456

        when:
        def actualMerchantAttributeMap = jdbcMerchantSpecificAttributeDAO.retrieveByMerchantMessageId(merchantMessageId)

        then:
        actualMerchantAttributeMap == expectedMerchantAttributeMap

    }

    def "Retrieving Merchant Specific Attribute using Order Line id - Successfully"() {

        given:
        def expectedMerchantAttributeMap = new HashMap()
        expectedMerchantAttributeMap.put("name1", "value1")
        expectedMerchantAttributeMap.put("name2", "value2")

        def orderLineId = 987654

        when:
        def actualMerchantAttributeMap = jdbcMerchantSpecificAttributeDAO.retrieveByOrderLineId(orderLineId)

        then:
        actualMerchantAttributeMap == expectedMerchantAttributeMap
    }

    def 'Retrieving Merchant Specific Attribute using Merchant Message id - Failure'(){

        given:
        def merchantMessageId = 123457

        when:
        def merchantAttributeMap = jdbcMerchantSpecificAttributeDAO.retrieveByMerchantMessageId(merchantMessageId)

        then:
        merchantAttributeMap.isEmpty()
    }

    def 'Retrieving Merchant Specific Attribute using Order Line id - Failure'(){

        given:
        def orderLineId = 987655

        when:
        def merchantAttributeMap = jdbcMerchantSpecificAttributeDAO.retrieveByOrderLineId(orderLineId)

        then:
        merchantAttributeMap.isEmpty()
    }
}
