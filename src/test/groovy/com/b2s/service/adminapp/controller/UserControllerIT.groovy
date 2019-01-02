package com.b2s.service.adminapp.controller

import com.b2s.service.adminapp.ProjectApplication
import com.b2s.service.adminapp.dao.UserMapDao
import com.b2s.service.adminapp.model.User
import io.restassured.RestAssured
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * Created by spattabiraman on 12/13/2018.
 */
@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [ProjectApplication.class])
@ActiveProfiles('local')
class UserControllerIT extends Specification {

    @Autowired
    private UserMapDao userMapDao

    @LocalServerPort
    int localServerPort

    def setup() {
        RestAssured.port = localServerPort
    }

    def cleanup() {
        RestAssured.reset()
        userMapDao.getUserMap().clear()
    }

    def 'save user'() {

        given:
        def testUser = User.builder().withUserId('u123').withUserPassword('u@123').withUserName('user').withUserAddress('uAddress')
                .withUserCity('uCity').withUserState('uState').withUserZIP(1).build()

        when:
        def body = RestAssured.given()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .body(testUser)
                .post("/users")
                .then()
                .extract()
                .body()

        then:
        //body
        def actualUser = body.as(User)
        actualUser == testUser
    }

    def 'view user'() {

        given:
        def testUser = User.builder().withUserId('u123').withUserPassword('u@123').withUserName('user').withUserAddress('uAddress')
                .withUserCity('uCity').withUserState('uState').withUserZIP(1).build()

        def id = 'u123'
        def password = 'u@123'

        userMapDao.getUserMap().put("u123", testUser)

        when:
        def response = RestAssured.given()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("id", id)
                .queryParam("password", password)
                .when()
                .get("/users/{id}")
                .then()
                .extract()

        then:
        def actualUser = response.as(User)
        actualUser.userId == id
    }

    def 'delete user'() {

        given:
        def testUser = User.builder().withUserId('u123').withUserPassword('u@123').withUserName('user').withUserAddress('uAddress')
                .withUserCity('uCity').withUserState('uState').withUserZIP(1).build()

        userMapDao.getUserMap().put("u123", testUser)

        def id = 'u123'
        def password = 'u@123'

        expect:
        RestAssured.given()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("id", id)
                .queryParam("password", password)
                .when()
                .delete("/users/{id}")
                .then()
                .statusCode(200)
    }

    def 'list of users'() {

        given:
        def testUserList = [User.builder().withUserId('a123').withUserPassword('a@123').withUserName('auser')
                                    .withUserAddress('aAddress').withUserCity('aCity').withUserState('aState').withUserZIP(2).build(),
                            User.builder().withUserId('b123').withUserPassword('b@123').withUserName('buser')
                                    .withUserAddress('bAddress').withUserCity('bCity').withUserState('bState').withUserZIP(3).build()]

        userMapDao.getUserMap().put("a123", User.builder().withUserId('a123').withUserPassword('a@123').withUserName('auser')
                .withUserAddress('aAddress').withUserCity('aCity').withUserState('aState').withUserZIP(2).build())
        userMapDao.getUserMap().put("b123", User.builder().withUserId('b123').withUserPassword('b@123').withUserName('buser')
                .withUserAddress('bAddress').withUserCity('bCity').withUserState('bState').withUserZIP(3).build())

        when:
        def userList = RestAssured.given()
                .when()
                .get("/users")
                .then()
                .extract()
                .jsonPath()
                .getList("", User.class)

        then:
        userList == testUserList
    }
}
