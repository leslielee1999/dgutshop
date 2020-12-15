package org.dgutstu.dgutshop.db;

import org.dgutstu.dgutshop.db.domain.DgutshopProduct;
import org.dgutstu.dgutshop.db.service.DgutshopCategoryItemService;
import org.dgutstu.dgutshop.db.service.DgutshopProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.List;

//import org.junit.jupiter.api.Test;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 23:11 2020/12/11
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DgutshopProductTest.class)
@ComponentScan({"org.dgutstu"})
public class DgutshopProductTest {

    @Autowired
    private DgutshopProductService dgutshopProductService;
    @Autowired
    private DgutshopCategoryItemService categoryItemService;

    @Test
    public void test(){
        List<DgutshopProduct> list = dgutshopProductService.list();
        System.out.println(list);
//        Assert.assertTrue(list.size() > 2);
    }

    @Test
    public void insert(){
        DgutshopProduct product = new DgutshopProduct();
        product.setCode("kkk");
        product.setBigOriginalPrice(new BigDecimal(4));
    }

    @Test
    public void list(){

    }
}
