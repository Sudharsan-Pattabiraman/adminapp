package com.b2s.service.adminapp.service

import com.b2s.service.adminapp.dao.JdbcUserDao
import com.b2s.service.adminapp.dao.UserDao
import com.b2s.service.adminapp.dao.UserMapDao
import com.b2s.service.adminapp.model.User
import spock.lang.Specification
import spock.lang.Subject

/**
 * Created by spattabiraman on 12/13/2018.
 */
class DefaultUserServiceSpec extends Specification {

    @Subject
    DefaultUserService defaultUserService
    UserDao mockUserDao
    UserValidator userValidator

    def setup() {
        mockUserDao = Mock(UserMapDao)
        defaultUserService = new DefaultUserService(mockUserDao, userValidator)
    }

    def 'test for viewUser'() {

        given:
        def testUser = User.builder().withUserId('u123').withUserPassword('u@123').withUserName('user')
                .withUserAddress('uAddress').withUserCity('uCity')
                .withUserState('uState').withUserZIP(1).build()
        mockUserDao.retrieveUser('u123', 'u@123') >> testUser

        expect:
        User actualUser = defaultUserService.display('u123', 'u@123')
        actualUser == testUser
    }

    def 'test for list of users'(){

        given:
        def testUserList = [User.builder().withUserId('u123').withUserPassword('u@123').withUserName('user').withUserAddress('uAddress')
                                .withUserCity('uCity').withUserState('uState').withUserZIP(1).build(),
                        User.builder().withUserId('b123').withUserPassword('b@123').withUserName('abuser').withUserAddress('bAddress')
                                .withUserCity('bCity').withUserState('bState').withUserZIP(2).build()]
        mockUserDao.getListOfUsers() >> testUserList

        expect:
        def actualUserList = defaultUserService.getAllUsers()
        actualUserList == testUserList
    }
}
