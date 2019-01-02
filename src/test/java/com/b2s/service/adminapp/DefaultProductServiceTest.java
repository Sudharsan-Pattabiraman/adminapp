package com.b2s.service.adminapp;

import com.b2s.service.adminapp.dao.JdbcProductDao;
import com.b2s.service.adminapp.dao.ProductDao;
import com.b2s.service.adminapp.dao.ProductMapDao;
import com.b2s.service.adminapp.model.Product;
import com.b2s.service.adminapp.model.ProductType;
import com.b2s.service.adminapp.service.DefaultProductService;
import com.b2s.service.adminapp.service.ProductValidator;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by spattabiraman on 11/21/2018.
 */
public class DefaultProductServiceTest {

    DefaultProductService defaultProductService;
    JdbcProductDao mockProductDao;
    ProductValidator testProductValidator;

    @Before
    public void setUp() throws Exception {
        mockProductDao = EasyMock.createMock(JdbcProductDao.class);
        defaultProductService = new DefaultProductService(mockProductDao, testProductValidator);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void createProductInDB() throws Exception {

    }

    @Test
    public void viewProductInDB() throws Exception {

        Product testProduct = Product.builder().withProductId("id").withProductName("product")
                .withProductPoints(100).withProductType(ProductType.ELECTRONICS).build();
        EasyMock.expect(mockProductDao.getProductByName("product")).andReturn(testProduct);
        EasyMock.replay(mockProductDao);
        final Product product = defaultProductService.viewProduct("product");
        assertEquals(product.getProductName(), testProduct.getProductName());
        EasyMock.verify(mockProductDao);
    }

    @Test
    public void updateProductService() throws Exception {

    }

    @Test
    public void deleteProductService() throws Exception {

    }

    @Test
    public void productListService() throws Exception {

    }

    @Test
    public void productListByType() throws Exception {

    }

}