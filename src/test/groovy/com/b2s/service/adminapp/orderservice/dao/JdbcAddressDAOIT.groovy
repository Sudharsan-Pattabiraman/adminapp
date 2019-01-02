package com.b2s.service.adminapp.orderservice.dao

import com.b2s.service.adminapp.ProjectApplication
import com.b2s.service.adminapp.orderservice.model.Address
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * Created by spattabiraman on 12/24/2018.
 */
@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [ProjectApplication.class])
@ActiveProfiles('local')
class JdbcAddressDAOIT extends Specification {

    @Autowired
    JdbcAddressDAO jdbcAddressDAO

    def 'Address is saved successfully'() {

        given:
        def expectedAddress = new Address()
        expectedAddress.platformOrderId = 12356789
        expectedAddress.firstName = 'first'
        expectedAddress.lastName = 'last'
        expectedAddress.businessName = 'bName'
        expectedAddress.addressLine1 = 'line1'
        expectedAddress.addressLine2 = 'line2'
        expectedAddress.addressLine3 = 'line3'
        expectedAddress.addressLine4 = 'line4'
        expectedAddress.city = 'testCity'
        expectedAddress.state = 'testState'
        expectedAddress.country = 'testCountry'
        expectedAddress.postalCode = 'test005'
        expectedAddress.email = 'test@bes.com'

        expect:
        def address = jdbcAddressDAO.save(expectedAddress)
        address == expectedAddress
    }

    def 'Address retrieved by Platform Order Id'() {

        given:
        def platformOrderId = 12356789

        def expectedAddress = new Address()
        expectedAddress.platformOrderId = 12356789
        expectedAddress.firstName = 'first'
        expectedAddress.lastName = 'last'
        expectedAddress.businessName = 'bName'
        expectedAddress.addressLine1 = 'line1'
        expectedAddress.addressLine2 = 'line2'
        expectedAddress.addressLine3 = 'line3'
        expectedAddress.addressLine4 = 'line4'
        expectedAddress.city = 'testCity'
        expectedAddress.state = 'testState'
        expectedAddress.country = 'testCountry'
        expectedAddress.postalCode = 'test005'
        expectedAddress.email = 'test@bes.com'

        expect:
        def address = jdbcAddressDAO.findByPlatformOrderId(platformOrderId)
        address.get() == expectedAddress

    }

    def 'Retrieving List of Address'() {

        given:
        def address = new Address()

        address.platformOrderId = 12356789
        address.firstName = 'first'
        address.lastName = 'last'
        address.businessName = 'bName'
        address.addressLine1 = 'line1'
        address.addressLine2 = 'line2'
        address.addressLine3 = 'line3'
        address.addressLine4 = 'line4'
        address.city = 'testCity'
        address.state = 'testState'
        address.country = 'testCountry'
        address.postalCode = 'test005'
        address.email = 'test@bes.com'

        def expectedAddresses = [address]

        expect:
        def addresses = jdbcAddressDAO.getAllAddress()
        addresses == expectedAddresses
    }

    def 'Attempt to retrieve Address for non existing Platform Order Id'() {

        given:
        def platformOrderId = 12356788

        expect:
        def shouldNotBeSameAddress = jdbcAddressDAO.findByPlatformOrderId(platformOrderId)
        !shouldNotBeSameAddress.isPresent()
    }
}
