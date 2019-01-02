package com.b2s.service.adminapp.controller

import com.b2s.service.adminapp.ProjectApplication
import com.b2s.service.adminapp.dao.ProductMapDao
import com.b2s.service.adminapp.model.Product
import com.b2s.service.adminapp.model.ProductInfo
import com.b2s.service.adminapp.model.ProductType
import io.restassured.RestAssured
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * Created by spattabiraman on 11/29/2018.
 */

@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [ProjectApplication.class])
@ActiveProfiles('local')
class ProductControllerIT extends Specification {

    @Autowired
    private ProductMapDao dao;


    @LocalServerPort
    int localServerPort

    def setup() {
        RestAssured.port = localServerPort
    }

    def cleanup() {
        RestAssured.reset()
        dao.getProductMap().clear()
    }

    def "Cre                                                                                                                                                ateProduct"() {

        given:
        Product product =
                Product.builder().withProductName("Television").withProductPoints(150).withProductType(ProductType.ELECTRONICS).withDescription('Test Electronics').build()

        when:
        def body = RestAssured.given()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .body(product)
                .post("/products")
                .then()
                .extract()
                .body()
        then:
        body
        def result = body.as(Product)
        result.productName == product.productName
    }

    def "get product"() {

        given:
        //Product product =
         //       Product.builder().withProductName("Television").withProductPoints(150).withProductType(ProductType.ELECTRONICS).withDescription('Test Electronics').build()



        def name = "Television"

        when:
        def body = RestAssured.given()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("name", name)
                .when()
                .get("/products/{name}")
                .then()
                .extract()

        then:
        def result = body.as(Product)
        result.productName == name
    }

    def "delete product"() {

        given:
        Product product =
                Product.builder().withProductId("PID001").withProductName("Television").withProductPoints(150).withProductType(ProductType.ELECTRONICS).withDescription('Test Electronics').build()

        dao.getProductMap().put("PID001", product);

        def name = "Television"

        expect:
        RestAssured.given()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("id", name)
                .when()
                .delete("/products/{id}")
                .then()
                .statusCode(200)
    }

    def 'get product list'() {

        given:
        def expectedProductList = [Product.builder().withProductId("PID0002").withProductName("Washing Machine").withProductPoints(150)
                                           .withProductType(ProductType.HOME_APPLIANCES).withDescription("test product").build(),
                                   Product.builder().withProductId("PID0003").withProductName("Mixer").withProductPoints(90)
                                           .withProductType(ProductType.HOME_APPLIANCES).withDescription("test product").build(),
                                   Product.builder().withProductId("PID0004").withProductName("iPhone").withProductPoints(150)
                                           .withProductType(ProductType.ELECTRONICS).withDescription("test product").build(),
                                   Product.builder().withProductId("PID0005").withProductName("Galaxy").withProductPoints(140)
                                           .withProductType(ProductType.ELECTRONICS).withDescription("test product").build()]

        dao.getProductMap().put("Washing Machine", Product.builder().withProductId("PID0002").withProductName("Washing Machine")
                .withProductPoints(150).withProductType(ProductType.HOME_APPLIANCES).withDescription("test product").build())

        dao.getProductMap().put("Mixer", Product.builder().withProductId("PID0003").withProductName("Mixer")
                .withProductPoints(90).withProductType(ProductType.HOME_APPLIANCES).withDescription("test product").build())

        dao.getProductMap().put("iPhone", Product.builder().withProductId("PID0004").withProductName("iPhone")
                .withProductPoints(150).withProductType(ProductType.ELECTRONICS).withDescription("test product").build())

        dao.getProductMap().put("Galaxy", Product.builder().withProductId("PID0005").withProductName("Galaxy")
                .withProductPoints(140).withProductType(ProductType.ELECTRONICS).withDescription("test product").build())

        when:
        def productList = RestAssured.given()
                .when()
                .get("/products")
                .then()
                .extract()
                .jsonPath()
                .getList("", Product.class)

        then:
        productList == expectedProductList
    }

    def 'get productInfo list'() {

        given:
        dao.getProductMap().put("Washing Machine", Product.builder().withProductId("PID0002").withProductName("Washing Machine")
                .withProductPoints(150).withProductType(ProductType.HOME_APPLIANCES).withDescription("test product").build())

        dao.getProductMap().put("Mixer", Product.builder().withProductId("PID0003").withProductName("Mixer")
                .withProductPoints(90).withProductType(ProductType.HOME_APPLIANCES).withDescription("test product").build())

        dao.getProductMap().put("iPhone", Product.builder().withProductId("PID0004").withProductName("iPhone")
                .withProductPoints(150).withProductType(ProductType.ELECTRONICS).withDescription("test product").build())

        dao.getProductMap().put("Galaxy", Product.builder().withProductId("PID0005").withProductName("Galaxy")
                .withProductPoints(140).withProductType(ProductType.ELECTRONICS).withDescription("test product").build())

        when:
        def productInfoList = RestAssured.given()
                .pathParams("type", type, "points", points)
                .when()
                .get("/products/{type}/{points}")
                .then()
                .extract()
                .jsonPath()
                .getList("", ProductInfo.class)

        then:
        productInfoList == expectedProductInfoList

        where:
        type              | points || expectedProductInfoList
        'HOME_APPLIANCES' | 100    || [new ProductInfo('Washing Machine', 150, 'PID0002')]
        'ELECTRONICS'     | 150    || [new ProductInfo('iPhone', 150, 'PID0004')]
    }
}
