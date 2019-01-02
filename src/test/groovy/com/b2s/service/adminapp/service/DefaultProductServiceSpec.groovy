package com.b2s.service.adminapp.service

import com.b2s.service.adminapp.dao.ProductMapDao
import com.b2s.service.adminapp.model.Product
import com.b2s.service.adminapp.model.ProductInfo
import com.b2s.service.adminapp.model.ProductType
import spock.lang.Specification
import spock.lang.Subject

/**
 * Created by spattabiraman on 11/22/2018.
 */

class DefaultProductServiceSpec extends Specification {

    @Subject
    DefaultProductService defaultProductService;
    ProductMapDao productMapDao;

    def setup() {
        productMapDao = Mock(ProductMapDao)
        ProductValidator productValidator = new ProductValidator();
        defaultProductService = new DefaultProductService(productMapDao, productValidator)
    }

    def 'test for viewing product'() {

        given:
        def testProduct = Product.builder().withProductName("abc").withProductPoints(100).withProductId("abc1").withProductType(ProductType.ELECTRONICS).build()
        productMapDao.getProductByName("abc") >> testProduct

        when:
        Product product = defaultProductService.viewProduct("abc")

        then:
        product == testProduct
    }

    def 'test for viewing list of products'() {

        given:
        def listProduct = [Product.builder().withProductName('abc').withProductPoints(30).withProductId('abc1').withProductType(ProductType.GIFT_CARD).build(), Product.builder().withProductName("xyz").withProductPoints(20).withProductId("xyz1").withProductType(ProductType.GIFT_CARD).build(),
                           Product.builder().withProductName("qwe").withProductPoints(50).withProductId("qwe1").withProductType(ProductType.HOME_APPLIANCES).build(), Product.builder().withProductName("opj").withProductPoints(40).withProductId("opj2").withProductType(ProductType.HOME_APPLIANCES).build()]
        productMapDao.getProducts() >> listProduct

        expect:
        List<ProductInfo> productInfoList = defaultProductService.productListByType(type, points)
        productInfoList == expectedProductInfoList

        where:
        type              | points || expectedProductInfoList
        'GIFT_CARD'       | 20     || [new ProductInfo('abc', 30, 'abc1'), new ProductInfo("xyz", 20, "xyz1")]
        'HOME_APPLIANCES' | 40     || [new ProductInfo("qwe", 50, "qwe1"), new ProductInfo("opj", 40, "opj2")]
    }
}